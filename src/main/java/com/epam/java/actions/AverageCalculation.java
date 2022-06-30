package com.epam.java.actions;

import com.epam.java.entities.Student;
import com.epam.java.entities.SubjectType;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AverageCalculation {
    public static double calculateAverageGrade(List<Student> students, SubjectType subjectType) {
        List<Integer> subjectGrades = students.stream().filter(student -> student.isStudySubject(subjectType)).
                map(student -> StudentAction.getGradesBySubject(student, subjectType)).flatMap(Collection::stream).
                collect(Collectors.toList());

        Integer sumOfGrades = subjectGrades.stream().reduce(Integer::sum).orElse(0);
        return !subjectGrades.isEmpty() ? round((sumOfGrades / (double) subjectGrades.size()), 1) : sumOfGrades;
    }

    private static double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}
