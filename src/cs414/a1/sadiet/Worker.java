package cs414.a1.sadiet;

import java.util.Set;

public class Worker {

	String name;
	Double salary;
	
	public Worker(String name, Set<Qualification> qs){
		
		
	}
	
	public String getName(){
		return name;
	}
	
	public Double getSalary(){
		return salary;
	}
	
	public void setSalary(Double salary){
		this.salary = salary;
	}
	
	public Set<Qualification> getQualifications(){
		
	}
	
	public void addQualification(Qualification q){
		
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Worker){
			if(name.equals(((Worker)obj).getName())){
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	@Override
	public int hashCode(){
		int val = 51; //arbitrary prime number
		int hash;
		if(name == null){ //check for empty name case
			hash = 0;
		}
		else{
			hash = name.hashCode();
		}
		int result = val + hash;
		return result;		
	}
	
	@Override
	public String toString(){
		
	}
	
	public boolean willOverload(Project p){
		
	}
	
	
}
