# Design Notes

## Why You Used ArrayList Instead of Array

The application uses ArrayList across StudentService, CourseService, and EnrollmentService to manage records dynamically.

An ArrayList automatically resizes as items are added or removed.Array have standard fixed size.Used built in utility methods like add in this application.



## Where You Used Static Members and Why

Static counters are centralized in the IdGenerator utility class:

- studentIdCounter
- courseIdCounter
- enrollmentIdCounter

The services use the static methods getNextStudentId(), getNextCourseId(), and getNextEnrollmentId() to generate IDs. This keeps ID generation in one place and avoids duplicate counters in the service classes.

The counters are shared globally for their entity type while the application is running. IDs reset when the application restarts because the data is stored in memory only. Static methods can be called without creating an IdGenerator object.



## Where You Used Inheritance and What You Gained From It

The Person class serves as the base entity, encapsulating common attributes such as id, firstName, lastName, and email. 

Student and Trainer extend Person to inherit these shared fields and methods, eliminating redundant code. 

Subclasses invoke super() within their constructors to delegate inherited field initialization, and they override getDisplayName() to deliver specialized behavior—demonstrating polymorphism.

Overall, inheritance hierarchy eliminates duplication while establishing a   relationship between a general person, a student, and a trainer.