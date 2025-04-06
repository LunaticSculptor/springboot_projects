package com.example.springboot.learn_spring_boot;

public class Course {
    private long id;
    private String subject;
    private String instructor;

    public Course(long id, String subject, String instructor) {
        this.id = id;
        this.subject = subject;
        this.instructor = instructor;
    }

    public long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getInstructor() {
        return instructor;
    }

//    @Override
//    public String toString() {
//        return "Course{" +
//                "id=" + id +
//                ", subject='" + subject + '\'' +
//                ", instructor='" + instructor + '\'' +
//                '}';
//    }
}
