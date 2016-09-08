package cs414.a1.sadiet;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.*;

public class ProjectTest {

	private Project p1;
	private Project p2;
	private Project p3;
	private Project p4;
	
	private Qualification q1;
	private Qualification q2;
	private Qualification q3;
	private Qualification q4;
	
	private HashSet<Qualification> qs1;
	private HashSet<Qualification> qs2;
	
	private Worker w1;
	private Worker w2;
	
	@Before
	public void setUp(){
		p1 = new Project("Neptune", ProjectSize.SMALL, ProjectStatus.PLANNED);
		p2 = new Project("Jupiter", ProjectSize.MEDIUM, ProjectStatus.ACTIVE);
		p3 = new Project("Uranus", ProjectSize.BIG, ProjectStatus.FINISHED);
		p4 = new Project("Earth", ProjectSize.BIG, ProjectStatus.SUSPENDED);
	
		q1 = new Qualification("Technical skills");
		q2 = new Qualification("Interpersonal skills");
		q3 = new Qualification("Intrapersonal skills");
		q4 = new Qualification("HR Skills");
		
		qs1 = new HashSet<Qualification>();
		qs1.add(q1);
		qs1.add(q2);
		qs1.add(q3);
		
		qs2 = new HashSet<Qualification>();
		qs2.add(q1);
		qs2.add(q2);
		qs2.add(q3);
		qs2.add(q4);
				
		w1 = new Worker("Alice", qs1);
		w2 = new Worker("Bob", qs2);
	}
	
	@Test
	public void testGetName(){
		assertEquals(p1.getName(), "Neptune");
	}
	
	@Test
	public void testGetSize1(){
		assertEquals(p1.getSize(), ProjectSize.SMALL);
	}
	
	@Test
	public void testGetSize2(){
		assertEquals(p2.getSize(), ProjectSize.MEDIUM);
	}
	
	@Test
	public void testGetSize3(){
		assertEquals(p3.getSize(), ProjectSize.BIG);
	}
	
	@Test
	public void testGetStatus1(){
		assertEquals(p1.getStatus(), ProjectStatus.PLANNED);
	}
	
	@Test
	public void testGetStatus2(){
		assertEquals(p2.getStatus(), ProjectStatus.ACTIVE);
	}
	
	@Test
	public void testGetStatus3(){
		assertEquals(p3.getStatus(), ProjectStatus.FINISHED);
	}
	
	@Test
	public void testGetStatus4(){
		assertEquals(p4.getStatus(), ProjectStatus.SUSPENDED);
	}
	
	@Test
	public void testSetStatus1(){
		p1.setStatus(ProjectStatus.FINISHED);
		assertEquals(p1.getStatus(), ProjectStatus.FINISHED);
	}
	
	@Test
	public void testSetStatus2(){
		p1.setStatus(ProjectStatus.PLANNED);
		assertEquals(p1.getStatus(), ProjectStatus.PLANNED);
	}
	
	@Test
	public void testSetStatus3(){
		p1.setStatus(ProjectStatus.SUSPENDED);
		assertEquals(p1.getStatus(), ProjectStatus.SUSPENDED);
	}
	
	@Test
	public void testSetStatus4(){
		p1.setStatus(ProjectStatus.ACTIVE);
		assertEquals(p1.getStatus(), ProjectStatus.ACTIVE);
	}
	
	@Test
	public void testAddQualification(){
		p1.addQualification(q1);
		assertTrue(p1.getQualifications().contains(q1));
	}
	
	@Test
	public void testGetQualifications(){
		p1.addQualification(q1);
		p1.addQualification(q2);
		boolean correct = true;
		if(p1.getQualifications().size() != 2){	correct = false;	}
		if(!(p1.getQualifications().contains(q1))){	correct = false;	}
		if(!(p1.getQualifications().contains(q2))){	correct = false;	}
		assertTrue(correct);		
	}
	
	@Test
	public void testAddWorker(){
		p1.addWorker(w1);
		assertTrue(p1.checkWorker(w1));
	}
	
	@Test
	public void testRemoveWorker(){
		p1.addWorker(w1);
		p1.removeWorker(w1);
		assertFalse(p1.checkWorker(w1));
	}
	
	@Test
	public void testCheckWorker1(){
		assertFalse(p1.checkWorker(w2));
	}
	
	@Test
	public void testCheckWorker2(){
		p1.addWorker(w2);
		assertTrue(p1.checkWorker(w2));
	}
	
	@Test
	public void testGetWorkers1(){
		assertTrue(p1.getWorkers().size() == 0);
	}
	
	@Test
	public void testGetWorkers2(){
		p1.addWorker(w1);
		p1.addWorker(w2);
		boolean correct = true;
		if(p1.getWorkers().size() != 2){	correct = false;	}
		if(!(p1.getWorkers().contains(w1))){	correct = false;	}
		if(!(p1.getWorkers().contains(w2))){	correct = false;	}
		assertTrue(correct);
	}
	
	@Test
	public void testEquals1(){
		Project copy = new Project("Neptune", ProjectSize.SMALL, ProjectStatus.PLANNED);
		assertTrue(copy.equals(p1));
	}
	
	@Test
	public void testEquals2(){
		Project copy = new Project("Neptune", ProjectSize.MEDIUM, ProjectStatus.ACTIVE);
		assertTrue(copy.equals(p1));
	}
	
	@Test
	public void testEquals3(){
		assertFalse(p1.equals(p2));
	}
	
	@Test
	public void testToString(){
		p1.addWorker(w1);
		p1.addWorker(w2);
		assertEquals(p1.toString(), "Neptune:2:PLANNED");
	}
	
	@Test
	public void testMissingQualifications1(){
		p1.addQualification(q1);
		p1.addQualification(q2);
		p1.addQualification(q3);
		p1.addQualification(q4);
		p1.addWorker(w1);
		boolean correct = true;
		Set<Qualification> qs = p1.missingQualifications();
		if(qs.size() != 1){ correct = false; }
		if(!(qs.contains(q4))){ correct = false; }
		assertTrue(correct);
	}
	
	@Test
	public void testMissingQualifications2(){
		p2.addQualification(q1);
		p2.addQualification(q2);
		p2.addQualification(q3);
		p2.addQualification(q4);
		p2.addWorker(w2);
		boolean correct = true;
		Set<Qualification> qs = p2.missingQualifications();
		if(!(qs.isEmpty())){ correct = false; }
		assertTrue(correct);
	}
	
	@Test
	public void testIsHelpful1(){
		p1.addQualification(q1);
		assertTrue(p1.isHelpful(w1));		
	}
	
	@Test
	public void testIsHelpful2(){
		p1.addQualification(q1);
		p1.addWorker(w1);
		assertFalse(p1.isHelpful(w2));
	}	
}
