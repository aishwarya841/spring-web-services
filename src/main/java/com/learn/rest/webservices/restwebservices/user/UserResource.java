package com.learn.rest.webservices.restwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	
	@Autowired
	UserDaoServices userService;
	
	@RequestMapping(method=RequestMethod.GET, path="users")
	public List<User> retrieveAllUsers(){
		
		return userService.findAll();
	}
	
	@RequestMapping(method=RequestMethod.GET, path="users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id){
		
		User user = userService.findById(id);
		if(user == null) {
			throw new UserNotFoundException("id"+id);
		}
		EntityModel<User> model = EntityModel.of(user);
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		model.add(linkToUsers.withRel("all-users"));
		return model;
	}
	
	@PostMapping(path="/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User newUser = userService.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
		
	}
	@RequestMapping(method=RequestMethod.DELETE, path="users/{id}")
	public void deleteUser(@PathVariable int id){
		
		User user = userService.delete(id);
		if(user == null) {
			throw new UserNotFoundException("id"+id);
		}
	}
	
	
	
	

}
