package com.epam.java.actions;

import com.epam.java.entities.Group;
import com.epam.java.entities.SubjectType;
import com.epam.java.exceptions.GroupWithoutStudentsException;

public class GroupAction extends AverageCalculation{
    public static double getAverageGradeBySubject(Group group, SubjectType subjectType)
            throws GroupWithoutStudentsException {
        return calculateAverageGrade(group.getStudents(), subjectType);
    }
}
