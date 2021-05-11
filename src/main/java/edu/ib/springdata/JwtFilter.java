package edu.ib.springdata;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends BasicAuthenticationFilter {

    public JwtFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header=request.getHeader("Authorization");
        if(header==null || !header.startsWith("Bearer ")){
            throw new ServletException();
        }
        UsernamePasswordAuthenticationToken authResult=getAuthorizationToken(header);
        SecurityContextHolder.getContext().setAuthentication(authResult);


    }

    private UsernamePasswordAuthenticationToken getAuthorizationToken(String header){
        Jws<Claims> claimsJws=Jwts.parser().setSigningKey("pass").parseClaimsJws(header.replace("Bearer ",""));
        return null;
    }
}

