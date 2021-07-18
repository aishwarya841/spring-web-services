package com.learn.rest.webservices.restwebservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;


@Entity
public class User {
	
	
	@Id
	@GeneratedValue
	private int id;
	@Past
	private Date birthdate;
	@Size(min=2)  //validation
	private String name;
	
	
	//for mapping the post into the user
	@OneToMany(mappedBy="user")
	private List<Post> posts;
	
	public User() {
		super();
	}
	public User(int id, String name, Date birthdate) {
		super();
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", birthdate=" + birthdate + ", name=" + name + ", posts=" + posts + "]";
	}
	
	
	
}
