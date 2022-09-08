package ru.hogwarts.school.servise;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;


    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public Faculty addFaculty(Faculty faculty) {     //метод добавления факультета               C
        return facultyRepository.save(faculty);                    //ид.номера для факультетов
    }

    public Faculty findFaculty(Long id) {             // По ид.номеру можем найти факультет       R
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {     // Редактируем факультет                    U
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long id) {           // Удаляем факультет.                       D
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findFacultyByName(String name) {     // используем метод из репозитория для поиска факультета по названию
        return facultyRepository.findFacultyByNameContainingIgnoreCase(name);
    }

    public Collection<Faculty> findFacultyById(Long id) {  // используем метод из репозитория для поиска факультета по id
        return facultyRepository.findFacultyById(id);
    }

    public Collection<Faculty> findFacultiesByColor(String color) { // используем метод из репозитория для поиска факультетов по цвету
        return facultyRepository.findFacultiesByColor(color);
    }

    public Collection<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }
}
