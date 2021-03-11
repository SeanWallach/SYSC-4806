package Entities.Repositories;

import Entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {

    User findById(long id);

    User findByUsername(String username);
}
