package qwta;

public class QuizWhizTestApp {


	public static void main(String[] args) {

		// First: instantiate one student, one instructor, one course, and one QuizWhiz.
		Student s1 = new Student( "S1" );
		Instructor i1 = new Instructor( "I1" );
		Course c1 = new Course( "C1", i1 );
		QuizWhiz qw1 = new QuizWhiz();
		
		// Next: enrol the student in the course (this is a Cecil use-case)
		c1.enrolStudent( s1 );
		
		// Next: tell the instructor that it's the first day of classes.  Time to start teaching!
		// The instructor will use qw1 to create a quiz, and to advertise it to the students in course c1.
		// After all students have attempted the quiz, the instructor will ask for a report.
		i1.teachCourse( c1, qw1 );
		
		// Finally: tell s1 to ask for a report.  
		// (I assume students will either retain a reference to their scoresheet, or will be able to find it on Cecil.)
		s1.requestReport();  

	}

}