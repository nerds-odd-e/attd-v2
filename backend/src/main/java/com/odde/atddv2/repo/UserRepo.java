package com.odde.atddv2.repo;

import com.odde.atddv2.entity.User;
import org.springframework.data.repository.Repository;

public interface UserRepo extends Repository<User, Long> {
    User save(User user);
}
