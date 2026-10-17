package com.airtribe.learntrack.ui;

import java.util.Scanner;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.EnrollmentStatus;
import com.airtribe.learntrack.entity.Person;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.entity.Course;

import com.airtribe.learntrack.exception.EntityNotFoundException;
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
					case "2" -> listStudents();
					case "3" -> displayStudent(studentService.findStudentById(readInt("Student ID: ")));
                    case "4" -> updateStudent();
					case "5" -> deactivateStudent();
					case "6" -> addCourse();
					case "7" -> listCourses();
                    case "8" -> courseService.setCourseActiveStatus(
                            readInt("Course ID: "),
                            readBoolean("Set active status (true/false): "));
					case "9" -> enrollStudent();
					case "10" -> viewEnrollments();
					case "11" -> updateEnrollmentStatus();
					case "0" -> running = false;
					default -> System.out.println("Option not found.");
				}
			} catch (InvalidInputException exception) {
				System.out.println(exception.getMessage());
			} catch (EntityNotFoundException exception) {
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

	private static void listStudents() {
		for (Student student : studentService.listStudents()) {
			displayStudent(student);
		}
	}

	private static void displayStudent(Student student) {
		Person person = student;
		System.out.println("============================================");
		System.out.println("ID: " + student.getId());
		System.out.println("Display Name: " + person.getDisplayName());
		System.out.println("First Name: " + student.getFirstName());
		System.out.println("Last Name: " + student.getLastName());
		System.out.println("Email: " + student.getEmail());
		System.out.println("Batch: " + student.getBatch());
		System.out.println("Active: " + student.isActive());
		System.out.println("============================================");
	}

	private static void deactivateStudent() {
		int studentId = readInt("Student ID: ");
		studentService.deactivateStudent(studentId);
		System.out.println("Student deactivated.");
	}

	private static void updateStudent() {
		int studentId = readInt("Student ID: ");
		String firstName = readString("New first name: ");
		String lastName = readString("New last name: ");
		String email = readString("New email: ");
		String batch = readString("New batch: ");

		studentService.updateStudent(studentId, firstName, lastName, email, batch);
		System.out.println("Student updated.");
	}

	private static void addCourse() {
		System.out.print("Course name: ");
		String name = scanner.nextLine();
		System.out.print("Description: ");
		String description = scanner.nextLine();
		int durationInWeeks = readInt("Duration (weeks): ");

		courseService.addCourse(name, description, durationInWeeks);
		System.out.println("Course added.");
	}

	private static void listCourses() {
		for (Course course : courseService.listCourses()) {
			System.out.println("============================================");
			System.out.println("ID: " + course.getId());
			System.out.println("Name: " + course.getCourseName());
			System.out.println("Description: " + course.getDescription());
			System.out.println("Duration (weeks): " + course.getDurationInWeeks());
			System.out.println("Active: " + course.isActive());
			System.out.println("============================================");
		}
	}

	private static void enrollStudent() {
		int studentId = readInt("Student ID: ");
		int courseId = readInt("Course ID: ");

		enrollmentService.enrollStudentInCourse(studentId, courseId);
		System.out.println("Student enrolled.");
	}

	private static void viewEnrollments() {
		int studentId = readInt("Student ID: ");
		for (Enrollment enrollment : enrollmentService.viewEnrollmentsForStudent(studentId)) {
			System.out.println(
					"Enrollment ID: " + enrollment.getId()
							+ ", Course ID: " + enrollment.getCourseId()
							+ ", Date: " + enrollment.getEnrollmentDate()
							+ ", Status: " + enrollment.getStatus());
		}
	}

	private static void updateEnrollmentStatus() {
		int enrollmentId = readInt("Enrollment ID: ");
		System.out.print("Status (ACTIVE, COMPLETED, CANCELLED): ");
		String status = scanner.nextLine();

		try {
			EnrollmentStatus newStatus = EnrollmentStatus.valueOf(status.trim().toUpperCase());
			enrollmentService.updateEnrollmentStatus(enrollmentId, newStatus);
		} catch (IllegalArgumentException exception) {
			throw new InvalidInputException(
					"Status must be ACTIVE, COMPLETED, or CANCELLED");
		}
		System.out.println("Enrollment status updated.");
	}

	private static int readInt(String prompt) {
		System.out.print(prompt);
		return Integer.parseInt(scanner.nextLine());
	}

	private static boolean readBoolean(String prompt) {
		String value = readString(prompt);
		if ("true".equalsIgnoreCase(value)) {
			return true;
		}
		if ("false".equalsIgnoreCase(value)) {
			return false;
		}
		throw new InvalidInputException("Please enter true or false.");
	}

	private static String readString(String prompt) {
		System.out.print(prompt);
		return scanner.nextLine();
	}
}
