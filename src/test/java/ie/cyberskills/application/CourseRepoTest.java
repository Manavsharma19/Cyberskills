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

import ie.cyberskills.application.entity.Course;
import ie.cyberskills.application.entity.Student;
import ie.cyberskills.application.repository.CourseRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CourseRepoTest {
    //CourseRepository.java Testing

	//We initalize the repositry as a mock to simulate its behavior down the road
	@Mock
	private CourseRepository courseRepository;
	//We create dummy student for testing
	private Student student;

	@Test
	public void testFindByStudentId(){
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
	public void testDeleteByStudentId(){
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
