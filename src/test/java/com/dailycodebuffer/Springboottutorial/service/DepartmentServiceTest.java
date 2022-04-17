package com.dailycodebuffer.Springboottutorial.service;

import static org.junit.jupiter.api.Assertions.*;

import com.dailycodebuffer.Springboottutorial.entity.Department;
import com.dailycodebuffer.Springboottutorial.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder()
            .departmentId(1l)
            .departmentCode("IT")
            .departmentAddress("Bangalore")
            .departmentName("Software")
            .build();
    }

    @Test
    @DisplayName("Get Data based on department name")
    public void getDepartmentByNameTest() {
        String departmentName = "IT";
        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase(departmentName)).thenReturn(department);
        Department department = departmentService.getDepartmentByName("IT");
        assertEquals(department.getDepartmentCode(),departmentName);
    }
}