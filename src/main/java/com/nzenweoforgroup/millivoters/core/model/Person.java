
package com.nzenweoforgroup.millivoters.core.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;


@Entity
@Table(name = "person")
public class Person {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;
	private String firstName;
	private String lastName;
  private Integer age;
  private String favouriteColour;
  @ElementCollection
  @CollectionTable(name="hobby", joinColumns=@JoinColumn(name = "hobby_id"))
  @Column(name="hobby")
  private List<String> hobby;

  public Person() {

  }

  public Person(String firstName, String lastName, Integer age, String favouriteColour, List<String> hobby) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.favouriteColour = favouriteColour;
    this.hobby = hobby;
  }

  @Column(name = "id", nullable = false)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "first_name", nullable = false)
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Column(name = "last_name", nullable = false)
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Column(name = "age", nullable = false)
  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @Column(name = "favourite_colour", nullable = false)
  public String getFavouriteColour() {
    return favouriteColour;
  }

  public void setFavouriteColour(String favouriteColour) {
    this.favouriteColour = favouriteColour;
  }

  public List<String> getHobby() {
    return hobby;
  }

  public void setHobby(List<String> hobby) {
    this.hobby = hobby;
  }

}