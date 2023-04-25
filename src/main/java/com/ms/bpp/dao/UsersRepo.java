package com.ms.bpp.dao;

import com.ms.bpp.entity.Users;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepo extends CrudRepository<Users, String> {

    Optional<Users> findByEmail(String username);

    Boolean existsByEmail(String email);

    Boolean existsByMobile(String mobile);

   // List<Users> getUsersByFirstNameOrLastNameIgnoreCaseContaining(String user);

    List<Users> findAll(Specification specification);

}
