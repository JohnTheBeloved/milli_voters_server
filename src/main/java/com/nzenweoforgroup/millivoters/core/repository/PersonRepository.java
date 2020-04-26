package com.nzenweoforgroup.millivoters.core.repository;

import com.nzenweoforgroup.millivoters.core.model.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}