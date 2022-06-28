package com.epam.java.entities;

import com.epam.java.exceptions.UniversityWithoutFacultiesException;

import java.util.ArrayList;

public class University {
    private ArrayList<Faculty> faculties = new ArrayList<>();

    public University() {
    }

    public ArrayList<Faculty> getFaculties() throws UniversityWithoutFacultiesException{
        if(faculties.isEmpty()){
            throw new UniversityWithoutFacultiesException("В университете нет ни одной группы!");
        }
        return faculties;
    }

    public void setFaculty(Faculty faculty){
        faculties.add(faculty);
    }

    @Override
    public String toString() {
        return "University{" +
                "faculties=" + faculties +
                '}';
    }
}
