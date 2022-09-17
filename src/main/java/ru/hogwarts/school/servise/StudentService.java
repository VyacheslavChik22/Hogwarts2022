package ru.hogwarts.school.servise;

;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {   //Метод добавления студента
        return studentRepository.save(student);
    }

    public Student getStudentId(Long id) {
        logger.debug("Request method getStudentId {}:", id);
    return studentRepository.findStudentsById(id);   // По ид.номеру можем найти студента
    }

    public Student editStudent(Student student) {
        logger.debug("Request method editStudent {}:",student);
        return studentRepository.save(student);        // Редактируем и сохраняем
    }

    public void deleteById(Long id) {
        logger.debug("Request method deleteById {}:",id);
        studentRepository.deleteById(id);              // Удаляем студента
    }

    public Collection<Student> getAllStudents() {      //  Получаем всех студентов
        logger.debug("Request method getAllStudents:");
        return studentRepository.findAll();
    }

    public Collection<Student> findStudentByName(String name) {        //используем метод поиска студента по имени без учета регистра
        logger.debug("Request method findStudentByName {}:",name);
        return studentRepository.findStudentByNameContainingIgnoreCase(name);
    }

    public Collection<Student> findStudentsByAge(int age, int ag2) {
        logger.debug("Request method findStudentsByAge {},{}:",age,ag2);
        return studentRepository.findAllStudentsByAgeBetween(age, ag2);    //используем метод поиска студентов по возрасту
    }

    public Collection<Student> findAllByNamePart(String part) {              // найти студента по части имени
        logger.debug("Request method findAllByNamePart {}:",part);
        return studentRepository.findAllByNameContains(part);
    }

    public Integer getAmountStudents() {                                     //подсчет студентов в таблице
        logger.debug("Request method findAllByNamePart:");
        return studentRepository.getAmountStudents();
    }

    public double getAverageAgeStudents() {
        logger.debug("Request method getAverageAgeStudents:");
        return studentRepository.getAverageAgeStudents();                   // подсчет среднего возраста студентов
    }

    public List<Student> getFiveStudentsWithMaxId(){
        logger.debug("Request method getFiveStudentsWithMaxId:");
        return studentRepository.getFiveLastId();
    }
}


