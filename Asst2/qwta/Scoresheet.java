package qwta;

import java.util.Vector;

public class Scoresheet {

	public Quiz scored;
	public Marksheet recorded;
	private Vector<Integer> scores;
	
	// String-valued utility function for reporting on the number of attempts
	private String attemptsAt() {
	   if( scores.size() == 1) {
		   return "1 attempt at ";
	   } else {
		   return scores.size() + " attempts at ";
	   }
	}

	public void addScore( int score ) {
		try {
			// BUGFIX : reached attempts limit
			if (scores.size() > scored.maxAttempts)
				throw new Exception("reached attempted limit: " + scores.size());
			scores.add( score );
		} catch (Exception e){
			System.err.println(e.toString()/*+"\n"*/);
		}
		
	}

	public void reportOnScoresForStudent() {
		String outstr; 
		outstr = "Report for student " + recorded.marked.name + ": ";
		outstr += "In " + (recorded.assessed).name + ", you made ";
		outstr += attemptsAt() + scored.name;
		outstr += ".  Marks = " + scored.summariseScores( scores );
		System.out.println( outstr ); 
	}

	public String reportScoresToInstructor() {
		String retval;
		retval = "In " + recorded.assessed.name;
		retval += ", student " + recorded.marked.name + " made ";
		retval += attemptsAt() + scored.name;
		retval += ".  Marks = " + scored.summariseScores( scores );
		// BUGFIX display the report here
		System.out.println(retval);
		return retval;
	}

	public Scoresheet(Quiz q, Marksheet m) {
		scored = q;
		recorded= m;
		scores = new Vector<Integer>();
	}

}