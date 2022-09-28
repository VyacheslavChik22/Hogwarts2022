SELECT student.name, student.age,faculty.name
FROM student
LEFT JOIN faculty ON student.faculty_id = faculty.id;

/////////////

SELECT avatar.id,student.name
FROM avatar
INNER JOIN student ON avatar.student_id = student.id;


