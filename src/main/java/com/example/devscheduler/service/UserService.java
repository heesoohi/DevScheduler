package com.example.devscheduler.service;

import com.example.devscheduler.dto.UserRequestDto;
import com.example.devscheduler.dto.UserResponseDto;
import com.example.devscheduler.entity.User;
import com.example.devscheduler.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto saveUser(UserRequestDto dto) {
        User user = new User(dto.getUsername(), dto.getEmail());
        User savedUser = userRepository.save(user);
        return new UserResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> dtos = new ArrayList<>();
        for (User user : users) {
            dtos.add(new UserResponseDto(user.getId(), user.getUsername(), user.getEmail()));
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public UserResponseDto findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User with id " + id + " does not exist")
        );
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail());
    }

    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto dto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User with id " + id + " does not exist")
        );
        user.update(dto);
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail());
    }

    @Transactional
    public void deleteUserById(Long id) {
        if(!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User with id " + id + " does not exist");
        }
        userRepository.deleteById(id);
    }
}
