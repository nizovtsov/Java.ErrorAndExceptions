package com.epam.java.entities;

import com.epam.java.exceptions.GradeOutOfRangeException;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;

public class Grades {
    private EnumMap<SubjectType, List<Integer>> subjectGrades = new EnumMap<>(SubjectType.class);

    public Grades(EnumSet<SubjectType> subjectTypes) {
        for (SubjectType subjectType : subjectTypes) {
            subjectGrades.put(subjectType, new ArrayList<>());
        }
    }

    public EnumMap<SubjectType, List<Integer>> getSubjectGrades() {
        return subjectGrades;
    }

    public void addSubject(SubjectType subjectType) {
        if (!subjectGrades.containsKey(subjectType)) {
            subjectGrades.put(subjectType, new ArrayList<>());
        }
    }

    public void setGrade(SubjectType subjectType, int grade)
            throws GradeOutOfRangeException {
        if ((grade >= 0) && (grade <= 10)) {
            List<Integer> grades = subjectGrades.get(subjectType);
            grades.add(grade);
            subjectGrades.put(subjectType, grades);
        } else {
            throw new GradeOutOfRangeException("Оценка " + grade + " недопустима!");
        }
    }

    @Override
    public String toString() {
        return subjectGrades.toString();
    }
}
