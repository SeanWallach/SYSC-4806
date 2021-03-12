import Entities.Cart;
import Entities.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUser {

    User user;
    Cart cart;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        this.user = new User("testUser");
        this.cart = new Cart(123);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown(){

    }

    @org.junit.jupiter.api.Test
    void testSetId() {
        user.setId(1234);
        assertEquals(this.user.getId(), 1234);
    }

    @org.junit.jupiter.api.Test
    void testSetUsername() {
        user.setUsername("Changed Username");
        assertEquals(this.user.getUsername(), "Changed Username");
    }

    @org.junit.jupiter.api.Test
    void testSetPassword() {
        user.setPassword("myPassword");
        assertEquals(this.user.getPassword(), "myPassword");
    }

    @org.junit.jupiter.api.Test
    void testSetCart() {
        user.setCart(cart);
        assertEquals(user.getCart(), cart);
    }

}
