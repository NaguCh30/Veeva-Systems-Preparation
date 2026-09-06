/*
 * Question 10:
 *
 * Implement the same University Student Management System problem
 * from Question 9 using MySQL.
 *
 * Create a Student table with the following details:
 *
 *   - Roll Number
 *   - Name
 *   - Branch
 *   - Section
 *   - CGPA
 *   - Grade
 *
 * Grade should be determined based on CGPA:
 *
 *   CGPA >= 9.0  -> S
 *   CGPA >= 8.0  -> A
 *   CGPA >= 7.0  -> B
 *   CGPA >= 6.0  -> C
 *   CGPA >= 5.0  -> D
 *   CGPA <  5.0  -> F
 *
 * Write MySQL queries for the following operations:
 *
 * 1. Display all students branch-wise.
 *
 * 2. Display students branch-wise in descending order of grade.
 *
 * 3. Display students branch-wise in ascending order of CGPA.
 *
 * 4. Given a branch:
 *      - Find the total number of students in that branch.
 *      - Display the names and roll numbers of students having S grade.
 *
 * 5. Find the branch having the highest number of students
 *    with S grade.
 *
 * 6. Insert a new student into the Student table.
 */


 CREATE TABLE Student (
    roll_number INT PRIMARY KEY,
    name VARCHAR(50),
    branch VARCHAR(20),
    section VARCHAR(10),
    cgpa DECIMAL(3,2),
    grade CHAR(1)
);


INSERT INTO Student
(roll_number, name, branch, section, cgpa, grade)
VALUES
(101, 'Ravi',   'CSE', 'A', 9.20, 'S'),
(102, 'Arun',   'CSE', 'B', 8.50, 'A'),
(103, 'John',   'CSE', 'A', 9.60, 'S'),
(104, 'Rahul',  'CSE', 'B', 7.80, 'B'),

(201, 'Raj',    'ECE', 'A', 9.50, 'S'),
(202, 'Sam',    'ECE', 'B', 8.10, 'A'),
(203, 'Kiran',  'ECE', 'A', 9.70, 'S'),

(301, 'Vijay',  'IT',  'A', 8.80, 'A'),
(302, 'Ajay',   'IT',  'B', 7.50, 'B'),
(303, 'Suresh', 'IT',  'A', 9.10, 'S');


-- 1. Display all students branch-wise
SELECT *
FROM Student
ORDER BY branch;

SELECT *
FROM Student
ORDER BY branch, roll_number;


-- 2. Display students branch-wise in descending order of grade
SELECT *
FROM Student
ORDER BY
    branch,
    FIELD(grade, 'S', 'A', 'B', 'C', 'D', 'F');


SELECT *
FROM Student
ORDER BY
    branch ASC,
    FIELD(grade, 'S', 'A', 'B', 'C', 'D', 'F') ASC;


-- 3. Display students branch-wise in ascending order of CGPA
SELECT *
FROM Student
ORDER BY branch ASC, cgpa ASC;


-- 4. Given a branch, find total students and students with S grade
SELECT COUNT(*) AS total_students
FROM Student
WHERE branch = 'CSE';


-- 5. Find the branch having the highest number of S grades
SELECT
    branch,
    COUNT(*) AS s_grade_count
FROM Student
WHERE grade = 'S'
GROUP BY branch
ORDER BY s_grade_count DESC
LIMIT 1;

