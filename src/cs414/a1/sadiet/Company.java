package cs414.a1.sadiet;

import java.util.HashSet;
import java.util.Set;

public class Company {
	
	private String name;
	private Set<Worker> availableWorkers;
	private Set<Worker> assignedWorkers;
	private Set<Worker> unassignedWorkers;
	private Set<Project> projects;
	
	public Company(String name){
		this.name = name;
		availableWorkers = new HashSet<Worker>();
		assignedWorkers = new HashSet<Worker>();
		unassignedWorkers = new HashSet<Worker>();
		projects = new HashSet<Project>();
	}
	
	public String getName(){
		return name;
	}
	
	public boolean checkProject(Project p){
		return projects.contains(p);
	}
	
	public boolean checkWorker(Worker w){
		return availableWorkers.contains(w);
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
		return availableWorkers;
	}
	
	public Set<Worker> getAssignedWorkers(){
		return assignedWorkers;
	}
	
	public Set<Worker> getUnassignedWorkers(){
		return unassignedWorkers;
	}
	
	@Override
	public String toString(){
		return null;
	}
	
	public void addToAvailableWorkerPool(Worker w){
		availableWorkers.add(w);
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
		
		//per program instructions and discussion board, I set the status to PLANNED no matter what is passed
		Project p = new Project(n, size, ProjectStatus.PLANNED);
		projects.add(p);
		
		for(Qualification q : qs){
			p.addQualification(q);
		}
		
		return p;
	}
	
	
}
