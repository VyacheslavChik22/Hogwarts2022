package ru.hogwarts.school.servise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {
    Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public Faculty addFaculty(Faculty faculty) {     //метод добавления факультета               C
        logger.debug("Request method addFaculty {}:",faculty);
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(Long id) {             // По ид.номеру можем найти факультет       R
        logger.debug("Request method findFaculty {}:",id);
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {     // Редактируем факультет                    U
        logger.debug("Request method editFaculty:");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long id) {           // Удаляем факультет.                       D
        logger.debug("Request method deleteFaculty {}:",id);
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findFacultyByName(String name) {     // используем метод из репозитория для поиска факультета по названию
        logger.debug("Request method findFacultyByName {}:",name);
        return facultyRepository.findFacultyByNameContainingIgnoreCase(name);
    }

    public Collection<Faculty> findFacultyById(Long id) {  // используем метод из репозитория для поиска факультета по id
        logger.debug("Request method findFacultyById {}:",id);
        return facultyRepository.findFacultyById(id);
    }

    public Collection<Faculty> findFacultiesByColor(String color) { // используем метод из репозитория для поиска факультетов по цвету
        logger.debug("Request method findFacultiesByColor {}:",color);
        return facultyRepository.findFacultiesByColor(color);
    }

    public Collection<Faculty> getAllFaculties() {
        logger.debug("Request method getAllFaculties:");
        return facultyRepository.findAll();
    }
}
