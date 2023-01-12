package project4;

public class Student {
	protected String firstName,lastName,ID;
	
	public Student(String firstName, String lastName,String ID) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.ID = ID;
	}
	
	
	 public String getfirstName() {
		return this.firstName;
	}
	 
	 public String getlastName() {
		 return this.lastName;
	 }
	 
	 public String getID() {
		 return this.ID;
	 }
	 
	 
	public void setfirstName(String F) {
		this.firstName = F;
	}
	
	public void setlastName(String L) {
		this.lastName = L;
	}
	
	public void setID(String I) {
		this.ID = I;
	}
	
	public String toString() {
		return (firstName+" "+lastName+" "+ID);
	}
	
	//tests equality by ID
			public boolean equals(Student other){
				return this.ID.equals(other.ID);
				}
			
			//compares students alphabetically
			public int compareTo(Student other)
			{
				int y = this.lastName.compareTo(other.lastName);
				if (y != 0) return y;
				y = this.firstName.compareTo(other.firstName);
				if (y != 0) return y;
				return this.ID.compareTo(other.ID);
				
			}

}
