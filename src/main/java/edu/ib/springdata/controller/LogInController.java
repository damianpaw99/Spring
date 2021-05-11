package edu.ib.springdata.controller;

import edu.ib.springdata.service.UserDtoService;
import edu.ib.springdata.objects.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String getToken(@RequestBody User user) throws Exception {
        String role = userDtoService.findUser(user.getName(),user.getPassword());
        user.setRole(role);

        long millis=System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(user.getName())
                .claim("roles",user.getRole())
                .setIssuedAt(new Date(millis))
                .setExpiration(new Date(millis+10*60*1000))
                .signWith(SignatureAlgorithm.HS512,"z3gHeX23")
                .compact();
    }
}
