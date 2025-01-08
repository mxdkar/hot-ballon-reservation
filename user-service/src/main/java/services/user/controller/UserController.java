package services.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.user.model.User;
import services.user.repository.UserRepository;

import java.util.List;

@RestController
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserRepository repository;
	
	@PostMapping("/")
	public ResponseEntity<User> add(@RequestBody User user) {
		LOGGER.info("User add: {}", user);
		return ResponseEntity.ok(repository.save(user));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable("id") Long id) {
		LOGGER.info("User find: id={}", id);
		return repository.findById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping("/")
	public List<User> findAll() {
		LOGGER.info("User find");
		return repository.findAll();
	}
	
}
