package qwta;

import java.util.Iterator;
import java.util.Vector;

public class Course {

  public String name;
  public Instructor taught;
  public Vector<Marksheet>  assessed;
  public Vector<Quiz>  assigned;
  
  public void assignQuiz(Quiz q) {
	  assigned.add( q );
  }

  public Marksheet enrolStudent(Student s) {
	  // create and return a marksheet for this student
	  Marksheet m = new Marksheet( s, this );
	  this.assessed.add( m );  // add this marksheet to the gradebook
	  return m;
  }
  
  public Vector<Student> enrolled() {
	  Vector<Student> vs = new Vector<Student>();
	  Iterator<Marksheet> mi = assessed.listIterator();
	  while( mi.hasNext() ) {
		  vs.add( mi.next().marked );
	  }
      return vs;
  }

  public Course(String n, Instructor i) {
	  name = n;
	  taught = i;
	  assessed = new Vector<Marksheet>();
	  assigned = new Vector<Quiz>();
  }

}