package Entities;

import Entities.Repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Initialize {

    private static final Logger log = LoggerFactory.getLogger(Initialize.class);


    public static void main(String[] args) {
        SpringApplication.run(Initialize.class, args);
    }

    @Bean
    public CommandLineRunner initialization(BookRepo bookRepo, OwnerRepo oRepo, UserRepo uRepo){
        //This can be used to initialize DB with stuff on start up.
        //Will be used just for testing of JPA instantiations of objects.
        return (args) -> {
            Owner testOwner = new Owner("testOwnerName");
            testOwner.setPassword("12345");
            oRepo.save(testOwner);

            Book testBook = new Book("testBook", 5555);
            bookRepo.save(testBook);

            User testUser = new User("testUserName");
            testUser.setPassword("myPassword");
            uRepo.save(testUser);


            Owner findOwner = oRepo.findByUsername("testOwnerName");
            log.info("Owner Added: " + findOwner.getUsername());

            Book findBook = bookRepo.findById(5555);
            log.info("Book Added: " + findBook.getISBN());

            User findUser = uRepo.findByUsername("testUserName");
            log.info("User Added: " + findUser.getUsername());
        };
    }
}
