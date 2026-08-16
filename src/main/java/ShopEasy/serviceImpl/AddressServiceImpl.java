package ShopEasy.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ShopEasy.dto.AddressRequest;
import ShopEasy.dto.AddressResponse;
import ShopEasy.model.Address;
import ShopEasy.model.User;
import ShopEasy.repository.AddressRepository;
import ShopEasy.repository.UserRepository;
import ShopEasy.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressServiceImpl(
            AddressRepository addressRepository,
            UserRepository userRepository) {

        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public AddressResponse addAddress(
            Long userId,
            AddressRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        Address address = new Address();

        address.setAddressLine(request.getAddressLine());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setPincode(request.getPincode());
        address.setLandmark(request.getLandmark());
        address.setUser(user);

        return mapToResponse(
                addressRepository.save(address)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressResponse> getUserAddresses(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        return addressRepository.findByUser(user)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AddressResponse getAddressById(Long addressId) {

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() ->
                        new RuntimeException("Address not found")
                );

        return mapToResponse(address);
    }

    @Override
    @Transactional
    public AddressResponse updateAddress(
            Long addressId,
            AddressRequest request) {

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() ->
                        new RuntimeException("Address not found")
                );

        address.setAddressLine(request.getAddressLine());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setPincode(request.getPincode());
        address.setLandmark(request.getLandmark());

        return mapToResponse(
                addressRepository.save(address)
        );
    }

    @Override
    @Transactional
    public void deleteAddress(Long addressId) {

        if (!addressRepository.existsById(addressId)) {
            throw new RuntimeException("Address not found");
        }

        addressRepository.deleteById(addressId);
    }

    private AddressResponse mapToResponse(Address address) {

        return new AddressResponse(
                address.getAddressId(),
                address.getAddressLine(),
                address.getCity(),
                address.getState(),
                address.getPincode(),
                address.getLandmark()
        );
    }
}