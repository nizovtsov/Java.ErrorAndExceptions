package com.epam.java.runner;

import com.epam.java.exceptions.*;
import com.epam.java.services.Initializer;

public class RunnerMain {
    public static void main(String[] args) {
        Initializer initializer = new DataInitializer();
        try{
            // TODO: 6/28/22 add average grades and exceptions 
        } catch (StudentWithoutSubjectException | GradeOutOfRangeException |
                 GroupWithoutStudentsException | FacultyWithoutGroupsException|
                 UniversityWithoutFacultiesException| NoFacultyWithNameException e){
            e.printStackTrace();
        }
    }
}