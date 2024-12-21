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

import cloneamazon.Amazon.Clone.Entity.Users;
import cloneamazon.Amazon.Clone.Service.UserService;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Users registerUser(@RequestBody Users user){
        return userService.registerUser(user);
        
    }   

    @GetMapping("/all")
    public List<Users> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable("id") Long userId){
        return userService.getUserById(userId);
    }

    @GetMapping("/{email}")
    public Users getUsersByemail(@PathVariable("email") String email){
        return userService.getUserByEmail(email);
    }

    @PutMapping("/{id}")
    public Users changeUserDetails(@PathVariable("id") Long userId , @RequestBody Users user ){
        return userService.changeUserDetails(user);
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable("id") Long userId){
        return userService.deleteUserById(userId);
    }
}
