package com.example.knu_info.data;

public class LectureListItemData {

    String lecId;
    String lecLocation;
    String grade;
    String className;
    String professor;
    String lecGrade;
    String personnel;
    String lecTime;
    String actTime;
    public LectureListItemData(String lecId,String lecLocation,String grade, String className, String professor,
                               String lecGrade, String personnel, String lecTime, String actTime){
        this.lecId=lecId;
        this.lecLocation=lecLocation;
        this.grade = grade;
        this.className = className;
        this.professor = professor;
        this.lecGrade = lecGrade;
        this.personnel = personnel;
        this.lecTime = lecTime;
        this.actTime=actTime;

    }
    public String getLecId() {
        return lecId;
    }

    public void setLecId(String lecId) {
        this.lecId = lecId;
    }

    public String getLecLocation() {
        return lecLocation;
    }

    public void setLecLocation(String lecLocation) {
        this.lecLocation = lecLocation;
    }
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getLecGrade() {
        return lecGrade;
    }

    public void setLecGrade(String lecGrade) {
        this.lecGrade = lecGrade;
    }

    public String getPersonnel() {
        return personnel;
    }

    public void setPersonnel(String personnel) {
        this.personnel = personnel;
    }

    public String getLecTime() {
        return lecTime;
    }

    public void setLecTime(String lecTime) {
        this.lecTime = lecTime;
    }

    public String getActTime() {
        return actTime;
    }

    public void setActTime(String actTime) {
        this.actTime = actTime;
    }
}
