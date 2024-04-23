package vn.gvt.ENote.Services;

import java.util.List;
import java.util.Optional;

import vn.gvt.ENote.Models.User;

public interface UserService {

    void save(User user);

    long delete(User user);

    long delete(int id);

    Optional<User> get(int id);

    void update(User user);

    List<User> getAllUsers();

    void defaultSave(User user);

    Optional<User> getByEmail(String email);
}
