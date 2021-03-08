package Entities.Repositories;

import Entities.*;
import org.springframework.data.repository.CrudRepository;

public interface BookRepo extends  CrudRepository<Book, Long>{

    Book findById(long ISBN);
}
