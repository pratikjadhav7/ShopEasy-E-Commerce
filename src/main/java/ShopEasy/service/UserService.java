package ShopEasy.service;

import ShopEasy.dto.UpdateProfileRequest;
import ShopEasy.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse getUserById(Long userId);

    UserResponse updateProfile(
            Long userId,
            UpdateProfileRequest request
    );

    List<UserResponse> getAllCustomers();

    List<UserResponse> searchCustomers(String name);
    
    UserResponse toggleUserStatus(Long userId);
}