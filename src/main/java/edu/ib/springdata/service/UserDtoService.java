package edu.ib.springdata.service;

import edu.ib.springdata.repository.UserDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDtoService {

    private UserDtoRepository userDtoRepository;

    @Autowired
    public UserDtoService(UserDtoRepository userDtoRepository) {
        this.userDtoRepository = userDtoRepository;
    }
}
