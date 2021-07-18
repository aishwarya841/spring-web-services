package com.learn.rest.webservices.restwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//controller
@RestController
public class HelloWorldController {
	
	@Autowired
	MessageSource source;
	
	//Get Method
	//URI
	@RequestMapping(method=RequestMethod.GET, path="/hello-world")
	public String hello() {
		return "Hello World";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/hello-world-bean")
	public HelloWorldBean helloWorld() {
		return new HelloWorldBean("Hello World");
	}
	
	//path variable
	@RequestMapping(method=RequestMethod.GET, path="/hello-world-bean/path-variable/{name}")
	public HelloWorldBean helloWorldBean(@PathVariable String name) {
		return new HelloWorldBean(String.format("hello world ,%s", name));
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/hello-world-internationalized")
	public String helloInternationalized(
			//@RequestHeader(name="Accept-Language", required=false) Locale locale
			) {
		
		return source.getMessage("good.morning.message", null,"Default Message", LocaleContextHolder.getLocale());
		
	}

}
