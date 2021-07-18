package com.learn.rest.webservices.restwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;



@Component
public class UserDaoServices {
	
	private static List<User> users = new ArrayList<User>();
	
	private static int usersCount = 4;
	static {
		users.add(new User(1,"Ad",new Date()));
		users.add(new User(2,"Ada",new Date()));
		users.add(new User(3,"Adb",new Date()));
		users.add(new User(4,"Adc",new Date()));		
		
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user) {
		if(user.getId() == 0) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}
	
	public User findById(int id) {
		for(User i : users) {
			if(i.getId() == id) {
				return i;
			}
		}
		return null;
	}
	public User delete(int id) {
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
	
	

}
