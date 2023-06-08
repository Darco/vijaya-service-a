package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
@DataJpaTest
public class EmployeeRepositoryTests {
  @Autowired
  private EmployeeRepository employeeRepository;

  private Employee employee;

  @BeforeEach
  public void setup() {
    // Create a sample user before each test
    employee = new Employee("John Doe", "john@example.com");
  }

  @AfterEach
  public void tearDown() {
    // Cleanup after each test
    employeeRepository.deleteAll();
  }

  @Test
  public void saveEmployee() {
    Employee savedEmployee = employeeRepository.save(employee);
    assertNotNull(savedEmployee.getId());
  }

  @Test
  public void findEmployeeById() {
    Employee savedEmployee = employeeRepository.save(employee);

    Optional<Employee> optionalEmployee = employeeRepository.findById(savedEmployee.getId());
    assertTrue(optionalEmployee.isPresent());

    Employee foundEmployee = optionalEmployee.get();
    assertEquals(employee.getName(), foundEmployee.getName());
    assertEquals(employee.getEmail(), foundEmployee.getEmail());
  }

  @Test
  public void updateEmployee() {
    Employee savedEmployee = employeeRepository.save(employee);
    savedEmployee.setName("Updated Name");
    savedEmployee.setEmail("updated@example.com");

    Employee updatedEmployee = employeeRepository.save(savedEmployee);
    assertEquals(savedEmployee.getId(), updatedEmployee.getId());
    assertEquals(savedEmployee.getName(), updatedEmployee.getName());
    assertEquals(savedEmployee.getEmail(), updatedEmployee.getEmail());
  }

  @Test
  public void deleteEmployee() {
    Employee savedEmployee = employeeRepository.save(employee);

    employeeRepository.delete(savedEmployee);

    assertFalse(employeeRepository.existsById(savedEmployee.getId()));
  }
}
