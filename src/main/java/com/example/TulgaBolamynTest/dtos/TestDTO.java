package com.example.TulgaBolamynTest.dtos;

import com.example.TulgaBolamynTest.domains.Question;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestDTO {

    private Long id;
    private String quest;
    private String option0;
    private String option1;
    private String option2;
    private String option3;

    private String selectedOption;

    public TestDTO(){}

    public TestDTO(Question question){
        this.id = question.getId();
        this.quest = question.getQuest();
        this.option0 = question.getOption0();
        this.option1 = question.getOption1();
        this.option2 = question.getOption2();
        this.option3 = question.getOption3();
    }
}
