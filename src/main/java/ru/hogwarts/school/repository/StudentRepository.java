package ru.hogwarts.school.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findStudentByNameContainingIgnoreCase(String name); // Ищем студента в Хогвартсе по имени или его части, без учета регистра

    Collection<Student> findAllStudentsByAgeBetween(int age, int age2); // Ищем всех студентов по возрасту

    Collection<Student> findAllByNameContains(String part); // Ищем всех студентов по части имени

    Collection<Student> findStudentsById(Long id);
}


