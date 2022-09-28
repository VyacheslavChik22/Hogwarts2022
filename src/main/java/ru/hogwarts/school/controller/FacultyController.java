package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.servise.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    //Заинжектим FacultyService для дальнейшего использования
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/{id}")                                        //http:/localhost:8080/faculty/1...  выводим факультет
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {                                    //Если факультет не найден:
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping                       // Фильтры факультетов по названию, по цвету и id
    public ResponseEntity<Collection<Faculty>> findFaculties(@RequestParam(required = false) String name,
                                                             @RequestParam(required = false) Long id,
                                                             @RequestParam(required = false) String color) {
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(facultyService.findFacultyByName(name));
        }
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.findFacultiesByColor(color));
        }
        if (id > 0) {
            return ResponseEntity.ok(facultyService.findFacultyByName(String.valueOf(id)));
        }
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }


    @PostMapping                                               //http:/localhost:8080/faculty
    public Faculty addFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {

        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (foundFaculty == null) {                                          // Если факультет не найден:
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Collection<Faculty>> removeFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();

    }

    @GetMapping("getLongNameFaculty")
    public ResponseEntity<String> getLongNameFaculty() {
        String lNF = facultyService.getLongNameFaculty();
        if (lNF != null) {
            return ResponseEntity.ok(lNF);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

