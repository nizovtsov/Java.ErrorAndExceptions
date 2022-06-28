package com.epam.java.exceptions;

public class StudentWithoutSubjectException extends Exception{
    public StudentWithoutSubjectException(){}

    public StudentWithoutSubjectException(String message){
        super(message);
    }

    public StudentWithoutSubjectException(String message, Throwable cause){
        super(message, cause);
    }

    public StudentWithoutSubjectException(Throwable cause){
        super(cause);
    }
}
