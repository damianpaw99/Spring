package edu.ib.springdata.repository;

import edu.ib.springdata.UserDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDtoRepository extends CrudRepository<UserDto,Long> {
}
