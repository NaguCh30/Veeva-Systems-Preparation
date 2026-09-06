/*
 * University Student Management System
 *
 * Create a student management system to store students branch-wise.
 *
 * Each Student should contain:
 *   - Roll Number
 *   - Name
 *   - Branch
 *   - Section
 *   - CGPA
 *   - Grade
 *
 * Grade should be calculated automatically based on CGPA:
 *
 *   CGPA >= 9.0  -> S
 *   CGPA >= 8.0  -> A
 *   CGPA >= 7.0  -> B
 *   CGPA >= 6.0  -> C
 *   CGPA >= 5.0  -> D
 *   CGPA <  5.0  -> F
 *
 * Store students using:
 *
 * HashMap<String, List<Student>>
 *
 * where the key is the branch name.
 *
 * Implement the following operations:
 *
 * 1. Display all students branch-wise.
 *
 * 2. Display students branch-wise in descending order of grade.
 *
 * 3. Display students branch-wise in ascending order of CGPA.
 *
 * 4. Given a branch:
 *      - Display the total number of students in that branch.
 *      - Display the names and roll numbers of students who have S grade.
 *
 * 5. Find the branch having the highest number of students with S grade.
 *
 * 6. Add a new student to the university.
 *
 * 7. Exit the program.
 *
 *
 * Example:
 *
 * CSE:
 * 101 | Ravi  | CSE | A | 9.2 | S
 * 102 | Arun  | CSE | B | 8.5 | A
 * 103 | John  | CSE | A | 9.6 | S
 * 104 | Rahul | CSE | B | 7.8 | B
 *
 * ECE:
 * 201 | Raj   | ECE | A | 9.5 | S
 * 202 | Sam   | ECE | B | 8.1 | A
 * 203 | Kiran | ECE | A | 9.7 | S
 *
 *
 * For CSE:
 *
 * Total students: 4
 *
 * Students with S grade:
 * Ravi  | Roll No: 101
 * John  | Roll No: 103
 *
 *
 * Branch with highest number of S grades:
 *
 * ECE
 * Number of S grades: 2
 *
 *
 * Use appropriate Java collections, sorting/comparators, and
 * object-oriented design to implement the system.
 */




package OOP;

import java.util.*;

class Student {

    int rollNumber;
    String name;
    String branch;
    String section;
    double cgpa;
    String grade;

    Student(int rollNumber, String name, String branch,
            String section, double cgpa) {

        this.rollNumber = rollNumber;
        this.name = name;
        this.branch = branch;
        this.section = section;
        this.cgpa = cgpa;

        this.grade = calculateGrade(cgpa);
    }

    static String calculateGrade(double cgpa) {

        if (cgpa >= 9.0) {
            return "S";
        } else if (cgpa >= 8.0) {
            return "A";
        } else if (cgpa >= 7.0) {
            return "B";
        } else if (cgpa >= 6.0) {
            return "C";
        } else if (cgpa >= 5.0) {
            return "D";
        } else {
            return "F";
        }
    }

    @Override
    public String toString() {

        return rollNumber + " | "
                + name + " | "
                + branch + " | "
                + section + " | "
                + cgpa + " | "
                + grade;
    }
}


class University {

    static HashMap<String, List<Student>> studentsByBranch
            = new HashMap<>();

    static void addStudent(Student student) {

        studentsByBranch
                .putIfAbsent(student.branch, new ArrayList<>());

        studentsByBranch
                .get(student.branch)
                .add(student);

        System.out.println("Student added successfully.");
    }

