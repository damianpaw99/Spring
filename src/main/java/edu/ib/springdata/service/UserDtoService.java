package edu.ib.springdata.service;

import edu.ib.springdata.PasswordEncoderConfig;
import edu.ib.springdata.UserDto;
import edu.ib.springdata.repository.UserDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Iterator;

@Service
public class UserDtoService {

    private UserDtoRepository userDtoRepository;

    @Autowired
    public UserDtoService(UserDtoRepository userDtoRepository) {
        this.userDtoRepository = userDtoRepository;
    }


    public String findUser(String name, String password) throws Exception {
        Iterable<UserDto> list=userDtoRepository.findAll();
        Iterator<UserDto> it=list.iterator();
        String role=null;
        PasswordEncoderConfig p=new PasswordEncoderConfig();
        PasswordEncoder passwordEncoder=p.passwordEncoder();

        while(it.hasNext()){
            UserDto user=it.next();
            if(user.getName().equals(name) && passwordEncoder.matches(password,user.getHashedPassword())){
                role=user.getRole();
                break;
            }
        }
        if(role==null){
            throw new Exception("Wrong login or password");
        }
        return role;
    }
}
