package com.epam.java.runner;

import com.epam.java.actions.StudentAction;
import com.epam.java.actions.UniversityAction;
import com.epam.java.entities.Student;
import com.epam.java.entities.SubjectType;
import com.epam.java.entities.University;
import com.epam.java.exceptions.*;
import com.epam.java.services.*;

public class RunnerMain {
    private final static int STUDENT_ID_FOR_TEST = 10;
    private final static SubjectType SUBJECT_FOR_TEST = SubjectType.TRIGONOMETRY;

    private static void getAverageGradesOfStudent(University university)
            throws UniversityWithoutFacultiesException, FacultyWithoutGroupsException,
            GroupWithoutStudentsException, StudentWithoutSubjectException {
        Student student = UniversityAction.getStudentsOfUniversity(university).get(STUDENT_ID_FOR_TEST);
        System.out.println("Средние оценки студента " + student.getName() + " " + student.getSurname() +
                ", ID " + student.getStudentID());
        System.out.println(StudentAction.getAverageGrades(student));
    }

    private static void getAverageGradesOfFaculties(University university)
            throws UniversityWithoutFacultiesException, NoFacultyWithNameException,
            FacultyWithoutGroupsException, GroupWithoutStudentsException {
        System.out.println("Средние оценки факультетов по предмету " + SUBJECT_FOR_TEST);
        System.out.println("Technical=" + UniversityAction.getAverageGradeOfFaculty(university,
                "Technical", SUBJECT_FOR_TEST));
        System.out.println("Mechanical=" + UniversityAction.getAverageGradeOfFaculty(university,
                "Mechanical", SUBJECT_FOR_TEST));
    }

    public static void main(String[] args) {
        Initializer initializer = new DataInitializer();
        try {
            University university = initializer.initializeUniversity();

            System.out.println("Список студентов в университете:");
            System.out.println(university);

            getAverageGradesOfStudent(university);
            getAverageGradesOfFaculties(university);
            
        } catch (StudentWithoutSubjectException | GradeOutOfRangeException |
                 GroupWithoutStudentsException | FacultyWithoutGroupsException |
                 UniversityWithoutFacultiesException | NoFacultyWithNameException e) {
            e.printStackTrace();
        }
    }
}
