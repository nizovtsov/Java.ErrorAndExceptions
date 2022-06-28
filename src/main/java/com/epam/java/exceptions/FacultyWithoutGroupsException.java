package com.epam.java.exceptions;

public class FacultyWithoutGroupsException extends Exception{
    public FacultyWithoutGroupsException(){}

    public FacultyWithoutGroupsException(String message){
        super(message);
    }

    public FacultyWithoutGroupsException(String message, Throwable cause){
        super(message, cause);
    }

    public FacultyWithoutGroupsException(Throwable cause){
        super(cause);
    }
}
