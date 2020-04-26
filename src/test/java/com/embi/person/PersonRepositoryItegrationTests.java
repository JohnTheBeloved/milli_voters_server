package com.nzenweoforgroup.millivoters.person;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.nzenweoforgroup.millivoters.core.MilliVotersApp;
import com.nzenweoforgroup.millivoters.core.model.Person;
import com.nzenweoforgroup.millivoters.core.repository.PersonRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MilliVotersApp.class)
@AutoConfigureTestEntityManager
class PersonRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private PersonRepository personRepository;

	@Test
	@Transactional
	public void whenFindById_thenReturnPerson() {
		// given
		List<String> hobbies = new ArrayList<>();
		hobbies.add("Singing");
		Person john = new Person("John", "Alade", 23, "red", hobbies);
		entityManager.persist(john);
		entityManager.flush();

		// when
		Optional<Person> found = personRepository.findById(john.getId());
 
    // then
    assertThat(found.get().getFirstName())
      .isEqualTo(john.getFirstName());
}


@Test
@Transactional
public void whenFindAll_thenReturnPersons() {
	// given
	List<String> hobbies = new ArrayList<>();
	hobbies.add("Singing");
	Person john = new Person("John", "Alade", 23, "red", hobbies);
	entityManager.persist(john);
	entityManager.flush();

	// when
	List<Person> found = personRepository.findAll();

	// then
	assertThat(found.size())
		.isEqualTo(1);
}


@Test
@Transactional
public void whenDelete_thenReturnNoPersons() {
	// given
	List<String> hobbies = new ArrayList<>();
	hobbies.add("Singing");
	Person john = new Person("John", "Alade", 23, "red", hobbies);
	entityManager.persist(john);
	entityManager.flush();

	// when
	personRepository.delete(john);
	List<Person> found = personRepository.findAll();

	// then
	assertThat(found.size())
		.isEqualTo(0);
}


@Test
@Transactional
public void whenUpdateName_thenReturnNewName() {
	// given
	List<String> hobbies = new ArrayList<>();
	hobbies.add("Singing");
	Person john = new Person("John", "Alade", 23, "red", hobbies);
	entityManager.persist(john);
	entityManager.flush();

	String newName = "Johnny";
	john.setFirstName(newName);
	personRepository.saveAndFlush(john);
	// when;
	Optional<Person> found = personRepository.findById(john.getId());

	// then
	assertThat(found.get().getFirstName())
		.isEqualTo(newName);
}



}
