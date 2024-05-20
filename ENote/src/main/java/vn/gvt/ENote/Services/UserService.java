package vn.gvt.ENote.Services;

import java.util.List;
import java.util.Optional;

import vn.gvt.ENote.Models.User;

public interface UserService {

    void save(User user);

    int delete(User user);

    int delete(int id);

    Optional<User> get(int id);

    void update(Integer id, User user);
    
    void changePassword(User user, String password);

    List<User> getAllUsers();

    void defaultSave(User user);

    Optional<User> getByEmail(String email);
}