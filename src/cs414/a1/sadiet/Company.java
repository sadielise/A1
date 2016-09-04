package cs414.a1.sadiet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Company {
	
	private String name;
	private HashSet<Worker> workers;
	private ArrayList<Project> projects;
	
	public Company(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Company){
			if(name.equals(((Company)obj).getName())){
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
	
	public Set<Worker> getAvailableWorkers(){
		
	}
	
	public Set<Worker> getAssignedWorkers(){
		
	}
	
	public Set<Worker> getUnassignedWorkers(){
		
	}
	
	@Override
	public String toString(){
		
	}
	
	public void addToAvailableWorkerPool(Worker w){
		
	}
	
	public void assign(Worker w, Project p){
		
	}
	
	public void unassign(Worker w, Project p){
		
	}
	
	public void unassignAll(Worker w){
		
	}
	
	public void start(Project p){

	}
	
	public void finish(Project p){
		
	}
	
	public Project createProject(String n, Set<Qualification> qs, ProjectSize size, ProjectStatus status){
		
	}
	
	
}
