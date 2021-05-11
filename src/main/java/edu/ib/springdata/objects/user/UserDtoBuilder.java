package edu.ib.springdata.objects.user;


import edu.ib.springdata.security.PasswordEncoderConfig;
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
