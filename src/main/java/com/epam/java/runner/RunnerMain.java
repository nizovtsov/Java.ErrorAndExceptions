package com.epam.java.runner;

import com.epam.java.actions.StudentAction;
import com.epam.java.actions.UniversityAction;
import com.epam.java.entities.Faculty;
import com.epam.java.entities.Student;
import com.epam.java.entities.SubjectType;
import com.epam.java.entities.University;
import com.epam.java.exceptions.*;
import com.epam.java.services.*;

import java.util.EnumSet;
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

    private static void getAverageGradesOfGroups(University university)
            throws UniversityWithoutFacultiesException, FacultyWithoutGroupsException,
            GroupWithoutStudentsException {
        System.out.println("Средние оценки групп по предмету " + SUBJECT_FOR_TEST);
        System.out.println("M001=" + UniversityAction.getAverageGradeOfGroup(university, "M001", SUBJECT_FOR_TEST));
        System.out.println("M002=" + UniversityAction.getAverageGradeOfGroup(university, "M002", SUBJECT_FOR_TEST));
        System.out.println("T001=" + UniversityAction.getAverageGradeOfGroup(university, "T001", SUBJECT_FOR_TEST));
        System.out.println("T002=" + UniversityAction.getAverageGradeOfGroup(university, "T002", SUBJECT_FOR_TEST));
        System.out.println("T003=" + UniversityAction.getAverageGradeOfGroup(university, "T003", SUBJECT_FOR_TEST));
    }

    private static void getAverageGradeOfUniversity(University university)
            throws UniversityWithoutFacultiesException, FacultyWithoutGroupsException,
            GroupWithoutStudentsException {
        System.out.println("Средняя оценка университета по предмету " + SUBJECT_FOR_TEST);
        System.out.println(UniversityAction.getAverageGradeOfUniversity(university, SUBJECT_FOR_TEST));
    }

    private static void getExceptions(University university)
            throws UniversityWithoutFacultiesException, FacultyWithoutGroupsException,
            GroupWithoutStudentsException, NoFacultyWithNameException, GradeOutOfRangeException, StudentWithoutSubjectException {
        System.out.print("Введите номер сценария с исключением, который необходимо выполнить (1-5): ");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        switch (number) {
            case 1:
                System.out.println("Попытка поставить оценку ниже 0 или выше 10");
                int incorrectGrade = 14;
                Student studentWithGradeOutOfRange = UniversityAction.getStudentsOfUniversity(university).
                        get(STUDENT_ID_FOR_TEST);
                studentWithGradeOutOfRange.setGrade(SubjectType.PROGRAMMING, incorrectGrade);
                System.out.println(studentWithGradeOutOfRange);
                break;
            case 2:
                System.out.println("Отсутсвие предметов у студента (должен быть хотя бы один)");
                Student studentWithoutSubjects = new Student("Any", "Silence",
                        EnumSet.noneOf(SubjectType.class));
                university.getFaculties().get(2).getGroup("T003").setStudent(studentWithoutSubjects);
                System.out.println(StudentAction.getAverageGrades(studentWithoutSubjects));
                break;
            case 3:
                System.out.println("Отсутствие студентов в группе");
                String groupWithoutStudents = "L002";
                university.getFaculties().get(3).setGroup(groupWithoutStudents);
                System.out.println(UniversityAction.getAverageGradeOfGroup(university,
                        groupWithoutStudents, SubjectType.GERMAN));
                break;
            case 4:
                System.out.println("Отсутствие групп на факультете");
                String facultyWithoutGroups = "Instrumental";
                university.setFaculty(new Faculty(facultyWithoutGroups));
                System.out.println(UniversityAction.getAverageGradeOfFaculty(university,
                        facultyWithoutGroups, SubjectType.MECHANICS));
                break;
            case 5:
                System.out.println("Отсутствие факультетов в университете");
                University universityWithoutFaculties = new University();
                System.out.println(UniversityAction.getStudentsOfUniversity(universityWithoutFaculties));
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
            getAverageGradesOfGroups(university);
            getAverageGradeOfUniversity(university);//Посчитать средний балл по предмету для всего университета

            getExceptions(university);
        } catch (StudentWithoutSubjectException | GradeOutOfRangeException |
                 GroupWithoutStudentsException | FacultyWithoutGroupsException |
                 UniversityWithoutFacultiesException | NoFacultyWithNameException e) {
            e.printStackTrace();
        }
    }
}
