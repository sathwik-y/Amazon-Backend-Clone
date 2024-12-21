package cloneamazon.Amazon.Clone.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cloneamazon.Amazon.Clone.Entity.UserPrincipal;
import cloneamazon.Amazon.Clone.Entity.Users;
import cloneamazon.Amazon.Clone.Repository.UserRepo;

@Service 
public class MyUserDetailsService implements  UserDetailsService{
    @Autowired
    private UserRepo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repo.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }   
        return new UserPrincipal(user);
        }
}