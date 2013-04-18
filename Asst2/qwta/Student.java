package qwta;

import java.util.Iterator;
import java.util.Vector;

public class Student {

	public String name;
	// public Vector<Course> enrolled; (implied: students can do this through
	// Cecil)
	// public Vector<Marksheet> marked; (implied: students can do this through
	// Cecil)
	public Vector<Scoresheet> sat; // Our student must remember the quizzes they
									// have attempted!

	// QW probably should have a use-case to allow students to look up their
	// scoresheets

	public void takeQuiz(Scoresheet ss) {
		ss.scored.sitQuiz(ss);
		sat.add(ss);
	}

	public void requestReport() {
		Iterator<Scoresheet> ssi = sat.listIterator();
		while (ssi.hasNext()) {
			ssi.next().reportOnScoresForStudent();
		}
	}

	public Student(String n) {
		name = n;
		sat = new Vector<Scoresheet>();
	}

}