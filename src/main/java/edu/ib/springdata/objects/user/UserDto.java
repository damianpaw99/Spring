package edu.ib.springdata.objects.user;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String hashedPassword;
    private String role;

    public UserDto() {
    }

    public UserDto(String name, String hashedPassword, String role) {
        this.name = name;
        this.hashedPassword = hashedPassword;
        this.role = role;
    }

    public UserDto(Long id, String name, String hashedPassword, String role) {
        this.id = id;
        this.name = name;
        this.hashedPassword = hashedPassword;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
