package zhangjian800.spring.rest.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import zhangjian800.spring.rest.model.User;
import zhangjian800.spring.rest.service.UserService;

@RestController
public class UserResourceController {
	
    private static Logger logger = LoggerFactory.getLogger(UserResourceController.class);

	@Autowired
	UserService userService; // Service which will do all data retrieval/manipulation work

	// -------------------Retrieve All Users--------------------------------------------------------
	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<Iterable<User>> listAllUsers() {
		Iterable<User> users = userService.findAllUsers();
		if (users == null) {
			return new ResponseEntity<Iterable<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Iterable<User>>(users, HttpStatus.OK);
	}

	// -------------------Retrieve Single User----------------------------
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("id") long id) {
		logger.info("Fetching User with id " + id);
		User user = userService.findById(id);
		if (user == null) {
			logger.error("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// -------------------Create a User--------------------------------------------------------
	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User " + user.getName());

		if (userService.isUserExist(user)) {
			logger.error("A User with name " + user.getName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		userService.createUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// ------ Update a User------------------------
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		logger.info("Updating User " + id);

		User currentUser = userService.findById(id);

		if (currentUser == null) {
			logger.error("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		currentUser.setName(user.getName());

		userService.updateUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	// ------------------- Delete a User----------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting User with id " + id);

		User user = userService.findById(id);
		if (user == null) {
			logger.error("Unable to delete. User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	// -------Delete All Users------------------------
	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers() {
		logger.info("Deleting All Users");

		userService.deleteAllUsers();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

}