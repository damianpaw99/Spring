package edu.ib.springdata;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserDtoBuilder {

    public UserDto build(User user){
        UserDto userDto=new UserDto();
        PasswordEncoder encoder=new BCryptPasswordEncoder();
        userDto.setHashedPassword(encoder.encode(user.getPassword()));
        userDto.setName(user.getName());
        userDto.setRole(user.getRole());
        return userDto;
    }

}
