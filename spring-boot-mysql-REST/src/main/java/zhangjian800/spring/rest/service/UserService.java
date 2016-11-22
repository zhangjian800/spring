package zhangjian800.spring.rest.service;

import zhangjian800.spring.rest.model.User;

public interface UserService {
    
    User findById(long id);
     
    User findByName(String name);
     
    void createUser(User user);
     
    void updateUser(User user);
     
    void deleteUserById(long id);
 
    Iterable<User> findAllUsers(); 
     
    void deleteAllUsers();
     
    public boolean isUserExist(User user);
     
}