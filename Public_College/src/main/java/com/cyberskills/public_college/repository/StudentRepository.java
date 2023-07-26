package com.cyberskills.public_college.repository;

import com.cyberskills.public_college.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class StudentRepository {
    private static Map<Integer, Student> students = new ConcurrentHashMap<>();

    static {
        students.put(1, new Student(1,"Shamim", "shamim@gmail.com", true));
        students.put(2, new Student(2,"Hasan", "hasan@gmail.com", true));

        students.put(3, new Student(3,"Shamim Hasan", "shamim.hasan@gmail.com", true));
    }


    public List<Student> readAll() {
        List<Student> allStudents = new ArrayList<>(students.values());
        allStudents.sort(Comparator.comparing(Student::getId));
        return allStudents;
    }

    public void create(Student student) {
        students.put(student.getId(), student);
    }

    public Student read(Integer id) {
        return students.get(id);
    }

    public void update(Student student) {
        students.put(student.getId(), student);
    }

    public void delete(Integer id) {
        students.remove(id);
    }
}