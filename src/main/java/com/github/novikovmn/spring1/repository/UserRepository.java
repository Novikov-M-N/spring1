package com.github.novikovmn.spring1.repository;

import com.github.novikovmn.spring1.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
