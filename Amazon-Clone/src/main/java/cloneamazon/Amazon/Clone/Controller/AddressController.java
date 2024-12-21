package cloneamazon.Amazon.Clone.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import cloneamazon.Amazon.Clone.Entity.Address;
import cloneamazon.Amazon.Clone.Service.AddressService;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/address")
public class AddressController {
    
    
    @Autowired
    private AddressService addressService;

    @PostMapping("/add")
    public Address addAddress(@RequestBody Address address){
        return addressService.addAddress(address);
        
    }   

    @GetMapping("/all/")
    public List<Address> allAddresses(){
        return addressService.allAddresses();
    }

    @GetMapping("/{id}")
    public Address addressById(@PathVariable("id") Long AddressId){
        return addressService.addressById(AddressId);
        }

    @GetMapping("/user/{userId}")
        public List<Address> addressesByUser(@PathVariable("userId") Long userId) {
        return addressService.addressesByUserId(userId);
    }
    @GetMapping("/user/{email}")  
    public List<Address> addressesByEmail(@PathVariable("email") String email) {
        return addressService.addressesByEmail(email);
    }
    @PutMapping("/{id}")
        public Address changeAddress(@PathVariable("id") Long AddressId, @RequestBody Address address) {
        return addressService.changeAddress(address);
    }

    @DeleteMapping("/{id}")
    public String deleteAddress(@PathVariable("id") Long AddressId){
        return addressService.deleteAddress(AddressId);
    }
}
