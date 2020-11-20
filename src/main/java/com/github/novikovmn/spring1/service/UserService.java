package com.github.novikovmn.spring1.service;

import com.github.novikovmn.spring1.domain.User;
import com.github.novikovmn.spring1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> getAll() { return userRepository.findAll(); }
}
