package com.epam.java.actions;

import com.epam.java.entities.Faculty;
import com.epam.java.entities.Group;
import com.epam.java.entities.Student;
import com.epam.java.exceptions.FacultyWithoutGroupsException;
import com.epam.java.exceptions.GroupWithoutStudentsException;

import java.util.ArrayList;
import java.util.List;

public class FacultyAction {
    public static List<Student> getStudentsOfFaculty(Faculty faculty)
            throws FacultyWithoutGroupsException, GroupWithoutStudentsException {
        ArrayList<Student> students = new ArrayList<>();
        for (Group group : faculty.getGroups()) {
            students.addAll(group.getStudents());
        }
        return students;
    }
}
