package Entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;


@Component
@Entity
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Owner(){}

    public Owner(String username){
        this.username = username;
    }
}
