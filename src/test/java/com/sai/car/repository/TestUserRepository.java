package com.sai.car.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
public class TestUserRepository {

	@Resource
	UserRepository userRepository;

	@Test
	public void testUserRepository_findByName() {
		assertEquals("pass", userRepository.findByName("test").get().getPassword());
	}

}
