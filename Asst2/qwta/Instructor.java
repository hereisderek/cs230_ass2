package qwta;

public class Instructor {

	public String iname;

	public void teachCourse( Course c, QuizWhiz qw ) {
		// use QW to create a quiz with default scoring,
		// and also to advertise the quiz to the students
		qw.createQuiz( "Q1", c );
		
		// then use QW to generate a report on student marks
		qw.reportOnCourse( c );
	}

	public Instructor(String n) {
		iname = n;
	}

}