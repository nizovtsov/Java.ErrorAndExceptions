package com.epam.java.actions;

import com.epam.java.entities.Faculty;
import com.epam.java.entities.Student;
import com.epam.java.entities.SubjectType;
import com.epam.java.entities.University;
import com.epam.java.exceptions.FacultyWithoutGroupsException;
import com.epam.java.exceptions.GroupWithoutStudentsException;
import com.epam.java.exceptions.NoFacultyWithNameException;
import com.epam.java.exceptions.UniversityWithoutFacultiesException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UniversityAction {
    public static List<Student> getStudentsOfUniversity(University university)
            throws UniversityWithoutFacultiesException, FacultyWithoutGroupsException, GroupWithoutStudentsException {
        ArrayList<Student> students = new ArrayList<>();
        for (Faculty faculty : university.getFaculties()) {
            students.addAll(FacultyAction.getStudentsOfFaculty(faculty));
        }
        return students;
    }

    public static double getAverageGradeOfFaculty(University university, String facultyName, SubjectType subjectType)
            throws UniversityWithoutFacultiesException, NoFacultyWithNameException,
            FacultyWithoutGroupsException, GroupWithoutStudentsException {
        Faculty facultyByName;
        Optional<Faculty> optionalFaculty = university.getFaculties().
                stream().filter(faculty -> faculty.getFacultyName().
                        equals(facultyName)).findFirst();
        if (optionalFaculty.isPresent()) {
            facultyByName = optionalFaculty.get();
        } else {
            throw new NoFacultyWithNameException("В университете не существует факультета с именем " + facultyName);
        }
        return FacultyAction.getAverageGradeBySubject(facultyByName, subjectType);
    }
}
