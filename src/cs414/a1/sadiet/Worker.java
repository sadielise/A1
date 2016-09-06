package cs414.a1.sadiet;

import java.util.HashSet;
import java.util.Set;

public class Worker {

	String name;
	Double salary;
	Set<Qualification> qs;
	Set<Project> ps;
	
	public Worker(String name, Set<Qualification> qs){
		this.name = name;
		this.qs = qs;	
		this.salary = 0.0;
		this.ps = new HashSet<Project>();
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
		return qs;
	}
	
	public void addQualification(Qualification q){
		qs.add(q);
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
		return name + ":" + ps.size() + ":" + qs.size() + ":" + salary;
	}
	
	public boolean willOverload(Project p){
		int numBig = 0;
		int numMedium = 0;
		int numSmall = 0;
		for(Project pTemp : ps){
			if(pTemp.getSize() == ProjectSize.BIG){
				numBig++;
			}
			else if(pTemp.getSize() == ProjectSize.MEDIUM){
				numMedium++;
			}
			else if(pTemp.getSize() == ProjectSize.SMALL){
				numSmall++;
			}
		}
		if(p.getSize() == ProjectSize.BIG){
			numBig++;
		}
		else if(p.getSize() == ProjectSize.MEDIUM){
			numMedium++;
		}
		else if(p.getSize() == ProjectSize.SMALL){
			numSmall++;
		}
		
		int total = (3*numBig + 2*numMedium + numSmall);
		return (total > 12);
	}
	
	public void addProject(Project p){
		ps.add(p);
	}
	
	public void removeProject(Project p){
		if(ps.contains(p)){
			ps.remove(p);
		}
	}
	
	
}
