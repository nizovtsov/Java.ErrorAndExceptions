package com.epam.java.actions;

import com.epam.java.entities.Faculty;
import com.epam.java.entities.Student;
import com.epam.java.entities.University;
import com.epam.java.exceptions.FacultyWithoutGroupsException;
import com.epam.java.exceptions.GroupWithoutStudentsException;
import com.epam.java.exceptions.UniversityWithoutFacultiesException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UniversityAction {
    public static List<Student> getStudentsOfUniversity(University university)
            throws UniversityWithoutFacultiesException, FacultyWithoutGroupsException, GroupWithoutStudentsException {
        ArrayList<Student> students = new ArrayList<>();
        for(Faculty faculty: university.getFaculties()){
            students.addAll(FacultyAction.getStudentsOfFaculty(faculty));
        }
        return students;
    }
}
