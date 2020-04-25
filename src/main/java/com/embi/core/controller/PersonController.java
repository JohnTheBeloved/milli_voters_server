package com.embi.core.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.embi.core.exception.ResourceNotFoundException;
import com.embi.core.model.Person;
import com.embi.core.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	@GetMapping("/persons")
	//@CrossOrigin(origins = "http://localhost:5500")
	public List < Person > getAllPersons() {
			return personRepository.findAll();
	}

	@GetMapping("/persons/{id}")
	//@CrossOrigin(origins = "http://localhost:5500")
	public ResponseEntity <Person> getPersonById(@PathVariable(value = "id") Long personId)
	throws ResourceNotFoundException {
			Person person = personRepository.findById(personId)
					.orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));
			return ResponseEntity.ok().body(person);
	}

	@PostMapping("/persons")
	//@CrossOrigin(origins = "http://localhost:5500")
	public Person createPerson(@Valid @RequestBody Person person) {
			return personRepository.save(person);
	}

	@PutMapping("/persons/{id}")
	//@CrossOrigin(origins = "http://localhost:5500")
	public ResponseEntity < Person > updatePerson(@PathVariable(value = "id") Long personId,
			@Valid @RequestBody Person personDetails) throws ResourceNotFoundException {
			Person person = personRepository.findById(personId)
					.orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));

			person.setAge(personDetails.getAge());
			person.setLastName(personDetails.getLastName());
			person.setFirstName(personDetails.getFirstName());
			final Person updatedPerson = personRepository.save(person);
			return ResponseEntity.ok(updatedPerson);
	}

	@DeleteMapping("/persons/{id}")
	//@CrossOrigin(origins = "http://localhost:5500")
	public Map<String, Boolean> deletePerson(@PathVariable(value = "id") Long personId)
	throws ResourceNotFoundException {
			Person person = personRepository.findById(personId)
					.orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));

			personRepository.delete(person);
			Map < String, Boolean > response = new HashMap<> ();
			response.put("deleted", Boolean.TRUE);
			return response;
	}
	
}