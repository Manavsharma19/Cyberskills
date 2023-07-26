package ie.cyberskills.application;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

//importing Student Entity and StudentRepository
import ie.cyberskills.application.entity.Student;
import ie.cyberskills.application.repository.StudentRepository;
//importing Course Entity and CourseRepository
import ie.cyberskills.application.entity.Course;
import ie.cyberskills.application.repository.CourseRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TestApplicationTests {
	//StudentRepositroy.java Testing

	//We initalize the repositry as a mock to simulate its behavior down the road
    @Mock
    private StudentRepository studentRepository;
	//We make a function which will be used in our test execution
    @Test
    void testFindByJoinCourses() {
		//Creating test data to be used within the Unit Test
        boolean joinCourses = true;
		//Using List to mock a database
        List<Student> students = new ArrayList<>();
		//Making to students to be added to the list
        students.add(new Student("Maksims Kazoha", "9 inis allain Bandon", "086 394 5665", joinCourses));
        students.add(new Student("Will Smith", "6969 main st bandon", "086 283 3931", joinCourses));
		//We use the mocked repository and find join courses by using list as database and checking by join courses
        when(studentRepository.findByJoinCourses(joinCourses)).thenReturn(students);
		//We check if the repository contains students with joinCourses as true and store it in result
        List<Student> result = studentRepository.findByJoinCourses(joinCourses = true);
		//We use assertion to check if the expected result is the same as the result we made
        Assertions.assertEquals(2, result.size());
    }

	@Test
	void testFindByStudentNameContaining(){
		//Target student name to be looked up
		String targetName = "Ma";
		//Using List to mock a database
		List<Student> students = new ArrayList<>();
		//Making to students to be added to the list
        students.add(new Student("Maksims Kazoha", "9 inis allain Bandon", "086 394 5665", true));
        students.add(new Student("Manav Sharma", "6969 main st bandon", "086 283 3931", true));
		students.add(new Student("Shamim Hasan", "4214 main st cork", "086 523 5312", true));
		
		when(studentRepository.findByStudentNameContaining(targetName)).thenReturn(students);
		List<Student> result = studentRepository.findByStudentNameContaining(targetName);
		System.out.println(students);
		System.out.println("Actual Result:");
		for (Student student : result) {
			System.out.println(student.getStudentName());
		}

		Assertions.assertEquals(1, result.size());
	}


	//CourseRepository.java Testing

	//We initalize the repositry as a mock to simulate its behavior down the road
	@Mock
	private CourseRepository courseRepository;
	//We create dummy student for testing
	private Student student;

	@Test
	void testFindByStudentId(){
		//we require CourseId for creating courses and decided to make two
		long courseId1 = 31202;
		long courseId2 = 31102;
		//we also need a student id for looking up a student
		long studentId = 188979;
		//we make an empty array as mock database
		List<Course> courses = new ArrayList<>();
		//we add two courses
		courses.add(new Course(courseId1 ,"Maths", "Maths Description", student));
		courses.add(new Course(courseId2 ,"Cyber Analytics", "Cyber Analytics Description", student));
		//we check for a student using findByStudentId then return the courses beloning to the student
		when(courseRepository.findByStudentId(studentId)).thenReturn(courses);
		//we store the result of findByStudentId into result for later tests
		List<Course> result = courseRepository.findByStudentId(studentId);
		//we compare the expected result with the result we got from using findByStudentId
		Assertions.assertEquals(2, result.size());
	}
	//Testing deleteByStudentId, checking if removing a student works.
	@Test
	void testDeleteByStudentId(){
		//creating studentId for look up
		long studentId = 188979;
		//we mock the course repository call for deleteByStudentId and use studentId to remove a specific student
		courseRepository.deleteByStudentId(studentId);
		//we verify that the delete function worked by searching up said student by id in the system
		when(courseRepository.findByStudentId(studentId)).thenReturn(new ArrayList<>());
		//we store the result of the outcome into result
		List<Course> result = courseRepository.findByStudentId(studentId);
		//we check the expect vs the result to see if its the same
		Assertions.assertEquals(0, result.size());
	}
}
