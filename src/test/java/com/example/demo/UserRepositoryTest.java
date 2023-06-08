package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

//@SpringBootTest
@DataJpaTest
public class UserRepositoryTest {

  @Autowired
  private EmployeeRepository userRepository;

  private Employee user;

  @BeforeEach
  public void setup() {
    // Create a sample user before each test
    user = new Employee("vijaya", "vijaya@example.com");
  }

  @AfterEach
  public void tearDown() {
    // Cleanup after each test
    userRepository.deleteAll();
  }

  @Test
  public void saveUser() {
    Employee savedUser = userRepository.save(user);
    assertNotNull(savedUser.getId());
  }

  @Test
  public void findUserById() {
    Employee savedUser = userRepository.save(user);

    Optional<Employee> optionalUser = userRepository.findById(savedUser.getId());
    assertTrue(optionalUser.isPresent());

    Employee foundUser = optionalUser.get();
    assertEquals(user.getName(), foundUser.getName());
    assertEquals(user.getEmail(), foundUser.getEmail());
  }

  @Test
  public void updateUser() {
    Employee savedUser = userRepository.save(user);

    savedUser.setName("Updated Name");
    savedUser.setEmail("updated@example.com");
  }
}
