package com.epam.java.services;

import com.epam.java.entities.Faculty;
import com.epam.java.entities.Group;
import com.epam.java.entities.Student;
import com.epam.java.entities.SubjectType;
import com.epam.java.exceptions.*;

import java.util.EnumSet;

public class DataInitializer extends Initializer {

    @Override
    protected void setFaculties() {
        university.setFaculty(new Faculty("Economical"));
        university.setFaculty(new Faculty("Mechanical"));
        university.setFaculty(new Faculty("Technical"));
        university.setFaculty(new Faculty("Linguistic"));
    }

    @Override
    protected void setGroups() throws UniversityWithoutFacultiesException {
        university.getFaculties().get(0).setGroup("E001");
        university.getFaculties().get(0).setGroup("E002");
        university.getFaculties().get(1).setGroup("M001");
        university.getFaculties().get(1).setGroup("M002");
        university.getFaculties().get(2).setGroup("T001");
        university.getFaculties().get(2).setGroup("T002");
        university.getFaculties().get(2).setGroup("T003");
        university.getFaculties().get(3).setGroup("L001");
    }

    @Override
    protected void setStudents() throws UniversityWithoutFacultiesException, FacultyWithoutGroupsException {
        university.getFaculties().get(0).getGroup("E001").setStudent(new Student("Ivan",
                "Ivanov", EnumSet.of(SubjectType.ENGLISH, SubjectType.GERMAN,
                SubjectType.ECONOMICS, SubjectType.ALGEBRA)));
        university.getFaculties().get(0).getGroup("E001").setStudent(new Student("Irina",
                "Filantropova", EnumSet.of(SubjectType.ENGLISH, SubjectType.GERMAN,
                SubjectType.ECONOMICS, SubjectType.ALGEBRA)));
        university.getFaculties().get(0).getGroup("E002").setStudent(new Student("Igor",
                "Petrov", EnumSet.of(SubjectType.ENGLISH, SubjectType.GERMAN,
                SubjectType.ECONOMICS, SubjectType.GEOMETRY)));

        university.getFaculties().get(1).getGroup("M001").setStudent(new Student("Artem",
                "Logvinov", EnumSet.of(SubjectType.ENGLISH, SubjectType.MECHANICS,
                SubjectType.GEOMETRY, SubjectType.ALGEBRA)));
        university.getFaculties().get(1).getGroup("M002").setStudent(new Student("Oleg",
                "Nefedov", EnumSet.of(SubjectType.ENGLISH, SubjectType.MECHANICS,
                SubjectType.GEOMETRY, SubjectType.TRIGONOMETRY)));
        university.getFaculties().get(1).getGroup("M002").setStudent(new Student("Fedor",
                "Golovin", EnumSet.of(SubjectType.ENGLISH, SubjectType.MECHANICS,
                SubjectType.GEOMETRY, SubjectType.TRIGONOMETRY)));

        university.getFaculties().get(2).getGroup("T001").setStudent(new Student("Taras",
                "Antonov", EnumSet.of(SubjectType.ENGLISH, SubjectType.ELECTRONICS,
                SubjectType.GEOMETRY, SubjectType.ALGEBRA)));
        university.getFaculties().get(2).getGroup("T001").setStudent(new Student("Oleg",
                "Kirilov", EnumSet.of(SubjectType.ENGLISH, SubjectType.ELECTRONICS,
                SubjectType.GEOMETRY, SubjectType.ALGEBRA)));
        university.getFaculties().get(2).getGroup("T002").setStudent(new Student("Ivan",
                "Tarasov", EnumSet.of(SubjectType.ENGLISH, SubjectType.ELECTRONICS,
                SubjectType.GEOMETRY, SubjectType.ALGEBRA, SubjectType.TRIGONOMETRY)));
        university.getFaculties().get(2).getGroup("T003").setStudent(new Student("Marina",
                "Nikitina", EnumSet.of(SubjectType.ENGLISH, SubjectType.PROGRAMMING,
                SubjectType.TRIGONOMETRY)));
        university.getFaculties().get(2).getGroup("T003").setStudent(new Student("Ivan",
                "Nesterov", EnumSet.of(SubjectType.ENGLISH, SubjectType.PROGRAMMING,
                SubjectType.TRIGONOMETRY)));

        university.getFaculties().get(3).getGroup("L001").setStudent(new Student("Olga",
                "Semenova", EnumSet.of(SubjectType.ENGLISH, SubjectType.GERMAN,
                SubjectType.ENGLISH_LITERATURE, SubjectType.GERMAN_LITERATURE)));
    }


    @Override
    protected void setGrades() throws GradeOutOfRangeException,
            StudentWithoutSubjectException, GroupWithoutStudentsException,
            FacultyWithoutGroupsException, UniversityWithoutFacultiesException {
        for (Faculty faculty : university.getFaculties()) {
            for (Group group : faculty.getGroups()) {
                for (Student student : group.getStudents()) {
                    for (SubjectType studentSubject : student.getSubjectTypes()) {
                        int countGrades = (int) (Math.random() * 4);
                        for (int i = 0; i < countGrades; i++) {
                            student.setGrade(studentSubject, (int) ((Math.random() * 10) + 1));
                        }
                    }
                }
            }
        }
    }
}
