package cloneamazon.Amazon.Clone.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cloneamazon.Amazon.Clone.Entity.UserPrincipal;
import cloneamazon.Amazon.Clone.Entity.Users;
import cloneamazon.Amazon.Clone.Repository.UserRepo;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    public Users registerUser(Users user) {
        if(getUserByEmail(user.getEmail())!=null) {
            throw new RuntimeException("Email already exists");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public Users getUserById(Long userId) {
       Optional<Users> user = userRepo.findById(userId);
        return user.orElseThrow(()->new RuntimeException("User not found"));
    }

    public Users getUserByEmail(String email) {
        return userRepo.getByEmail(email);
    }
    
    public Users changeUserDetails(Users user) {
        Users usr = userRepo.getReferenceById(user.getUserId());
        usr.setEmail(user.getEmail());
        usr.setFirstName(user.getFirstName());
        usr.setLastName(user.getLastName());
        usr.setPassword(user.getPassword());
        usr.setUsername(user.getUsername());
        return userRepo.save(usr);
    }
    public String deleteUserById(Long userId) {
        if(!userRepo.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        userRepo.deleteById(userId);
        return "User Deleted";
    }

    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

        @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }   
        return new UserPrincipal(user);
        }

}
