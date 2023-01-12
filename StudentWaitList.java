package project4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class StudentWaitList extends LinkedWaitList{
	
	@SuppressWarnings("resource")
	//reads in students from a textfile then creates a node which then gets inserted into a linked list
	public StudentWaitList(LinkedWaitList list) throws IOException {
		String fileName = "stuWait.txt";	//sets variable filename = to fileName
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		String line = br.readLine();	//sets the variable line = to first line it sees in the file
		br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName))); 

		while((line = br.readLine())!=null) { //as long as line is not empty space keep going
		String[] st = line.split(" "); //split the line by the whitespace
		Student temp = new Student(st[0], st[1], st[2]); //adds the elements of the line by pieces into the object student 
		
		Enqueue(temp);
			}
		br.close();
		System.out.println("Students read in from File\n");
	}

}
