package ShopEasy.service;

import ShopEasy.model.User;

public interface JwtService {

    String generateToken(User user);
}