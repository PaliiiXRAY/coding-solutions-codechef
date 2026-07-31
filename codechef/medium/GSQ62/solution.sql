/* Write a query to join the tables 'student' and 'course' and output the same. Check if you can find the course with id ENG201 in the output */
 SELECT * 
 from student
 
 join course
 on student.Course_id = course.Course_id;