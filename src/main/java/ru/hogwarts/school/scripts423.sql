SELECT student.name, student.age,faculty.name
FROM student
INNER JOIN faculty ON student.faculty_id = faculty.id;

/////////////

SELECT avatar.id,student.name
FROM avatar
LEFT JOIN student ON avatar.student_id = student.id;


