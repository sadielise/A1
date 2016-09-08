package cs414.a1.sadiet;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.*;

public class WorkerTest {
	
	Worker w;
	
	Qualification q1;
	Qualification q2;
	Qualification q3;
	Set<Qualification> qs;
	
	Project p;

	@Before
	public void setUp(){
		
		q1 = new Qualification("Technical skills");
		q2 = new Qualification("Personal skills");
		q3 = new Qualification("Hands-on skills");
		
		qs = new HashSet<Qualification>();
		qs.add(q1);
		qs.add(q2);
		qs.add(q3);
		
		w = new Worker("Sadie",qs);

		p = new Project("Venus", ProjectSize.BIG, ProjectStatus.ACTIVE);
		w.addProject(p);
	}
	
	@Test
	public void testGetName(){
		assertEquals(w.getName(), "Sadie");
	}
	
	@Test
	public void testGetSalary(){
		assertTrue(w.getSalary() == 0.0);
	}
	
	@Test
	public void testSetSalary(){
		w.setSalary(100.00);
		assertTrue(w.getSalary() == 100.00);
	}
	
	@Test
	public void testGetQualifications(){
		Set<Qualification> qsTemp = w.getQualifications();
		boolean correct = true;
		if(qsTemp.size() != 3){
			correct = false;
		}
		for(Qualification q : qsTemp){
			if(!(qs.contains(q))){
				correct = false;
			}
		}
		assertTrue(correct);
	}
	
	@Test
	public void testAddQualification(){
		Qualification qTemp = new Qualification("Office skills");
		w.addQualification(qTemp);
		Set<Qualification> qsTemp = w.getQualifications();
		boolean correct = true;
		if(qsTemp.size() != 4){
			correct = false;
		}
		for(Qualification q : qsTemp){
			if(!(qs.contains(q))){
				correct = false;
			}
		}
		assertTrue(correct);
	}
	
	@Test
	public void testEquals1(){
		Worker wTemp = new Worker("Sadie", qs);
		assertTrue(w.equals(wTemp));
	}
	
	@Test
	public void testEquals2(){
		Worker wTemp = new Worker("Nathan", qs);
		assertFalse(w.equals(wTemp));
	}
	
	@Test
	public void testToString(){
		assertEquals(w.toString(), "Sadie:1:3:0.0");
	}
	
	@Test
	public void testWillOverload1(){
		Project pTemp = new Project("Mars", ProjectSize.MEDIUM, ProjectStatus.ACTIVE);
		assertFalse(w.willOverload(pTemp));
	}

	@Test
	public void testWillOverload2(){
		Project pTemp1 = new Project("p1", ProjectSize.BIG, ProjectStatus.ACTIVE);
		Project pTemp2 = new Project("p2", ProjectSize.BIG, ProjectStatus.ACTIVE);
		Project pTemp3 = new Project("p3", ProjectSize.BIG, ProjectStatus.ACTIVE);
		Project pTemp4 = new Project("p4", ProjectSize.SMALL, ProjectStatus.ACTIVE);
		w.addProject(pTemp1);
		w.addProject(pTemp2);
		w.addProject(pTemp3);
		assertTrue(w.willOverload(pTemp4));
	}
	
	@Test
	public void testAddProject(){
		Project pTemp = new Project("p1", ProjectSize.BIG, ProjectStatus.ACTIVE);
		w.addProject(pTemp);
		assertTrue(w.getProjects().contains(pTemp));
	}
	
	@Test
	public void testRemoveProject(){
		Project pTemp = new Project("p1", ProjectSize.BIG, ProjectStatus.ACTIVE);
		w.addProject(pTemp);
		w.removeProject(pTemp);
		assertFalse(w.getProjects().contains(pTemp));
	}
	
	@Test
	public void testGetProjects(){
		Project pTemp1 = new Project("p1", ProjectSize.BIG, ProjectStatus.ACTIVE);
		Project pTemp2 = new Project("p2", ProjectSize.BIG, ProjectStatus.ACTIVE);
		w.addProject(pTemp1);
		w.addProject(pTemp2);
		boolean correct = true;
		if(w.getProjects().size() != 3){	correct = false;	}
		if(!(w.getProjects().contains(p))){	correct = false;	}
		if(!(w.getProjects().contains(pTemp1))){	correct = false;	}
		if(!(w.getProjects().contains(pTemp2))){	correct = false;	}
		assertTrue(correct);
	}
}
