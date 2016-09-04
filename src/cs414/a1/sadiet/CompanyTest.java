package cs414.a1.sadiet;

import java.util.HashSet;
import java.util.Set;

import org.junit.*;
import junit.framework.TestCase;

public class CompanyTest extends TestCase {

	private Company c;
	
	private Qualification q1;
	private Qualification q2;
	private Qualification q3;
	private Qualification q4;
	private Qualification q5;
	
	private HashSet<Qualification> qs1;	
	private HashSet<Qualification> qs2;	
	private HashSet<Qualification> qs3;	
	private HashSet<Qualification> qs4;
	
	private Worker w1;
	private Worker w2;
	private Worker w3;
	private Worker w4;
	
	@Before 
	public void setUp(){
		c = new Company("Acme, Inc.");
		
		q1 = new Qualification("Technical skills");
		q2 = new Qualification("Interpersonal skills");
		q3 = new Qualification("Intrapersonal skills");
		q4 = new Qualification("Organizational skills");
		q5 = new Qualification("Hands-on skills");
		
		qs1 = new HashSet<Qualification>();
		qs1.add(q1);
		qs1.add(q2);
		qs1.add(q3);
		
		qs2 = new HashSet<Qualification>();
		qs2.add(q2);
		qs2.add(q3);
		qs2.add(q4);
		
		qs3 = new HashSet<Qualification>();
		qs3.add(q3);
		qs3.add(q4);
		
		qs4 = new HashSet<Qualification>();
		qs4.add(q4);
		qs4.add(q5);
		
		w1 = new Worker("Alice", qs1);
		w2 = new Worker("Bob", qs2);
		w3 = new Worker("Cathy", qs3);
		w4 = new Worker("David", qs4);
		
	}

	@Test 
	public void testGetName1(){
		assertEquals("Acme, Inc.", c.getName());
	}
	
	@Test
	public void testGetName2(){
		Company blank = new Company("");
		assertEquals("", blank.getName());
	}
	
	@Test
	public void testGetAvailableWorkers(){
		c.addToAvailableWorkerPool(w1);
		c.addToAvailableWorkerPool(w2);
		c.addToAvailableWorkerPool(w3);
		boolean correct = true;
		Set<Worker> ws = c.getAvailableWorkers();
		if(ws.size() != 3){ correct = false; }
		if(!(c.checkWorker(w1))){ correct = false; }
		if(!(c.checkWorker(w2))){ correct = false; }
		if(!(c.checkWorker(w3))){ correct = false; }
		assertTrue(correct);
	}
	
	@Test
	public void testGetAssignedWorkers(){
		
	}
	
	@Test
	public void testEquals1(){
		Company copy = new Company("Acme, Inc.");
		assertTrue(copy.equals(c));
	}
	
	@Test
	public void testEquals2(){
		Company d = new Company("Cyberware LLC");
		assertFalse(d.equals(c));
	}
	
	@Test
	public void testAddToAvailableWorkerPool1(){
		c.addToAvailableWorkerPool(w1);
		assertTrue(c.checkWorker(w1));
	}
	
	@Test
	public void testAddToAvailableWorkerPool2(){
		c.addToAvailableWorkerPool(w1);
		c.addToAvailableWorkerPool(w2);
		assertTrue(c.checkWorker(w1) && c.checkWorker(w2));
	}
	
	@Test
	public void testAssign1(){
		c.addToAvailableWorkerPool(w1);
		Project p = c.createProject("Neptune", qs1, ProjectSize.SMALL, ProjectStatus.PLANNED);
		
	}
	
	@Test
	public void testCreateProject1(){
		Project p = c.createProject("Neptune", qs1, ProjectSize.SMALL, ProjectStatus.PLANNED);
		assertTrue(c.checkProject(p));
	}
	
	@Test
	public void testCreateProject2(){
		Project p1 = c.createProject("Neptune", qs1, ProjectSize.SMALL, ProjectStatus.PLANNED);
		Project p2 = c.createProject("Jupiter", qs2, ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		assertTrue(c.checkProject(p1) && c.checkProject(p2));
	}
	

}

