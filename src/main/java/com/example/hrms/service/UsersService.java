package com.example.hrms.service;


import com.example.hrms.model.Users;
import com.example.hrms.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Users getUserById(Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    public Users getUserByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    public Users createUser(Users user) {
        return usersRepository.save(user);
    }

    public Users updateUser(Long id, Users userDetails) {
        Optional<Users> optionalUser = usersRepository.findById(id);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            user.setRoleId(userDetails.getRoleId());
            user.setLastLogin(userDetails.getLastLogin());
            user.setLastIp(userDetails.getLastIp());
            user.setToken(userDetails.getToken());
            user.setLastDeviceId(userDetails.getLastDeviceId());
            return usersRepository.save(user);
        }
        return null;
    }

    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }
}

