package qwta;
// BUGFIX
import java.util.Iterator;
import java.util.Vector;
import java.util.Collections;

public class Quiz {

	public String name;
	public Course assigned;
	public int maxAttempts; // limits the # of attempts per student
	public SummaryStatistic summaryMethod; // defines how marks are computed

	public Vector<Scoresheet> scored;
	
	public int maxScore;		//highest value that QuestionMark will award to any student taking this quiz.
	public double maxMarks;	//scale a student’s summarised score
//	scaledMarks() = summarisedScore / maxScore * maxMarks.
	
	public int sitQuiz(Scoresheet ss) {
		// In an included QM use-case, our student would get a new score
		// whenever they sit ss.scored,
		// but our simulated students always get a zero score from our simulated
		// QM!
		int score = 0;
		// QuizWhiz now adds the new score to the student's scoresheet
		ss.addScore(score);
		return score;
	}
	
	// BUGFIX change return type from Integer to float
	public float summariseScores(Vector<Integer> sv) {
		//  detect all none integer element and delete them
		String exceptionMsg = "";
		int i = 0;
		try {
			for (i = 0; i < sv.size(); i++) {
//				BUGFIX detect null element and remove it from the vector
				if (sv.get(i) == null) {
					sv.remove(i);
					exceptionMsg = "null element at index: " + i;
					throw new NullPointerException(exceptionMsg);
				}
				//  BUGFIX detect negative number and remove it from the vector 
				if (sv.get(i) < 0) {
					int nagetiveNum = sv.remove(i);
					exceptionMsg = ("negative score: " + nagetiveNum + " at index: " + i);
					throw new Exception(exceptionMsg);
				}
				
			}
		} catch (ClassCastException e){
			// BUGFIX: not an Integer element in the Vector 
//			exceptionMsg = ("not an Integer element: " + sv.get(i).toString());
//			System.err.println(exceptionMsg);
			exceptionMsg = ("" + sv.remove(i));
			System.err.println(e.toString() + ": " + exceptionMsg);
		} catch (Exception e) {
			System.err.println(e.toString());
			//e.printStackTrace();
		}
		switch (summaryMethod) {

		case LAST_SCORE:
			return (sv.lastElement());

		case AVG_SCORE:
			Iterator<Integer> si = sv.listIterator();
			Integer sum = 0;
			while (si.hasNext()) {
				sum += si.next();
			}
			return ((sv.size() == 0) ? null : (sum / sv.size()));

		default:
		case MAX_SCORE:
			return (Collections.max(sv));

		}
	}

	public double scaledMark(Vector<Integer> sv){
//		quiz.summariseScores(sv);
		return this.summariseScores(sv) / maxScore * maxMarks;
	}
	public Quiz(String n, Course c, int ma, SummaryStatistic sstat) {
		name = n;
		//System.out.println(name);
		assigned = c;
		maxAttempts = ma;
		summaryMethod = sstat;
	}

}