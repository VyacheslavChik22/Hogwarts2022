package ru.hogwarts.school;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.AvatarController;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.servise.AvatarService;
import ru.hogwarts.school.servise.FacultyService;
import ru.hogwarts.school.servise.StudentService;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class MockApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean                                       // Эмуляция репозитория, чтобы не изменить данные в БД
    private StudentRepository studentRepository;

    @MockBean
    private FacultyRepository facultyRepository;

    @MockBean
    private AvatarRepository avatarRepository;

    @SpyBean                                       // Бин без изменений
    private StudentService studentService;

    @SpyBean
    private FacultyService facultyService;

    @SpyBean
    private AvatarService avatarService;

    // Все тесты используем в заинжекченых контроллерах
    @InjectMocks
    StudentController studentController;

    @InjectMocks
    FacultyController facultyController;

    @InjectMocks
    AvatarController avatarController;

    //***************************************************************

    //Объявляем константы
    private static final String NAME_STUDENT = "Charls Gotling";
    private static final int AGE_STUDENT1 = 11;
    private static final int AGE_STUDENT2 = 20;
    private static final Long ID_STUDENT = 1L;
@Test
    public void saveStudentTest() throws Exception {

        // Данные отправляемые на сервер
        JSONObject studentObject = new JSONObject();
        studentObject.put("name", NAME_STUDENT);
        studentObject.put("age", AGE_STUDENT1);
        studentObject.put("id", ID_STUDENT);


        //Ожидаемое значение:
        Student student = new Student();
        student.setId(ID_STUDENT);
        student.setAge(AGE_STUDENT1);
        student.setName(NAME_STUDENT);


        //назначение запросов
        when(studentRepository.save(any(Student.class))).thenReturn(student);



        //выполнение запросов
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")                //создаем объект
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))

                //Проверка возвращаемых значений

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID_STUDENT))
                .andExpect(jsonPath("$.age").value(AGE_STUDENT1))
                .andExpect(jsonPath("$.name").value(NAME_STUDENT));


    }

}