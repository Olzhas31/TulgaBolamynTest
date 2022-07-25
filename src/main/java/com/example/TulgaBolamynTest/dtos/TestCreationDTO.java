package com.example.TulgaBolamynTest.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TestCreationDTO {

    private List<TestDTO> testList = new ArrayList<>();

    public void addTest(TestDTO testDTO){
        this.testList.add(testDTO);
    }

    public List<TestDTO> getTestList() {
        return testList;
    }
}
