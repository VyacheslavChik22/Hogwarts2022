package ru.hogwarts.school.servise;;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import java.util.Collection;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {   //Метод добавления студента
        return studentRepository.save(student);
    }

    public Collection<Student> getStudentId(Long id) {
        return studentRepository.findStudentsById(id);   // По ид.номеру можем найти студента
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);        // Редактируем и сохраняем
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);              // Удаляем студента
    }

    public Collection<Student> getAllStudents() {      //  Получаем всех студентов
        return studentRepository.findAll();
    }

    public Collection<Student> findStudentByName(String name) {        //используем метод поиска студента по имени без учета регистра
        return studentRepository.findStudentByNameContainingIgnoreCase(name);
    }

    public Collection<Student> findStudentsByAge(int age, int ag2) {
        return studentRepository.findAllStudentsByAgeBetween(age, ag2);    //используем метод поиска студентов по возрасту
    }

    public Collection<Student> findAllByNamePart(String part) {
        return studentRepository.findAllByNameContains(part);
    }

}


