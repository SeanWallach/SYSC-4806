package Entities.Repositories;

import Entities.*;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepo extends CrudRepository<Owner, Long>{

    Owner findById(long id);

    Owner findByUsername(String username);
}
