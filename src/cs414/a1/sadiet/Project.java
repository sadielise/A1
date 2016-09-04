package cs414.a1.sadiet;

import java.util.HashSet;
import java.util.Set;

public class Project {

	String name;
	ProjectSize size;
	ProjectStatus status;
	HashSet<Qualification> qs;
	HashSet<Worker> ws;

	public Project(String name, ProjectSize size, ProjectStatus status){
		this.name = name;
		this.size = size;
		this.status = status;
		qs = new HashSet<Qualification>();
		ws = new HashSet<Worker>();
	}

	public String getName(){
		return name;
	}

	public ProjectSize getSize(){
		return size;
	}

	public ProjectStatus getStatus(){
		return status;
	}

	public void setStatus(ProjectStatus s){
		status = s;
	}

	public void addQualification(Qualification q){
		qs.add(q);
	}

	public void removeQualification(Qualification q){
		if(qs.contains(q)){
			qs.remove(q);
		}
	}

	public void addWorker(Worker w){
		ws.add(w);
	}
	
	public void removeWorker(Worker w){
		if(ws.contains(w)){
			ws.remove(w);
		}
	}

	@Override
	public boolean equals(Object obj){
		if(obj instanceof Project){
			if(name.equals(((Project)obj).getName())){
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
		return name + ":" + ws.size() + ":" + status;
	}

	public Set<Qualification> missingQualifications(){
		Set<Qualification> qsMet = new HashSet<Qualification>();
		for(Worker w : ws){
			Set<Qualification> qsTemp = w.getQualifications();
			for(Qualification q1 : qsTemp){
				qsMet.add(q1);
			}
		}
		
		Set<Qualification> qsMissing = new HashSet<Qualification>();
		for(Qualification q2 : qs){
			if(!(qsMet.contains(q2))){
				qsMissing.add(q2);
			}
		}
		
		return qsMissing;
	}

	public boolean isHelpful(Worker w){
		Set<Qualification> qsTemp = w.getQualifications();
		Set<Qualification> qsMissing = missingQualifications();
		for(Qualification q : qsMissing){
			if(qsTemp.contains(q)){ return true; }
		}
		
		return false;
	}

}
