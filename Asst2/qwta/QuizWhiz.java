package qwta;

import java.util.Iterator;
import java.util.Vector;

public class QuizWhiz {

	public Quiz createQuiz(String qn, Course c ) {
		return createQuiz( qn, c, 3, SummaryStatistic.AVG_SCORE );
	}

	public Quiz createQuiz(String qn, Course c, int ma, SummaryStatistic sstat) {
		// use QM to create a quiz
		Quiz q = new Quiz( qn, c, ma, sstat ); 
		// use Cecil to associate the new quiz with course c
		c.assignQuiz( q );
		// use Cecil to generate a list of enrolled students
		Vector<Marksheet> vm = c.assessed;
		Iterator<Marksheet> mi = vm.listIterator();
		while( mi.hasNext() ) {
			Marksheet m = mi.next();
			// add a scoresheet to a student's marksheet
			Scoresheet ss = m.addScoresheet( q );
			// inform (command ;-) a student to take this quiz
			m.marked.takeQuiz( ss );
		}
		return q;
	}

	public void reportOnMarks(Marksheet m) {
		String outstr;
		outstr = "Report for instructor " + m.assessed.taught.iname + ": ";
		Iterator<Scoresheet> ss = m.recorded.listIterator();
		while( ss.hasNext() ) {
			outstr += ss.next().reportScoresToInstructor();
			System.out.println( outstr );
			outstr = "    "; // indent reports on additional quizzes (if any)
		}
	}
	
	public void reportOnCourse(Course c) {
		Iterator<Marksheet> m = c.assessed.listIterator();
		while( m.hasNext() ) {
			reportOnMarks( m.next() );
			System.out.println(); // blank line after each course
		}
	}

}