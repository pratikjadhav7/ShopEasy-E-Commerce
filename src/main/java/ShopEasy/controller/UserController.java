package ShopEasy.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ShopEasy.dto.UpdateProfileRequest;
import ShopEasy.dto.UserResponse;
import ShopEasy.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // =====================================================
    // GET USER BY ID
    // =====================================================

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                userService.getUserById(userId)
        );
    }

 // =====================================================
 // UPDATE USER PROFILE
 // =====================================================

 @PutMapping("/{userId}")
 public ResponseEntity<UserResponse> updateProfile(
         @PathVariable Long userId,
         @Valid @RequestBody UpdateProfileRequest request) {

     return ResponseEntity.ok(
             userService.updateProfile(
                     userId,
                     request
             )
     );
 }
    // =====================================================
    // GET ALL CUSTOMERS
    // =====================================================

    @GetMapping("/customers")
    public ResponseEntity<List<UserResponse>> getAllCustomers() {

        return ResponseEntity.ok(
                userService.getAllCustomers()
        );
    }

    // =====================================================
    // SEARCH CUSTOMERS
    // =====================================================

    @GetMapping("/customers/search")
    public ResponseEntity<List<UserResponse>> searchCustomers(
            @RequestParam String name) {

        return ResponseEntity.ok(
                userService.searchCustomers(name)
        );
    }

    // =====================================================
    // TOGGLE USER ACTIVE / INACTIVE - ADMIN
    // =====================================================

    @PatchMapping("/{userId}/toggle")
    public ResponseEntity<UserResponse> toggleUserStatus(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                userService.toggleUserStatus(userId)
        );
    }
}