package com.cdf.factory.ldap.repository;

import javax.naming.Name;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdf.factory.ldap.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Name> {

}