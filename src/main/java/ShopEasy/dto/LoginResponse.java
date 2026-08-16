package ShopEasy.dto;

import ShopEasy.model.Role;

public class LoginResponse {

    private Long userId;
    private String name;
    private String phone;
    private Role role;
    private String token;

    public LoginResponse() {
    }

    public LoginResponse(
            Long userId,
            String name,
            String phone,
            Role role,
            String token) {

        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.role = role;
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}