package cloneamazon.Amazon.Clone.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cloneamazon.Amazon.Clone.Entity.Address;
import cloneamazon.Amazon.Clone.Repository.AddressRepo;

@Service
public class AddressService {

    @Autowired
    private AddressRepo addressRepo;

    public Address addAddress(Address address) {
        if (address.getUser() == null) {
            throw new IllegalArgumentException("Address must be associated with a user");
        }
            return addressRepo.save(address);
        }
     
    public List<Address> allAddresses() {
            return addressRepo.findAll();
    }

    public Address addressById(Long addressId) {
        return addressRepo.findById(addressId).orElse(null);
    }

    public List<Address> addressesByUserId(Long userId) {
        return addressRepo.findByUser_UserId(userId);
    }

    public Address changeAddress(Address address) {
        Address addy = addressRepo.getReferenceById(address.getAddressId());
        addy.setCity(address.getCity());
        addy.setCountry(address.getCountry());
        addy.setState(address.getState());
        addy.setStreet(address.getStreet());
        addy.setZipCode(address.getZipCode());
        return addressRepo.save(addy);
    }

    public String deleteAddress(Long addressId) {
        addressRepo.deleteById(addressId);
        return "Address deleted successfully";
    }

    public List<Address> addressesByEmail(String email) {
        return addressRepo.findByUser_Email(email);
    }

}
