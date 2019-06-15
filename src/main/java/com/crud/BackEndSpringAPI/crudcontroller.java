package com.crud.BackEndSpringAPI;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
//@CrossOrigin(origins = "http://localhost:4216", maxAge = 3600 )
@CrossOrigin(origins ="*" , allowedHeaders = "*")
@RequestMapping("/api")
public class crudcontroller {

	@Autowired
	private crudrepo repo;
	//private ISpecimenService specimenService; 
	
	
//Adding user
	@PostMapping("/user")
	public ResponseEntity<Object> getAll(@Valid @RequestBody User user) {

		User Rep = repo.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Rep.getId())
				.toUri();
		return ResponseEntity.created(location).build();

	}
//Get a user
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return repo.findAll();
	}
//Get a user by ID
	@GetMapping("/user/{id}")
	public User retrieveUser(@PathVariable int id) throws Exception {
		Optional<User> user = repo.findById(id);

		if (!user.isPresent())
			throw new Exception("id-" + id + " does not exist");

		return user.get();
	}
//Delete a user by ID
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable int id) {
		repo.deleteById(id);
	}
//Update a user at this ID
	@PutMapping("/user/{id}")
	public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable int id) {

		Optional<User> userOptional = repo.findById(id);

		if (!userOptional.isPresent())
			return ResponseEntity.notFound().build();

		user.setId(id);

		repo.save(user);

		return ResponseEntity.noContent().build();
	}
	
	
//	@PostMapping("/uploadImage")
//	public String uploadImage(@ RequestParam ("imagefile") MultipartFile imageFile) throws Exception {
//		String returnedValue = " ";
//		specimenService.saveImage(imageFile);
//		return returnedValue;
//	}
}
