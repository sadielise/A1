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
	public void testGetAvailableWorkers1(){
		Set<Worker> wsTemp = c.getAvailableWorkers();
		assertTrue(wsTemp.size() == 0);
	}
	
	@Test
	public void testGetAvailableWorkers2(){
		c.addToAvailableWorkerPool(w1);
		c.addToAvailableWorkerPool(w2);
		c.addToAvailableWorkerPool(w3);
		boolean correct = true;
		Set<Worker> wsTemp = c.getAvailableWorkers();
		if(wsTemp.size() != 3){ correct = false; }
		if(!(c.checkWorker(w1))){ correct = false; }
		if(!(c.checkWorker(w2))){ correct = false; }
		if(!(c.checkWorker(w3))){ correct = false; }
		assertTrue(correct);
	}
	
	@Test
	public void testGetAssignedWorkers1(){
		Set<Worker> wsTemp = c.getAssignedWorkers();
		assertTrue(wsTemp.size() == 0);
	}
	
	@Test
	public void testGetAssignedWorkers2(){
		Project pTemp1 = c.createProject("Mars", qs1, ProjectSize.SMALL, ProjectStatus.PLANNED);
		c.addToAvailableWorkerPool(w1);
		c.assign(w1, pTemp1);
		Project pTemp2 = c.createProject("Jupiter", qs2, ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		c.addToAvailableWorkerPool(w2);
		c.assign(w2, pTemp2);
		Set<Worker> wsTemp = c.getAssignedWorkers();
		boolean correct = true;
		if(wsTemp.size() != 2){	correct = false;	}
		if(!(wsTemp.contains(w1))){	correct = false;	}
		if(!(wsTemp.contains(w2))){	correct = false;	}		
		assertTrue(correct);		
	}
	
	@Test
	public void testGetUnassignedWorkers1(){
		Set<Worker> wsTemp = c.getUnassignedWorkers();
		assertTrue(wsTemp.size() == 0);
	}
	
	@Test
	public void testGetUnassignedWorkers2(){
		Project pTemp = c.createProject("Mars", qs1, ProjectSize.SMALL, ProjectStatus.PLANNED);
		c.addToAvailableWorkerPool(w1);
		c.assign(w1, pTemp);
		c.unassign(w1, pTemp);
		Set<Worker> wsTemp = c.getUnassignedWorkers();
		boolean correct = true;
		if(wsTemp.size() != 1){	correct = false;	}
		if(!(wsTemp.contains(w1))){ correct = false;	}
		assertTrue(correct);
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
	public void testToString(){
		c.addToAvailableWorkerPool(w1);
		c.addToAvailableWorkerPool(w2);
		c.addToAvailableWorkerPool(w3);
		c.createProject("Mars", qs1, ProjectSize.BIG, ProjectStatus.PLANNED);
		assertEquals(c.toString(), "Acme, Inc.:3:1");
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
		Project pTemp = c.createProject("Neptune", qs1, ProjectSize.SMALL, ProjectStatus.PLANNED);
		c.assign(w1, pTemp);
		assertFalse(pTemp.checkWorker(w1));
	}
	
	@Test
	public void testAssign2(){
		Project pTemp = c.createProject("Neptune", qs1, ProjectSize.SMALL, ProjectStatus.PLANNED);
		c.addToAvailableWorkerPool(w1);
		c.assign(w1, pTemp);
		assertTrue(pTemp.checkWorker(w1));
	}

	@Test
	public void testAssign3(){
		Project pTemp = c.createProject("Neptune", qs1, ProjectSize.SMALL, ProjectStatus.PLANNED);
		pTemp.setStatus(ProjectStatus.ACTIVE);
		c.addToAvailableWorkerPool(w1);
		c.assign(w1, pTemp);
		assertFalse(pTemp.checkWorker(w1));
		pTemp.setStatus(ProjectStatus.FINISHED);
		c.assign(w1, pTemp);
		assertFalse(pTemp.checkWorker(w1));
	}
	
	@Test
	public void testAssign4(){
		Project pTemp1 = c.createProject("Neptune", qs1, ProjectSize.BIG, ProjectStatus.PLANNED);
		Project pTemp2 = c.createProject("Mars", qs1, ProjectSize.BIG, ProjectStatus.PLANNED);
		Project pTemp3 = c.createProject("Jupiter", qs1, ProjectSize.BIG, ProjectStatus.PLANNED);
		Project pTemp4 = c.createProject("Earth", qs1, ProjectSize.BIG, ProjectStatus.PLANNED);
		Project pTemp5 = c.createProject("Venus", qs1, ProjectSize.BIG, ProjectStatus.PLANNED);
		c.addToAvailableWorkerPool(w1);
		c.assign(w1, pTemp1);
		c.assign(w1, pTemp2);
		c.assign(w1, pTemp3);
		c.assign(w1, pTemp4);
		c.assign(w1, pTemp5);
		assertTrue(pTemp1.checkWorker(w1));
		assertTrue(pTemp2.checkWorker(w1));
		assertTrue(pTemp3.checkWorker(w1));
		assertTrue(pTemp4.checkWorker(w1));
		assertFalse(pTemp5.checkWorker(w1));
	}
	
	@Test
	public void testAssign5(){
		Project pTemp = c.createProject("Neptune", qs1, ProjectSize.SMALL, ProjectStatus.PLANNED);
		c.addToAvailableWorkerPool(w4);
		c.assign(w4, pTemp);
		assertFalse(pTemp.checkWorker(w1));
	}
	
	@Test
	public void testUnassign1(){
		Project pTemp = c.createProject("Neptune", qs1, ProjectSize.SMALL, ProjectStatus.PLANNED);
		c.addToAvailableWorkerPool(w1);
		c.addToAvailableWorkerPool(w2);
		c.assign(w1, pTemp);
		c.unassign(w2, pTemp);
		assertTrue(pTemp.getWorkers().size() == 1);
	}
	
	@Test
	public void testUnassign2(){
		Project pTemp = c.createProject("Neptune", qs1, ProjectSize.SMALL, ProjectStatus.PLANNED);
		c.addToAvailableWorkerPool(w1);
		c.assign(w1, pTemp);
		c.unassign(w1, pTemp);
		assertFalse(c.getAssignedWorkers().contains(w1));
		assertTrue(c.getUnassignedWorkers().contains(w1));
		assertTrue(c.getAvailableWorkers().contains(w1));
	}	
	
	@Test
	public void testUnassign3(){
		Project pTemp = c.createProject("Neptune", qs1, ProjectSize.SMALL, ProjectStatus.PLANNED);
		c.addToAvailableWorkerPool(w1);
		c.assign(w1, pTemp);
		c.unassign(w1, pTemp);
		assertEquals(pTemp.getStatus(), ProjectStatus.PLANNED);
	}
	
	@Test
	public void testUnassign4(){
		Project pTemp = c.createProject("Neptune", qs1, ProjectSize.SMALL, ProjectStatus.PLANNED);
		c.addToAvailableWorkerPool(w1);
		c.assign(w1, pTemp);
		c.start(pTemp);
		c.unassign(w1, pTemp);
		assertEquals(pTemp.getStatus(), ProjectStatus.SUSPENDED);
	}
	
	@Test
	public void testUnassignAll1(){
		Project pTemp1 = c.createProject("Neptune", qs1, ProjectSize.BIG, ProjectStatus.PLANNED);
		Project pTemp2 = c.createProject("Mars", qs1, ProjectSize.BIG, ProjectStatus.PLANNED);
		Project pTemp3 = c.createProject("Jupiter", qs1, ProjectSize.BIG, ProjectStatus.PLANNED);
		Project pTemp4 = c.createProject("Earth", qs1, ProjectSize.BIG, ProjectStatus.PLANNED);
		c.addToAvailableWorkerPool(w1);
		c.assign(w1, pTemp1);
		c.assign(w1, pTemp2);
		c.assign(w1, pTemp3);
		c.assign(w1, pTemp4);
		c.unassignAll(w1);
		assertFalse(pTemp1.checkWorker(w1));
		assertFalse(pTemp2.checkWorker(w1));
		assertFalse(pTemp3.checkWorker(w1));
		assertFalse(pTemp4.checkWorker(w1));
		assertFalse(c.getAssignedWorkers().contains(w1));
	}
	
	@Test
	public void testUnassignAll2(){
		Project pTemp1 = c.createProject("Neptune", qs1, ProjectSize.BIG, ProjectStatus.PLANNED);
		Project pTemp2 = c.createProject("Mars", qs1, ProjectSize.BIG, ProjectStatus.PLANNED);
		Project pTemp3 = c.createProject("Jupiter", qs1, ProjectSize.BIG, ProjectStatus.PLANNED);
		Project pTemp4 = c.createProject("Earth", qs1, ProjectSize.BIG, ProjectStatus.PLANNED);
		c.addToAvailableWorkerPool(w1);
		c.assign(w1, pTemp1);
		c.assign(w1, pTemp2);
		c.assign(w1, pTemp3);
		c.assign(w1, pTemp4);
		c.start(pTemp1);
		c.start(pTemp2);
		c.unassignAll(w1);
		assertEquals(pTemp1.getStatus(), ProjectStatus.SUSPENDED);
		assertEquals(pTemp2.getStatus(), ProjectStatus.SUSPENDED);
		assertEquals(pTemp3.getStatus(), ProjectStatus.PLANNED);
		assertEquals(pTemp4.getStatus(), ProjectStatus.PLANNED);
	}
	
	@Test
	public void testStart1(){
		Project pTemp = c.createProject("Neptune", qs1, ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		c.addToAvailableWorkerPool(w1);
		c.assign(w1, pTemp);
		c.start(pTemp);
		assertEquals(pTemp.getStatus(), ProjectStatus.ACTIVE);
	}
	
	@Test
	public void testStart2(){
		Project pTemp = c.createProject("Neptune", qs2, ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		c.addToAvailableWorkerPool(w1);
		c.assign(w1, pTemp);
		c.start(pTemp);
		assertEquals(pTemp.getStatus(), ProjectStatus.PLANNED);
	}
	
	@Test
	public void testStart3(){
		Project pTemp = c.createProject("Neptune", qs1, ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		c.addToAvailableWorkerPool(w1);
		pTemp.setStatus(ProjectStatus.SUSPENDED);
		assertEquals(pTemp.getStatus(), ProjectStatus.SUSPENDED);
	}
	
	@Test
	public void testFinish1(){
		Project pTemp = c.createProject("Neptune", qs1, ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		c.finish(pTemp);
		assertEquals(pTemp.getStatus(), ProjectStatus.PLANNED);
	}
	
	@Test
	public void testFinish2(){
		Project pTemp = c.createProject("Neptune", qs1, ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		pTemp.setStatus(ProjectStatus.SUSPENDED);
		c.finish(pTemp);
		assertEquals(pTemp.getStatus(), ProjectStatus.SUSPENDED);
	}

	@Test
	public void testFinish3(){
		Project pTemp = c.createProject("Neptune", qs1, ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		c.addToAvailableWorkerPool(w1);
		c.assign(w1, pTemp);
		c.start(pTemp);
		c.finish(pTemp);
		assertEquals(pTemp.getStatus(), ProjectStatus.FINISHED);
	}
	
	@Test
	public void testFinish4(){
		Project pTemp = c.createProject("Neptune", qs1, ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		c.addToAvailableWorkerPool(w1);
		c.assign(w1, pTemp);
		c.start(pTemp);
		c.finish(pTemp);
		assertFalse(c.getAssignedWorkers().contains(w1));
		assertTrue(c.getUnassignedWorkers().contains(w1));
	}
	
	@Test
	public void testCreateProject1(){
		Project pTemp = c.createProject("Neptune", qs1, ProjectSize.SMALL, ProjectStatus.PLANNED);
		assertTrue(c.checkProject(pTemp));
	}
	
	@Test
	public void testCreateProject2(){
		Project pTemp1 = c.createProject("Neptune", qs1, ProjectSize.SMALL, ProjectStatus.PLANNED);
		Project pTemp2 = c.createProject("Jupiter", qs2, ProjectSize.MEDIUM, ProjectStatus.PLANNED);
		assertTrue(c.checkProject(pTemp1) && c.checkProject(pTemp2));
	}
	
	@Test
	public void testCreateProject3(){
		Project pTemp = c.createProject("Neptune", qs1, ProjectSize.SMALL, ProjectStatus.ACTIVE);
		assertEquals(pTemp.getStatus(), ProjectStatus.PLANNED);
	}
	
	@Test
	public void testCreateProject4(){
		Project pTemp = c.createProject("Neptune", qs1, ProjectSize.SMALL, ProjectStatus.ACTIVE);
		assertEquals(pTemp.getName(), "Neptune");
		assertEquals(pTemp.getSize(), ProjectSize.SMALL);
		assertEquals(pTemp.getQualifications(), qs1);
	}
	
	@Test
	public void testCheckProject1(){
		Project pTemp = c.createProject("Neptune", qs1, ProjectSize.SMALL, ProjectStatus.ACTIVE);
		assertTrue(c.checkProject(pTemp));
	}
	
	@Test
	public void testCheckProject2(){
		Project pTemp = new Project("Neptune", ProjectSize.SMALL, ProjectStatus.PLANNED);
		assertFalse(c.checkProject(pTemp));
	}
	
	@Test
	public void testCheckWorker1(){
		c.addToAvailableWorkerPool(w1);
		assertTrue(c.checkWorker(w1));
	}
	
	@Test
	public void testCheckWorker2(){
		assertFalse(c.checkWorker(w1));
	}
}

