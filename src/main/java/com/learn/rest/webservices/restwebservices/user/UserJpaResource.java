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

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@RestController
public class UserJpaResource {
	@Autowired
	UserJpaRepository userRepository;
	
	@Autowired
	PostJpaRepository postRepository;
	
	@RequestMapping(method=RequestMethod.GET, path="/jpa/users")
	public List<User> retrieveAllUsers(){
		
		return userRepository.findAll();
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id){
		
		java.util.Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("id"+id);
		}
		EntityModel<User> model = EntityModel.of(user.get());
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		model.add(linkToUsers.withRel("all-users"));
		return model;
	}
	
	@PostMapping(path="/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User newUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
		
	}
	@RequestMapping(method=RequestMethod.DELETE, path="/jpa/users/{id}")
	public void deleteUser(@PathVariable int id){
		
		userRepository.deleteById(id);
//		if(user == null) {
//			throw new UserNotFoundException("id"+id);
//		}
	}
	// for getting the mapped post to a single user
	@RequestMapping(method=RequestMethod.GET, path="/jpa/users/{id}/posts")
	public List<Post> retrieveAllUserPosts(@PathVariable int id){
		java.util.Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("id"+id);
		}
		
		return user.get().getPosts();
		
	}
	
	@PostMapping(path="/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
		java.util.Optional<User> useroptional = userRepository.findById(id);
		if(!useroptional.isPresent()) {
			throw new UserNotFoundException("id"+id);
		}
		
		User user = useroptional.get();
		post.setUser(user);
		postRepository.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
		
	}
	
	
	

}
