package qwta;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Vector;

public class QuizTest {

	private Quiz quiz1, quizMax, quizAvg, quizLast;
	private Vector<Integer> vector1 = new Vector<Integer>();
	private Course course = new Course("course_name", new Instructor(
			"Instructor of a course"));
	private Scoresheet scoresheet; 

	@Before
	public void setUp() {
		quizMax = new Quiz("quizMax", course, 5, SummaryStatistic.MAX_SCORE);
		quizAvg = new Quiz("quizAvg", course, 5, SummaryStatistic.AVG_SCORE);
		quizLast = new Quiz("quizLast", course, 5, SummaryStatistic.LAST_SCORE);
		vector1.add(10);
		vector1.add(20);
		vector1.add(30);
		vector1.add(20);
		scoresheet = new Scoresheet(quizAvg, new Marksheet(new Student("student1"), course));
	}

	// coverage of the three methods for computing a SummaryStatistic on a
	// vector of integer scores
	@Test
	public void testSummariseScoresMax() {
		assertEquals((Integer) 30, quizMax.summariseScores(vector1));
	}

	@Test
	public void testSummariseScoresAvg() {
		assertEquals((Integer) 20, quizAvg.summariseScores(vector1));
	}

	@Test
	public void testSummariseScoresLast() {
		assertEquals((Integer) 20, quizLast.summariseScores(vector1));
	}

	// coverage of different lengths of the score vector
	@Test
	public void testSummariseScoresMoreElement() {
		// create a lot of element and add into vector1
		// UNVERIFIED_BUG if NUMBERS_OF_ELEMENT becomes large enough
		int NUMBERS_OF_ELEMENT = 1000000;
		Vector vector2 = new Vector<Integer>();
		int sum = 0, temp = 0;
		for (int i = 0; i < NUMBERS_OF_ELEMENT; i++) {
			temp =  (int)(1000000 * Math.random());
			sum += temp;
			vector2.add(temp);
		}
		assertEquals((Integer) (sum/NUMBERS_OF_ELEMENT), quizAvg.summariseScores(vector2));
	}

	// 1 c,d (none)integer values within the score vector
	@Test
	public void testSummariseScoresMoreMinusElement() {
		quiz1 = new Quiz("quiz1", course, 5, SummaryStatistic.AVG_SCORE);
		vector1.add((-30));
		// cause a error: JAVA_TYPE_SAFETY_SUCCESSFUL_TEST (minus integer)
		assertEquals((Integer) 20, quiz1.summariseScores(vector1));
	}

	// none-interger
	@Test
	public void testSummariseScoresMoreNoneIntegerElement() {
		quiz1 = new Quiz("quiz1", course, 5, SummaryStatistic.AVG_SCORE);
		Vector vector2 = new Vector(vector1);
		vector2.add("hello");
		// cause a error: JAVA_TYPE_SAFETY_SUCCESSFUL_TEST UNVERIFIED_BUG
		assertEquals((Integer) 20, quiz1.summariseScores(vector2));
	}

	// none-interger, float number
	@Test
	public void testSummariseScoresMoreNoneIntegerElementFloat() {
		quiz1 = new Quiz("quiz1", course, 5, SummaryStatistic.AVG_SCORE);
		Vector vector2 = new Vector();
		vector2.add(30);
		vector2.add(2.3);
		// cause a error: JAVA_TYPE_SAFETY_SUCCESSFUL_TEST UNVERIFIED_BUG
		assertEquals((Integer) 30, quiz1.summariseScores(vector2));
	}

	// none-interger, float number
	@Test
	public void testSummariseScoresNull() {
		quiz1 = new Quiz("quiz1", course, 5, SummaryStatistic.AVG_SCORE);
		// Vector vector2 = new Vector();
		// vector2.add(30);
		vector1.add(null);
		// cause a error: JAVA_TYPE_SAFETY_SUCCESSFUL_TEST UNVERIFIED_BUG
		assertEquals((Integer) 20, quiz1.summariseScores(vector1));
	}

	// @Test
	// public void testSummariseScoresAvg() {
	// //assertEquals("testSummariseScores 1: ", 100,
	// quiz1.summariseScores(vector1));
	// assertEquals((Integer)20, quiz1.summariseScores(vector1));
	// }

	// test 3 attempts
	@Test
	public void testSitQuizAttemptThreeTimes(){
//		Scoresheet scoresheet= new Scoresheet(quizAvg, new Marksheet(new Student("student1"), course));
		assertEquals(0, quizAvg.sitQuiz(scoresheet));
		assertEquals(0, quizAvg.sitQuiz(scoresheet));
		assertEquals(0, quizAvg.sitQuiz(scoresheet));
		scoresheet.reportOnScoresForStudent();
		scoresheet.reportScoresToInstructor();
	}
	
	// test one more attempt 
	@Test
	public void testSitQuizAttemptBeyondLimitedTimes(){
//		Scoresheet scoresheet= new Scoresheet(quizAvg, new Marksheet(new Student("student1"), course));
		assertEquals(0, quizAvg.sitQuiz(scoresheet));
		assertEquals(0, quizAvg.sitQuiz(scoresheet));
		assertEquals(0, quizAvg.sitQuiz(scoresheet));
		assertEquals(0, quizAvg.sitQuiz(scoresheet));
		assertEquals(0, quizAvg.sitQuiz(scoresheet));
		assertEquals(0, quizAvg.sitQuiz(scoresheet));
		assertEquals(0, quizAvg.sitQuiz(scoresheet));
		scoresheet.reportOnScoresForStudent();
		scoresheet.reportScoresToInstructor();
	}

}
