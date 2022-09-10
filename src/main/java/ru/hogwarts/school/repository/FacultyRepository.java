package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Collection<Faculty> findFacultyByNameContainingIgnoreCase(String name); // Ищем факультет в Хогвартсе по названию, игнорируя регистр

    Collection<Faculty> findFacultyById(Long id); // Ищем факультет в Хогвартсе по id

    Collection<Faculty> findFacultiesByColor(String color); // Ищем факультеты  по цвету


}