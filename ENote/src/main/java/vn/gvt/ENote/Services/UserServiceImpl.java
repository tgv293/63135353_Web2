package vn.gvt.ENote.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import vn.gvt.ENote.Models.User;
import vn.gvt.ENote.Models.UserRole;
import vn.gvt.ENote.Repositories.UserRepository;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }

        userRepository.save(user);
    }

    @Override
    public long delete(User user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }

        Integer userId = user.getId();
        if (userId == null || userId < 1) {
            throw new IllegalArgumentException(String.format("Can't delete user with id equals null or less than 1. Id = %d", userId));
        }

        userRepository.delete(user);
        return userId;
    }

    @Override
    public long delete(int id) {
        if (id < 1) {
            throw new IllegalArgumentException();
        }

        userRepository.deleteById(id);
        return id;
    }

    @Override
    public Optional<User> get(int id) {
        if (id < 1) {
            throw new IllegalArgumentException();
        }

        return userRepository.findById(id);
    }

    @Override
    public void update(User user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }

        Integer userId = user.getId();
        if (userId == null || userId < 1) {
            throw new IllegalArgumentException(String.format("Can't find user with id equals null or less than 1, updating denied. Id = %d", userId));
        }

        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void defaultSave(User user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }

        UserRole userRole = new UserRole();
        userRole.setId(1);
        userRole.setRole("User");

        user.setRole(userRole);
        user.setRegistration(LocalDate.now());

        userRepository.save(user);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException();
        }

        return userRepository.findByEmail(email);
    }
}
