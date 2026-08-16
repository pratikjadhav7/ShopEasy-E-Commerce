package ShopEasy.service;

import ShopEasy.dto.LoginRequest;
import ShopEasy.dto.LoginResponse;
import ShopEasy.dto.RegisterRequest;

public interface AuthService {

    LoginResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}