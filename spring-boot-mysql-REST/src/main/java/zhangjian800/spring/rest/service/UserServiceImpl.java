package zhangjian800.spring.rest.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zhangjian800.spring.rest.model.User;
import zhangjian800.spring.rest.repo.UserRepository;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepo;
     
    public Iterable<User> findAllUsers() {
         return userRepo.findAll();
    }
     
    public User findById(long id) {
        return userRepo.findOne(id);
    }
     
    public User findByName(String name) {
        return userRepo.findByName(name);
    }
     
    public void createUser(User user) {
        userRepo.save(user);
    }
 
    public void updateUser(User user) {
    	userRepo.save(user);
    }
 
    public void deleteUserById(long id) {
    	userRepo.delete(id);
    }
 
    public boolean isUserExist(User user) {
        return findByName(user.getName())!=null;
    }
 
    public void deleteAllUsers() {
        userRepo.deleteAll();
    }
 
}
