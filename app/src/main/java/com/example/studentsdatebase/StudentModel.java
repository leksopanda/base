package com.example.studentsdatebase;

public class StudentModel {
    String name, surname, middleName,birthDate, group;

    public StudentModel(String name, String surname, String lastname, String birthDate, String group) {
        this.name = name;
        this.surname = surname;
        this.middleName = lastname;
        this.birthDate = birthDate;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGroup() {
        return "Группа: " + group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
