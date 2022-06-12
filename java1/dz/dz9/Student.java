package java1.dz.dz9;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private List<Course> allCourses = new ArrayList<>();

    public Student(String name, List<Course> allCourses) {
        this.name = name;
        this.allCourses = allCourses;
    }

    public String getName() {
        return name;
    }

    public List<Course> getAllCourses() {
        return allCourses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", allCourses=" + allCourses +
                '}';
    }
}
