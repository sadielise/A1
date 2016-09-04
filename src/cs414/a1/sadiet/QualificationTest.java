package cs414.a1.sadiet;

import static org.junit.Assert.*;

import org.junit.*;

public class QualificationTest {

	private Qualification q;
	
	@Before
	public void setUp(){
		q = new Qualification("Technical Skills");
	}
	
	@Test
	public void testEquals1(){
		Qualification copy = new Qualification("Technical Skills");
		assertTrue(copy.equals(q));
	}
	
	@Test
	public void testEquals2(){
		Qualification r = new Qualification("HR Skills");
		assertFalse(r.equals(q));
	}
	
	@Test
	public void testToString(){
		assertTrue(q.toString().equals("Technical Skills"));
	}
}
