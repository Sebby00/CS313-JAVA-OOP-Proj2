package project4;

import java.util.Scanner;

public class Project4 {
	public static void main(String [] args) throws Exception {	
		BST BSTreadIn = new BST();
		BSTFile roster = new BSTFile(BSTreadIn);
		
		System.out.println(roster.largest);
		
		LinkedWaitList waitReader = new LinkedWaitList();
		StudentWaitList waitList = new StudentWaitList(waitReader);
		
		StudentNode tempHead = waitList.head;
		while((roster.getLength() != 20) &&(tempHead.next != null)) {
				roster.insert(waitList.Dequeue());
				roster.length++;
		}
		
		getUser(roster,waitList,roster.hashTable);//Method that allows interacts with the user telling them what they can do	
	}

	/**
	 * @param treeList
	 * @param waitList
	 * @throws Exception
	 */
	/**
	 * @param treeList
	 * @param waitList
	 * @throws Exception
	 */
	/**
	 * @param treeList
	 * @param waitList
	 * @param hashTable 
	 * @throws Exception
	 */
	private static void getUser(BST treeList, LinkedWaitList waitList, Student[] hashTable ) throws Exception {
		int stop = -1;
		int check = 0;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Scanner user = new Scanner(System.in);
		String first,last,id;
		
		while(stop != 0) {
			menu();
			int end = scanner.nextInt();
				//Exits the program
				if(end == 0) {
					System.out.println("Program Exied");
					user.close();
					break;
				}
				
				//Allows the user to add a student to the BST if its full prevents it
				if(end == 1) {
					//if the list is not full add student to the roster
					if(treeList.getLength() != 20) {	
						System.out.println("Enter First Name, then last name, then ID one at a time#\n");
						 first = user.nextLine();
						 last = user.nextLine();
						 id = user.nextLine();
						
						first = first.replaceAll("\\s", "");
						last = last.replaceAll("\\s", "");
						id = id.replaceAll("\\s", "");
				
						Student stu = new Student(first, last, id);
						treeList.insert(stu);
						treeList.inorderTraveral(treeList.root);
						treeList.length++;
					} 
					else {
						//if the roster is full add student to waitlist
						System.out.println("The list is full, this new student will be wait listed\n"
								+ "Enter First Name, Last Name and ID one at a time");
						 first = user.nextLine();
						 last = user.nextLine();
						 id = user.nextLine();
						 Student wait = new Student(first, last, id);
						waitList.Enqueue(wait);
						System.out.println("\nCurrent WaitList\n" + waitList.toString());
					}
				}
				
				//Allows the user to remove a student if there are none prevents user from using method
				if(end == 2) {
					if(treeList.getLength() != 0){
						System.out.println("Enter Student First Name,Last Name, and ID#");
						first = user.nextLine();
						 last = user.nextLine();
						 id = user.nextLine();
						
						first = first.replaceAll("\\s", "");
						last = last.replaceAll("\\s", "");
						id = id.replaceAll("\\s", "");
						
						Student stu = new Student(first, last, id);
					
						treeList.deleteStudent(stu, treeList.root);
						treeList.deleteIndex(hashTable, stu);
						
						if(treeList.getLength() != 20) {
						Student waitStu = waitList.Dequeue();
						treeList.insert(waitStu);
						treeList.length++;
						treeList.inorderTraveral(treeList.root);
						}						
					}
					else { 
						System.out.println("\nThere are no students to delete, try adding some.");
					}
						
				}
				
				//Allows the user to search for students by ID numbers
				if(end == 3) {
					System.out.println("Enter Student ID#");
					 id = user.nextLine();
					id = id.replaceAll("\\s", "");
					
					Student stu = new Student(null, null, id);
					treeList.listSearchID(treeList.root,stu);
				
					//if the returned null then the student being searched for is not in the roster
					if(treeList.BSTstu != null) {
						
						System.out.println("Student Found"+"\n");
						System.out.println(treeList.BSTstu.toString());
						treeList.BSTstu = null;
					}
					else
						System.out.println("Student Not Found" +"\n");
				}
				
				//use search method that make another method that calls it and can return a the student that it found
				if(end == 4) {
					System.out.println("Enter Student First Name and Last");
					first = user.nextLine();
					last  = user.nextLine();
					
					first = first.replaceAll("\\s", "");
					last = last.replaceAll("\\s", "");
					
					Student stu = treeList.newStudent(first, last);
					treeList.listSearchName(treeList.root, stu);
					
					if(treeList.BSTstu == null) {
						System.out.println("Student Not Found" +"\n");
					}
					if(treeList.BSTstu != null) {
						System.out.println("Student Found");
						treeList.BSTstu = null;
					}
					
				}
				
				//Saves the users students into an array
				if(end == 5 || end == 6) {
					check = 1;
					System.out.println("Saved into an array");
					Student[] array = new Student[treeList.getLength()];
					array = treeList.saveArray(treeList.root, array);
					treeList.index = 0;
				
					treeList.getLargest(treeList.root);
					hashTable = new Student[treeList.largest+1];
					
					treeList.hashFill(hashTable, array);
					for(int i = 0; i < hashTable.length; i++) {
						if(hashTable[i] != null) {
						System.out.println(hashTable[i]);
						}
					}
					//allows the user to save the students into a text file and load it up for use again
					if(end == 6) {
						treeList.saveFile(array);
						waitList.writeList();
						System.out.println("Successfully saved to file, GoodBye!");
						user.close();
						break;
					}
				}//Allows the user to check to see if a student is in the hashTable
				if(end == 7 && check == 0) {
					System.out.println("Please Save to array and hashTable first");
				} 
				if((end == 7) && (check == 1)) {
				id = user.nextLine();
				id = id.replaceAll("\\s", "");
				Student stu = new Student(null, null, id);
				treeList.hashSearch(stu,hashTable);
				}
				
		}
	} 
	//a method that can be called out print out a menu
	public static void menu() {
		System.out.println("(0) to exit"
				       + "\n(1) add student"
				       + "\n(2) remove student from list"
				       + "\n(3) search student by ID"
				       + "\n(4) search student by name"
				       + "\n(5) save to array and hashTable"
				       + "\n(6) save to file & exit program"
					   + "\n(7) search hashTable");
		
	}
}






