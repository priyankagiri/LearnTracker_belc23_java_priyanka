package com.airtribe.learntrack.ui;

import java.util.Scanner;

import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;

public class Main {
	private static final Scanner scanner = new Scanner(System.in);
	private static final StudentService studentService = new StudentService();
	private static final CourseService courseService = new CourseService();
	private static final EnrollmentService enrollmentService = new EnrollmentService(studentService, courseService);

	public static void main(String[] args) {
		boolean running = true;

		while (running) {
			displayMenu();
			String option = scanner.nextLine();

			try {
				switch (option) {
					case "1" -> addStudent();
					case "2" -> studentService.listStudents();
					case "3" -> studentService.findStudentById(readInt("Student ID: "));
                    case "4" -> studentService.updateStudent(readInt("Student ID: "), readString("New name: "));
					case "5" -> deactivateStudent();
					case "6" -> addCourse();
					case "7" -> courseService.listCourses();
                    case "8" -> courseService.setCourseActiveStatus(readInt("Course ID: "), readString("Set active status (true/false): ").equalsIgnoreCase("true"));
					case "9" -> enrollStudent();
					case "10" -> viewEnrollments();
					case "11" -> updateEnrollmentStatus();
					case "0" -> running = false;
					default -> System.out.println("Option not found.");
				}
			} catch (InvalidInputException exception) {
				System.out.println(exception.getMessage());
			} catch (NumberFormatException exception) {
				System.out.println("Please enter a valid number.");
			}
		}

		scanner.close();
		System.out.println("Application closed.");
	}

	private static void displayMenu() {
		System.out.println("\n--- LearnTrack Menu ---");
		System.out.println("1. Add student");
		System.out.println("2. View all students");
        System.out.println("3. View student by ID");
        System.out.println("4. Update student");
		System.out.println("5. Deactivate student");
		System.out.println("6. Add course");
		System.out.println("7. View all courses");
        System.out.println("8. Set course active status");
		System.out.println("9. Enroll student in course");
		System.out.println("10. View student enrollments");
		System.out.println("11. Update enrollment status");
		System.out.println("0. Exit");
		System.out.print("Choose an option: ");
	}

	private static void addStudent() {
		System.out.print("First name: ");
		String firstName = scanner.nextLine();
		System.out.print("Last name: ");
		String lastName = scanner.nextLine();
		System.out.print("Email: ");
		String email = scanner.nextLine();
		System.out.print("Batch: ");
		String batch = scanner.nextLine();

		studentService.addStudent(firstName, lastName, email, batch);
		System.out.println("Student added.");
	}

	private static void deactivateStudent() {
		int studentId = readInt("Student ID: ");
		studentService.deactivateStudent(studentId);
		System.out.println("Student deactivated.");
	}

	private static void addCourse() {
		System.out.print("Course name: ");
		String name = scanner.nextLine();
		System.out.print("Description: ");
		String description = scanner.nextLine();

		courseService.addCourse(name, description);
		System.out.println("Course added.");
	}

	private static void enrollStudent() {
		int studentId = readInt("Student ID: ");
		int courseId = readInt("Course ID: ");

		enrollmentService.enrollStudentInCourse(studentId, courseId);
		System.out.println("Student enrolled.");
	}

	private static void viewEnrollments() {
		int studentId = readInt("Student ID: ");
		enrollmentService.viewEnrollmentsForStudent(studentId);
	}

	private static void updateEnrollmentStatus() {
		int enrollmentId = readInt("Enrollment ID: ");
		System.out.print("Status (ACTIVE, COMPLETED, CANCELLED): ");
		String status = scanner.nextLine();

		enrollmentService.updateEnrollmentStatus(enrollmentId, status);
		System.out.println("Enrollment status updated.");
	}

	private static int readInt(String prompt) {
		System.out.print(prompt);
		return Integer.parseInt(scanner.nextLine());
	}

	private static String readString(String prompt) {
		System.out.print(prompt);
		return scanner.nextLine();
	}
}
