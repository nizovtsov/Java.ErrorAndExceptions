package com.epam.java.runner;

import com.epam.java.entities.University;
import com.epam.java.exceptions.*;
import com.epam.java.services.*;

public class RunnerMain {
    public static void main(String[] args) {
        Initializer initializer = new DataInitializer();
        try{
            University university = initializer.initializeUniversity();

            System.out.println(university);
        } catch (StudentWithoutSubjectException | GradeOutOfRangeException |
                 GroupWithoutStudentsException | FacultyWithoutGroupsException|
                 UniversityWithoutFacultiesException/*| NoFacultyWithNameException*/ e){
            e.printStackTrace();
        }
    }
}
