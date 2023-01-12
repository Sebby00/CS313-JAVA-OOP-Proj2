package project4;

import java.io.FileWriter;
import java.io.IOException;

public class LinkedWaitList {
	protected  StudentNode head;
	protected  StudentNode last = head;
	
	//sets the head equal to a new node that is equal to null
	public LinkedWaitList(){
		head = new StudentNode(null);
	}
	
	//adds a student to the waitlist
	public  void Enqueue(Student wait) {
		StudentNode newStu = new StudentNode(wait);
		
		if(head.next == null) {
			head.next = newStu;
			last = newStu;
		}
		
		else {
			last.next = newStu;
			last = newStu;
		}
	}
	
	//removes the student at the front of the waitlist
	public Student Dequeue() throws Exception {
		
		StudentNode stu = head.next;
		
		
		if(head.next == null) {
			throw new NullPointerException("Null");
		}
		head.next = head.next.next;
		return stu.student;
	}
	
	//writes the linked list into a test file to be saved
	public void writeList() throws IOException {
		StudentNode curr = head.next;
		FileWriter write = new FileWriter("C:/Users/parti/Desktop/eclipse-workspace/CS313 Project 3/stuWait.txt");
		
		while(curr != null) {
			write.write(curr.student +"\n");
			curr = curr.next;
		}
		write.close();
	}
	//Converts the elements in the array list to string and returns it to string
	public String toString() {
		StudentNode p = head.next;
		String returnString = "";
		while(p != null) {
			returnString += p.student +"\n";
			p=p.next;
		}
		return returnString;
	}
	
}
