package ru.hogwarts.school.servise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class StudentService {

    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

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
        logger.debug("Request method editStudent {}:", student);
        return studentRepository.save(student);        // Редактируем и сохраняем
    }

    public void deleteById(Long id) {
        logger.debug("Request method deleteById {}:", id);
        studentRepository.deleteById(id);              // Удаляем студента
    }

    public Collection<Student> getAllStudents() {      //  Получаем всех студентов
        logger.debug("Request method getAllStudents:");
        return studentRepository.findAll();
    }


    public Collection<Student> findStudentByName(String name) {        //используем метод поиска студента по имени без учета регистра
        logger.debug("Request method findStudentByName {}:", name);
        return studentRepository.findStudentByNameContainingIgnoreCase(name);
    }

    public Collection<Student> findStudentsByAge(int age, int ag2) {
        logger.debug("Request method findStudentsByAge {},{}:", age, ag2);
        return studentRepository.findAllStudentsByAgeBetween(age, ag2);    //используем метод поиска студентов по возрасту
    }

    public Collection<Student> findAllByNamePart(String part) {              // найти студента по части имени
        logger.debug("Request method findAllByNamePart {}:", part);
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

    public List<Student> getFiveStudentsWithMaxId() {
        logger.debug("Request method getFiveStudentsWithMaxId:");
        return studentRepository.getFiveLastId();
    }

    public Collection<String> getAllNameStartsWithA() { // Ищем студентов с именем на А
        return studentRepository.findAll().stream()     // Создаем стрим
                .filter(student -> student.getName().startsWith("A")) //фильтруем имена начинающиеся на А
                .map(student -> student.getName().toUpperCase())      // преобразуем в верхний регистр
                .sorted().collect(Collectors.toList());               // сортируем коллекцию
    }

    public Double getAverageAgeStudentsStream() {                      // Ищем средний возраст всех студентов
        return studentRepository.findAll().stream()
                .mapToInt(student -> student.getAge())
                .average().orElse(0);
    }

    //               Метод для получения коллекции имен студентов(МПКИС):
    private List<String> studentsNamesForNextActions() {
        List<String> infoNamesStudents = studentRepository.findAll().stream()
                .map(student -> student.getName())
                .collect(Collectors.toList());
        return infoNamesStudents;
    }

    // запускаем два параллельных потока для вывода имен студентов в консоль.
    public void getAllNamesInConsole() {
        List<String> namesStudents = studentsNamesForNextActions(); // переменная с  методом (МПКИС)

        logger.info("Основной поток Имена: {}", namesStudents.get(0));
        logger.info("Основной поток Имена: {}", namesStudents.get(1));
        new Thread(() -> {
            logger.info("Поток №_2 Имена: {}", namesStudents.get(2));
            logger.info("Поток №_2 Имена: {}", namesStudents.get(3));
        }).start();
        new Thread(() -> {
            logger.info("Поток №_3 Имена: {}", namesStudents.get(4));
            logger.info("Поток №_3 Имена: {}", namesStudents.get(5));
        }).start();


    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public final Object flag = new Object(); //Объект для блокировки потока

    // Метод для вывода двух имен студентов в консоль по параметрам
    private void printNamesToConsole(int index, List<String> namesStudentsForPrint) {
        synchronized (flag) {
            logger.info("Имена: {}", namesStudentsForPrint.get(index));
            logger.info("Имена: {}", namesStudentsForPrint.get(index + 1));
        }
    }

    public void getSynchronizedThreadsWithNames() {
        List<String> namesStudents = studentsNamesForNextActions();// переменная с  методом (МПКИС)

        printNamesToConsole(0, namesStudents);               // поток с индексами 0, 1

        new Thread(() -> {
            printNamesToConsole(2, namesStudents);      // поток с индексами 2, 3

        }).start();
        new Thread(() -> {
            printNamesToConsole(4, namesStudents);      // поток с индексами 4, 5

        }).start();

    }

}


