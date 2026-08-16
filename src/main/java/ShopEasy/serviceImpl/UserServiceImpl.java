package ShopEasy.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ShopEasy.dto.UpdateProfileRequest;
import ShopEasy.dto.UserResponse;
import ShopEasy.model.Role;
import ShopEasy.model.User;
import ShopEasy.repository.UserRepository;
import ShopEasy.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        return mapToResponse(user);
    }

    @Override
    @Transactional
    public UserResponse updateProfile(
            Long userId,
            UpdateProfileRequest request
    ) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );


        // =========================
        // PHONE DUPLICATE CHECK
        // =========================

        if (!user.getPhone().equals(request.getPhone())
                && userRepository.existsByPhone(request.getPhone())) {

            throw new RuntimeException(
                    "Phone number already registered"
            );
        }


        // =========================
        // EMAIL DUPLICATE CHECK
        // =========================

        if (!user.getEmail().equalsIgnoreCase(request.getEmail())
                && userRepository.existsByEmailIgnoreCase(request.getEmail())) {

            throw new RuntimeException(
                    "Email already registered"
            );
        }


        // =========================
        // UPDATE PROFILE
        // =========================

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());


        return mapToResponse(
                userRepository.save(user)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllCustomers() {

        return userRepository.findByRole(Role.CUSTOMER)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> searchCustomers(String name) {

        return userRepository
                .findByRoleAndNameContainingIgnoreCase(
                        Role.CUSTOMER,
                        name
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private UserResponse mapToResponse(User user) {

        return new UserResponse(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole(),
                user.isActive()
        );
    }

    @Override
    @Transactional
    public UserResponse toggleUserStatus(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        // Toggle active status
        user.setActive(!user.isActive());

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }
}