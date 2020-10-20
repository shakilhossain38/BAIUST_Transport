package com.example.zmns.baiusttransport;

public class StudentModelClass {

    String id, name, dept, level, term, type, busFee;

    public StudentModelClass(String id, String name, String dept, String level, String term, String type, String busFee) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.level = level;
        this.term = term;
        this.type = type;
        this.busFee = busFee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBusFee() {
        return busFee;
    }

    public void setBusFee(String busFee) {
        this.busFee = busFee;
    }
}
