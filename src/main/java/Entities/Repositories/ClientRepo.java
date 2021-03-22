package Entities.Repositories;

import Entities.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepo extends CrudRepository<Client, Long> {

    Client findById(long id);

    Client findByUsername(String username);
}