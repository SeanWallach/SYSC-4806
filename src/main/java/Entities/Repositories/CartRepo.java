package Entities.Repositories;

import Entities.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepo extends CrudRepository<Cart, Long> {

    Cart findById(long Id);
}
