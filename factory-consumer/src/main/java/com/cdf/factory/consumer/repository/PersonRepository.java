package com.cdf.factory.consumer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdf.factory.consumer.domain.Person;

/**
 * Created on 2017/11/7.
 *
 * @author zlf
 * @since 1.0
 */
public interface PersonRepository extends JpaRepository<Person, Integer> {

	List<Person> findByAge(Integer age);
}
