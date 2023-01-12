package project4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class BSTFile extends BST {

	@SuppressWarnings("resource")
	public BSTFile(BST roster) throws IOException {
		String fileName = "students.txt";	//sets variable filename = to fileName
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		String line = br.readLine();	//sets the variable line = to first line it sees in the file
		br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName))); 

		while((line = br.readLine())!=null && length != 20) { //as long as line is not empty space keep going
		String[] st = line.split(" "); //split the line by the whitespace
		Student temp = new Student(st[0], st[1], st[2]); //adds the elements of the line by pieces into the object student 
		
		length++;
		insert(temp);
		
			}
		br.close();
	}
}
