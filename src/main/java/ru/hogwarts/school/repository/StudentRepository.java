package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findStudentByNameContainingIgnoreCase(String name); // Ищем студента в Хогвартсе по имени или его части, без учета регистра

    Collection<Student> findAllStudentsByAgeBetween(int age, int age2); // Ищем всех студентов по возрасту

    Collection<Student> findAllByNameContains(String part); // Ищем всех студентов по части имени

    Student findStudentsById(Long id);

    @Query(value = "SELECT COUNT(*) FROM student ", nativeQuery = true)// Подсчет всех студентов
    Integer getAmountStudents();

    @Query(value = "SELECT AVG(age) FROM student ", nativeQuery = true)// Подсчет  среднего возраста всех студентов
    double getAverageAgeStudents();


    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true) // фильтр 5 студентов с наибольшим id
    List<Student> getFiveLastId();


}