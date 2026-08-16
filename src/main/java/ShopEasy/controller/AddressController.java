package ShopEasy.controller;

import ShopEasy.dto.AddressRequest;
import ShopEasy.dto.AddressResponse;
import ShopEasy.service.AddressService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@CrossOrigin(origins = "*")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    // =====================================================
    // ADD ADDRESS
    // =====================================================

    @PostMapping("/user/{userId}")
    public ResponseEntity<AddressResponse> addAddress(
            @PathVariable Long userId,
            @Valid @RequestBody AddressRequest request) {

        return ResponseEntity.ok(
                addressService.addAddress(userId, request)
        );
    }

    // =====================================================
    // GET ADDRESS BY ID
    // =====================================================

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressResponse> getAddressById(
            @PathVariable Long addressId) {

        return ResponseEntity.ok(
                addressService.getAddressById(addressId)
        );
    }

    // =====================================================
    // GET USER ADDRESSES
    // =====================================================

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AddressResponse>> getUserAddresses(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                addressService.getUserAddresses(userId)
        );
    }

    // =====================================================
    // UPDATE ADDRESS
    // =====================================================

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressResponse> updateAddress(
            @PathVariable Long addressId,
            @Valid @RequestBody AddressRequest request) {

        return ResponseEntity.ok(
                addressService.updateAddress(
                        addressId,
                        request
                )
        );
    }

    // =====================================================
    // DELETE ADDRESS
    // =====================================================

    @DeleteMapping("/{addressId}")
    public ResponseEntity<String> deleteAddress(
            @PathVariable Long addressId) {

        addressService.deleteAddress(addressId);

        return ResponseEntity.ok(
                "Address deleted successfully"
        );
    }
}