/* Write a query to join the table 'student' and 'course' using 'Course_id' to match both the tables and output the joined table. */

SELECT * from student
join course
on student.course_id = course.course_id;