package cs414.a1.sadiet;

public class Qualification {
	
	String description;
	
	public Qualification(String description){
		this.description = description;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Qualification){
			if(description.equals(((Qualification)obj).toString())){
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
		if(description == null){ //check for empty name case
			hash = 0;
		}
		else{
			hash = description.hashCode();
		}
		int result = val + hash;
		return result;		
	}
	
	@Override
	public String toString(){
		return description;
	}

}
