package com.epam.java.runner;

import com.epam.java.actions.StudentAction;
import com.epam.java.actions.UniversityAction;
import com.epam.java.entities.Student;
import com.epam.java.entities.SubjectType;
import com.epam.java.entities.University;
import com.epam.java.exceptions.*;
import com.epam.java.services.*;

import java.util.Scanner;

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

    private static void getAverageGradeOfGroups(University university)
            throws UniversityWithoutFacultiesException, FacultyWithoutGroupsException,
            GroupWithoutStudentsException {
        System.out.println("Средние оценки групп по предмету " + SUBJECT_FOR_TEST);
        System.out.println("M001=" + UniversityAction.getAverageGradeOfGroup(university, "M001", SUBJECT_FOR_TEST));
        System.out.println("M002=" + UniversityAction.getAverageGradeOfGroup(university, "M001", SUBJECT_FOR_TEST));
        System.out.println("T001=" + UniversityAction.getAverageGradeOfGroup(university, "M001", SUBJECT_FOR_TEST));
        System.out.println("T002=" + UniversityAction.getAverageGradeOfGroup(university, "M001", SUBJECT_FOR_TEST));
        System.out.println("T003=" + UniversityAction.getAverageGradeOfGroup(university, "M001", SUBJECT_FOR_TEST));
    }

    private static void getAverageGradeOfUniversity(University university)
            throws UniversityWithoutFacultiesException, FacultyWithoutGroupsException,
            GroupWithoutStudentsException {
        System.out.println("Средняя оценка университета по предмету "+SUBJECT_FOR_TEST);
        System.out.println(UniversityAction.getAverageGradeOfUniversity(university, SUBJECT_FOR_TEST));
    }

    private static void getExceptions(University university){
        System.out.println("Введите номер сценария с исключением, который необходимо выполнить (1-5): ");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        switch (number){




            Отсутствие факультетов в университете
            case 1://Оценка ниже 0 или выше 10
                break;
            case 2://Отсутсвие предметов у студента (должен быть хотя бы один)
                break;
            case 3://Отсутствие студентов в группе
                break;
            case 4://Отсутствие групп на факультете
                break;
            case 5://Отсутствие факультетов в университете
                break;
            default:
                System.out.println("Введено неверное число!");
                break;
        }
    }

    public static void main(String[] args) {
        Initializer initializer = new DataInitializer();
        try {
            University university = initializer.initializeUniversity();

            System.out.println("Список студентов в университете:");
            System.out.println(university);

            getAverageGradesOfStudent(university);//Посчитать средний балл по всем предметам студента
            //Посчитать средний балл по конкретному предмету в конкретной группе и на конкретном факультете
            getAverageGradesOfFaculties(university);
            getAverageGradeOfGroups(university);
            getAverageGradeOfUniversity(university);//Посчитать средний балл по предмету для всего университета

            getExceptions(university);
        } catch (StudentWithoutSubjectException | GradeOutOfRangeException |
                 GroupWithoutStudentsException | FacultyWithoutGroupsException |
                 UniversityWithoutFacultiesException | NoFacultyWithNameException e) {
            e.printStackTrace();
        }
    }
}
