package zhangjian800.spring.rest.repo;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import zhangjian800.spring.rest.model.User;

@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

	  /**
	   * Return the user having the passed email or null if no user is found.
	   * 
	   * @param email the user email.
	   */
	  public User findByEmail(String email);
	  
	  /**
	   * Return the user having the passed name or null if no user is found.
	   * 
	   * @param name the user name.
	   */
	  public User findByName(String name);

}
