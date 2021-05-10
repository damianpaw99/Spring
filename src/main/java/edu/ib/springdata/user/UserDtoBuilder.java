package edu.ib.springdata.user;


import edu.ib.springdata.PasswordEncoderConfig;
import edu.ib.springdata.UserDto;
import edu.ib.springdata.user.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserDtoBuilder {

    public UserDto build(User user){
        UserDto userDto=new UserDto();
        PasswordEncoderConfig p=new PasswordEncoderConfig();
        PasswordEncoder encoder= p.passwordEncoder();
        userDto.setHashedPassword(encoder.encode(user.getPassword()));
        userDto.setName(user.getName());
        userDto.setRole(user.getRole());
        return userDto;
    }

}
