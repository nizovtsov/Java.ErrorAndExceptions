package com.epam.java.actions;

import com.epam.java.entities.Student;
import com.epam.java.entities.SubjectType;

import java.util.List;

public class AverageCalculation {
    public static Double calculateAverageGrade(List<Student> students, SubjectType subjectType){
        List<Integer> subjectGrades = students.stream().filter(student -> student.isStudySubject(subjectType)).
                map(student -> StudentAction.)
    }
}
