package bank.api.bankapi.service;

import bank.api.bankapi.model.dto.ModifyUserDTO;
import bank.api.bankapi.model.User;

import java.util.List;


public interface UserService {
    List<User> getUsers(Integer limit,Integer offset);
    List<User> getAllUsers();
    User createUser(User user);
    User getUserById(long id);
    void deleteUserById(long id);
    void updateUser(ModifyUserDTO user,long id);
    boolean isUserPresent(long id);
}