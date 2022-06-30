package com.epam.java.services;

import com.epam.java.entities.University;
import com.epam.java.exceptions.*;

public abstract class Initializer {
    protected University university;

    public Initializer() {
        university = new University();
    }

    protected abstract void setStudents() throws UniversityWithoutFacultiesException,
            FacultyWithoutGroupsException;

    protected abstract void setGroups() throws UniversityWithoutFacultiesException;

    protected abstract void setFaculties();

    protected abstract void setGrades() throws GradeOutOfRangeException,
            StudentWithoutSubjectException, GroupWithoutStudentsException,
            FacultyWithoutGroupsException, UniversityWithoutFacultiesException;

    public University initializeUniversity() throws StudentWithoutSubjectException, GroupWithoutStudentsException, FacultyWithoutGroupsException,UniversityWithoutFacultiesException,GradeOutOfRangeException{
        setFaculties();
        setGroups();
        setStudents();
        setGrades();
        return university;
    }
}
