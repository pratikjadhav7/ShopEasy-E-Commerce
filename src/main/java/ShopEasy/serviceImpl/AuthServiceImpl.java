package ShopEasy.serviceImpl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ShopEasy.dto.LoginRequest;
import ShopEasy.dto.LoginResponse;
import ShopEasy.dto.RegisterRequest;
import ShopEasy.model.Role;
import ShopEasy.model.User;
import ShopEasy.repository.UserRepository;
import ShopEasy.service.AuthService;
import ShopEasy.service.JwtService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    

    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public AuthServiceImpl(
            UserRepository userRepository,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    @Transactional
    public LoginResponse register(RegisterRequest request) {

        if (userRepository.existsByPhone(request.getPhone())) {
            throw new RuntimeException("Phone number already registered");
        }

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        // Password is NEVER stored as plain text
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        // Public registration always creates CUSTOMER
        user.setRole(Role.CUSTOMER);

        user.setActive(true);

        User savedUser = userRepository.save(user);

        return mapToLoginResponse(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByPhone(request.getPhone())
                .orElseThrow(() ->
                        new RuntimeException("Invalid phone number or password")
                );

        if (!user.isActive()) {
            throw new RuntimeException("Account is inactive");
        }

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {
            throw new RuntimeException("Invalid phone number or password");
        }

        return mapToLoginResponse(user);
    }

    private LoginResponse mapToLoginResponse(User user) {

        String token = jwtService.generateToken(user);

        return new LoginResponse(
                user.getUserId(),
                user.getName(),
                user.getPhone(),
                user.getRole(),
                token
        );
    }
}