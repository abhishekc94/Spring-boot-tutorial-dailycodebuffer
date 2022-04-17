package com.dailycodebuffer.Springboottutorial.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.dailycodebuffer.Springboottutorial.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
            .departmentName("Electronics Department")
            .departmentCode("ECE")
            .departmentAddress("Tiptur")
            .build();

        testEntityManager.persist(department);
    }

    @Test
    public void whenFindById_thenReturnDepartment() {
        Department department = departmentRepository.findById(1l).get();
        assertEquals("Electronics Department",department.getDepartmentName());
    }
}