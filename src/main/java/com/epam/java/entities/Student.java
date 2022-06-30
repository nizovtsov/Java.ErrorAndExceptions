package com.epam.java.entities;

import com.epam.java.exceptions.GradeOutOfRangeException;
import com.epam.java.exceptions.StudentWithoutSubjectException;

import java.util.EnumSet;
import java.util.Objects;

public class Student {
    private static int countStudents = 0;
    private String name;
    private String surname;
    private int studentID;

    private EnumSet<SubjectType> subjectTypes;
    private Grades grades;

    public Student(String name, String surname, EnumSet<SubjectType> subjectTypes) {
        this.name = name;
        this.surname = surname;
        this.subjectTypes = subjectTypes;
        grades = new Grades(this.subjectTypes);
        studentID = countStudents;
        countStudents++;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Grades getGrades() {
        return grades;
    }

    public void setGrade(SubjectType subject, int grade) throws GradeOutOfRangeException, StudentWithoutSubjectException {
        if(!getSubjectTypes().contains(subject)){
            setSubjectType(subject);
        }
        grades.setGrade(subject, grade);
    }

    public EnumSet<SubjectType> getSubjectTypes() throws StudentWithoutSubjectException {
        if (subjectTypes.isEmpty()) {
            throw new StudentWithoutSubjectException("У студента " + getName() + " "
                    + getSurname() + " с номером студенческого билета " + getStudentID()
                    + " отсутствуют предметы!");
        }
        return subjectTypes;
    }

    public boolean isStudySubject(SubjectType subjectType){
        return subjectTypes.contains(subjectType);
    }

    public void setSubjectType(SubjectType subject){
        subjectTypes.add(subject);
        grades.addSubject(subject);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (studentID != student.studentID) return false;
        if (!Objects.equals(name, student.name)) return false;
        if (!Objects.equals(surname, student.surname)) return false;
        if (!Objects.equals(subjectTypes, student.subjectTypes))
            return false;
        return Objects.equals(grades, student.grades);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + studentID;
        result = 31 * result + (subjectTypes != null ? subjectTypes.hashCode() : 0);
        result = 31 * result + (grades != null ? grades.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", studentID=" + studentID +
                ", grades=" + grades +
                '}';
    }
}
