package ShopEasy.dto;

public class AddressResponse {

    private Long addressId;
    private String addressLine;
    private String city;
    private String state;
    private String pincode;
    private String landmark;

    public AddressResponse() {
    }

    public AddressResponse(
            Long addressId,
            String addressLine,
            String city,
            String state,
            String pincode,
            String landmark
    ) {
        this.addressId = addressId;
        this.addressLine = addressLine;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.landmark = landmark;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }
}