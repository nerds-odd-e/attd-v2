package com.odde.atddv2.repo;

import com.odde.atddv2.entity.User;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepo extends Repository<User, Long> {
    User save(User user);

    void deleteAll();

    Optional<User> findByUserNameAndPassword(String username, String password);

    boolean existsByUserName(String username);
}
