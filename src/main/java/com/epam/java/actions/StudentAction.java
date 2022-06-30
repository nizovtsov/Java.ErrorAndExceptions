package com.epam.java.actions;

import com.epam.java.entities.Student;
import com.epam.java.entities.SubjectType;
import com.epam.java.exceptions.StudentWithoutSubjectException;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;

public class StudentAction extends AverageCalculation {
    public static List<Integer> getGradesBySubject(Student student, SubjectType subjectType) {
        return student.getGrades().getSubjectGrades().get(subjectType);
    }

    public static EnumMap<SubjectType, Double> getAverageGrades(Student student) throws StudentWithoutSubjectException {
        EnumMap<SubjectType, Double> averageGrades = new EnumMap<>(SubjectType.class);
        for (SubjectType subjectType : student.getSubjectTypes()) {
            averageGrades.put(subjectType, getAverageGradesBySubject(student, subjectType));
        }
        return averageGrades;
    }

    public static double getAverageGradesBySubject(Student student, SubjectType subjectType) {
        LinkedList<Student> studentList = new LinkedList<>();
        studentList.add(student);
        return calculateAverageGrade(studentList, subjectType);
    }
}
