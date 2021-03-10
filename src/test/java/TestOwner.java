import Entities.Owner;

import static org.junit.jupiter.api.Assertions.*;

public class TestOwner {

    Owner owner;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        this.owner = new Owner("testUser");
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown(){

    }

    @org.junit.jupiter.api.Test
    void testSetId() {
        owner.setId(1234);
        assertEquals(this.owner.getId(), 1234);
    }

    @org.junit.jupiter.api.Test
    void testSetUsername() {
        owner.setUsername("Changed Username");
        assertEquals(this.owner.getUsername(), "Changed Username");
    }

    @org.junit.jupiter.api.Test
    void testSetPassword() {
        owner.setPassword("myPassword");
        assertEquals(this.owner.getPassword(), "myPassword");
    }


}
