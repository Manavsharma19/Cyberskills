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

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class StudentRepoTest {
    //StudentRepositroy.java Testing

	//We initalize the repositry as a mock to simulate its behavior down the road
    @Mock
    private StudentRepository studentRepository;
	//We make a function which will be used in our test execution
    @Test
    public void testFindByJoinCourses() {
		//Creating test data to be used within the Unit Test
        boolean joinCourses = true;
		//Using List to mock a database
        List<Student> students = new ArrayList<>();
		//Making to students to be added to the list
        students.add(new Student(1,"Maksims Kazoha", "9 inis allain Bandon", "086 394 5665","max@gmail.com", joinCourses));
        students.add(new Student(2,"Will Smith", "6969 main st bandon", "086 283 3931", "max@gmail.com",joinCourses));
		//We use the mocked repository and find join courses by using list as database and checking by join courses
        when(studentRepository.findByJoinCourses(joinCourses)).thenReturn(students);
		//We check if the repository contains students with joinCourses as true and store it in result
        List<Student> result = studentRepository.findByJoinCourses(joinCourses = true);
		//We use assertion to check if the expected result is the same as the result we made
        Assertions.assertEquals(2, result.size());
    }

	@Test
	public void testFindByStudentNameContaining(){
		//Target student name to be looked up
		String targetName = "Ma";
		//Using List to mock a database
		List<Student> students = new ArrayList<>();
		//Making to students to be added to the list
        students.add(new Student("Maksims Kazoha", "9 inis allain Bandon", "086 394 5665", true));
        students.add(new Student("Manav Sharma", "6969 main st bandon", "086 283 3931", true));
		students.add(new Student("Shamim Hasan", "4214 main st cork", "086 523 5312", true));
		
		when(studentRepository.findByStudentNameContaining(targetName)).thenReturn(students);
		List<Student> result =  new ArrayList<>();
		result = studentRepository.findByStudentNameContaining(targetName);
		System.out.println(result);
		System.out.println("Actual Result:");
		for (Student student : result) {
			System.out.println(student.getStudentName());
		}

		Assertions.assertEquals(1, result.size());
	}
}
