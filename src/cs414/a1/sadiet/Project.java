package cs414.a1.sadiet;

public class Project {
	
	String name;
	ProjectSize size;
	ProjectStatus status;
	
	public Project(String name, ProjectSize size, ProjectStatus status){
		
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
		
	}
	
	public Set<Qualification> missingQualifications(){
		
	}
	
	public boolean isHelpful(Worker w){
		
	}

}
