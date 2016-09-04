package cs414.a1.sadiet;

import org.junit.*;
import junit.framework.TestCase;

public class CompanyTest extends TestCase {

	private Company c;
	private Company blank;
	
	@Before 
	public void setUp(){
		c = new Company("Acme, Inc.");
		blank = new Company("");
	}
	
	@Test 
	public void testGetName1(){
		assertEquals("Acme, Inc.", c.getName());
	}
	
	@Test
	public void testGetName2(){
		assertEquals("", blank.getName());
	}
	
	@Test
	public void testGetAvailableWorkers(){
		
	}

}

