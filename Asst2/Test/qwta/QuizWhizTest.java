package qwta;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QuizWhizTest {
	private QuizWhiz quizWhiz;
	private Quiz quiz;
	@Before
	public void setUp(){
		quizWhiz = new QuizWhiz();
		quiz = quizWhiz.createQuiz("quiz1", new Course("course4QW", new Instructor("Instructor4QW")));
		quiz.maxMarks = 0.3;
		quiz.maxScore = 20;
	}
/*
	@Test
	public void testCreateQuizStringCourse() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateQuizStringCourseIntSummaryStatistic() {
		fail("Not yet implemented");
	}

	@Test
	public void testReportOnMarks() {
		fail("Not yet implemented");
	}

	@Test
	public void testReportOnCourse() {
		fail("Not yet implemented");
	}
*/
//	@Test
//	public double testScaledMark() {
//		//return quiz.summariseScores(sv) / maxScore * maxMarks;
//		//assertEquals(expected, actual);
//	}

}
