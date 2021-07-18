package com.learn.rest.webservices.restwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersionController {

	@GetMapping("/v1/person")
	public PersonV1 person() {
		return new PersonV1("Aishwarya Dubey");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 person2() {
		return new PersonV2(new Name("Aishwarya", "Dubey"));
	}
}
