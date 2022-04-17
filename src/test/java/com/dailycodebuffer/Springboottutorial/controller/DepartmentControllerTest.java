package com.dailycodebuffer.Springboottutorial.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.dailycodebuffer.Springboottutorial.entity.Department;
import com.dailycodebuffer.Springboottutorial.error.DepartmentNotFoundException;
import com.dailycodebuffer.Springboottutorial.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder()
            .departmentAddress("Bangalore")
            .departmentCode("ECE")
            .departmentName("Electronics and Communication")
            .departmentId(1l)
            .build();
    }

    @Test
    void saveDepartment() throws Exception {
        Department inputDepartment = Department.builder()
            .departmentAddress("Bangalore")
            .departmentCode("ECE")
            .departmentName("Electronics and Communication")
            .build();
        Mockito.when(departmentService.saveDepartment(inputDepartment)).thenReturn(department);
        mockMvc.perform(post("/departments")
            .contentType(MediaType.APPLICATION_JSON).content("{\n" + "\t\"departmentName\":\"Electronics and Communication\",\n"
                + "\t\"departmentAddress\":\" Bangalore\",\n" + "\t\"departmentCode\":\"ECE\"\n" + "}"))
                .andExpect(status().isOk());
    }

    @Test
    void getDepartmentById() throws Exception {
        Mockito.when(departmentService.getDepartmentById(1L)).thenReturn(department);
        mockMvc.perform(get("/departments/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.departmentName").value(department.getDepartmentName()));
    }
}