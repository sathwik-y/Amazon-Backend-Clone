package cloneamazon.Amazon.Clone.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import cloneamazon.Amazon.Clone.Entity.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users,Long> {
    public Users getByEmail(String email);
    Users findByUsername(String username);
}