    void displayStudentsBranchWise() {

        System.out.println();
        System.out.println("========== STUDENTS BRANCH-WISE ==========");

        for (Map.Entry<String, List<Student>> entry
                : studentsByBranch.entrySet()) {

            String branch = entry.getKey();

            List<Student> students = entry.getValue();

            System.out.println();
            System.out.println("Branch: " + branch);

            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    void displayByGradeDescending() {

        System.out.println();
        System.out.println("========== STUDENTS BY GRADE DESCENDING ==========");

        for (Map.Entry<String, List<Student>> entry
                : studentsByBranch.entrySet()) {

            String branch = entry.getKey();

            List<Student> students = entry.getValue();

            System.out.println();
            System.out.println("Branch: " + branch);

            List<Student> temp = new ArrayList<>(students);

            temp.sort(
                (a, b) -> compareGrade(b.grade, a.grade)
            );

            for (Student student : temp) {
                System.out.println(student);
            }
        }
    }

    static int compareGrade(String grade1, String grade2) {

        Map<String, Integer> gradeValue = new HashMap<>();

        gradeValue.put("S", 6);
        gradeValue.put("A", 5);
        gradeValue.put("B", 4);
        gradeValue.put("C", 3);
        gradeValue.put("D", 2);
        gradeValue.put("F", 1);

        return Integer.compare(
                gradeValue.get(grade1),
                gradeValue.get(grade2)
        );
    }

    void displayByCGPAscending() {

        System.out.println();
        System.out.println("========== STUDENTS BY CGPA ASCENDING ==========");

        for (Map.Entry<String, List<Student>> entry
                : studentsByBranch.entrySet()) {

            String branch = entry.getKey();

            List<Student> students = entry.getValue();

            System.out.println();
            System.out.println("Branch: " + branch);

            List<Student> temp = new ArrayList<>(students);

            temp.sort(
                (a, b) -> Double.compare(a.cgpa, b.cgpa)
            );

            for (Student student : temp) {
                System.out.println(student);
            }
        }
    }

    void findStudentsWithSGrade(String branch) {

        if (!studentsByBranch.containsKey(branch)) {

            System.out.println(
                    "Branch not found."
            );

            return;
        }

        List<Student> students =
                studentsByBranch.get(branch);

        System.out.println();
        System.out.println(
                "Total students in " + branch
                + ": " + students.size()
        );

        System.out.println();
        System.out.println("Students with S grade:");

        boolean found = false;

        for (Student student : students) {

            if (student.grade.equals("S")) {

                System.out.println(
                        student.name
                        + " | Roll No: "
                        + student.rollNumber
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println(
                    "No student has S grade."
            );
        }
    }

    void branchWithHighestSGrades() {

        String highestBranch = null;
        int highestCount = 0;

        for (Map.Entry<String, List<Student>> entry
                : studentsByBranch.entrySet()) {

            String branch = entry.getKey();

            List<Student> students = entry.getValue();

            int count = 0;

            for (Student student : students) {

                if (student.grade.equals("S")) {
                    count++;
                }
            }

            if (count > highestCount) {

                highestCount = count;
                highestBranch = branch;
            }
        }

        if (highestBranch == null) {

            System.out.println(
                    "No S grade found in any branch."
            );

        } else {

            System.out.println();
            System.out.println(
                    "Branch with highest number of S grades: "
                    + highestBranch
            );

            System.out.println(
                    "Number of S grades: "
                    + highestCount
            );
        }
    }
}


class StudentManagement {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        University university = new University();

        University.addStudent(
                new Student(101, "Ravi", "CSE", "A", 9.2)
        );

        University.addStudent(
                new Student(102, "Arun", "CSE", "B", 8.5)
        );

        University.addStudent(
                new Student(103, "John", "CSE", "A", 9.6)
        );

        University.addStudent(
                new Student(104, "Rahul", "CSE", "B", 7.8)
        );


        University.addStudent(
                new Student(201, "Raj", "ECE", "A", 9.5)
        );

        University.addStudent(
                new Student(202, "Sam", "ECE", "B", 8.1)
        );

        University.addStudent(
                new Student(203, "Kiran", "ECE", "A", 9.7)
        );


        University.addStudent(
                new Student(301, "Vijay", "IT", "A", 8.8)
        );

        University.addStudent(
                new Student(302, "Ajay", "IT", "B", 7.5)
        );

        University.addStudent(
                new Student(303, "Suresh", "IT", "A", 9.1)
        );


        while (true) {

            System.out.println();
            System.out.println("=============== MENU ===============");

            System.out.println("1. Display students branch-wise");
            System.out.println("2. Display students by grade descending");
            System.out.println("3. Display students by CGPA ascending");
            System.out.println("4. Find students with S grade in branch");
            System.out.println("5. Find branch with highest S grades");
            System.out.println("6. Add Student");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();


            if (choice == 1) {

                university.displayStudentsBranchWise();

            }

            else if (choice == 2) {

                university.displayByGradeDescending();

            }

            else if (choice == 3) {

                university.displayByCGPAscending();

            }

            else if (choice == 4) {

                System.out.print("Enter branch: ");

                String branch = sc.nextLine();

                university.findStudentsWithSGrade(branch);

            }

            else if (choice == 5) {

                university.branchWithHighestSGrades();

            }

            else if (choice == 6) {

                System.out.print("Roll Number: ");
                int rollNumber = sc.nextInt();
                sc.nextLine();

                System.out.print("Name: ");
                String name = sc.nextLine();

                System.out.print("Branch: ");
                String branch = sc.nextLine();

                System.out.print("Section: ");
                String section = sc.nextLine();

                System.out.print("CGPA: ");
                double cgpa = sc.nextDouble();
                sc.nextLine();

                Student student = new Student(
                        rollNumber,
                        name,
                        branch,
                        section,
                        cgpa
                );

                University.addStudent(student);
            }

            else if (choice == 7) {

                System.out.println("Exiting...");
                break;

            }

            else {

                System.out.println(
                        "Please enter a valid choice."
                );
            }
        }

        sc.close();
    }
}
