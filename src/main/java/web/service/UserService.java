package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    public void addUser(User user);
    public boolean updateUserById(long id, User user);
    public boolean deleteUserById(long id);
    public User getUserById(long id);
    public List<User> getUsers();
}
