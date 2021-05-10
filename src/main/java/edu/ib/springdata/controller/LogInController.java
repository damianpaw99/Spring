package edu.ib.springdata.controller;

import edu.ib.springdata.PasswordEncoderConfig;
import edu.ib.springdata.service.UserDtoService;
import edu.ib.springdata.user.LoginUser;
import edu.ib.springdata.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class LogInController {

    private UserDtoService userDtoService;

    @Autowired
    public LogInController(UserDtoService userDtoService) {
        this.userDtoService = userDtoService;
    }


    @PostMapping("/getToken")
    public String getToken(@RequestBody LoginUser user1) throws Exception {
        String role = userDtoService.findUser(user1.getLogin(),user1.getPassword());
        User user=new User(user1.getLogin(),user1.getPassword(),role);

        long millis=System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(user.getName())
                .claim("roles",user.getRole())
                .setIssuedAt(new Date(millis))
                .setExpiration(new Date(millis+10*60*1000))
                .signWith(SignatureAlgorithm.HS512,user.getPassword())
                .compact();
    }
}
