package ShopEasy.service;

import ShopEasy.dto.AddressRequest;
import ShopEasy.dto.AddressResponse;

import java.util.List;

public interface AddressService {

    AddressResponse addAddress(
            Long userId,
            AddressRequest request
    );

    List<AddressResponse> getUserAddresses(Long userId);

    AddressResponse getAddressById(Long addressId);

    AddressResponse updateAddress(
            Long addressId,
            AddressRequest request
    );

    void deleteAddress(Long addressId);
}