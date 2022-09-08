package ru.hogwarts.school;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HogwartsSchoolApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testDefaultMessage() {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localHost:" + port + "/", String.class))
                .isEqualTo("Hello, my test");

    }

    @Test
    public void testGetStudents() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localHost:" + port + "/student", String.class))
                .isNotNull();
    }
    @Test
    public void testGetFaculties() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localHost:" + port + "/faculty", String.class))
                .isNotNull();
    }
// Post методы изменяют данные в БД. Для проверки Post методов нужно использовать Mock
/*    @Test
    public void testPostStudent() throws Exception {
        Student student = new Student();
        student.setName("Aleksandr");
        student.setAge(12);
        student.setId(1L);

        Assertions
                .assertThat(this.restTemplate.postForObject("http://localHost:" + port + "/student", student, String.class))
                .isNotNull();
    }*/


}
