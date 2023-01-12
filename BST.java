package project4;

import java.io.FileWriter;
import java.io.IOException;

public class BST {
	class BSTnode{
		Student student;
		BSTnode left,right;
		
		public BSTnode(Student s) {
			student = s;
			left = null;
			right = null;
		}
	}
	protected Student BSTstu = null; 
	protected BSTnode root = null;
	protected int length = 0;
	protected int largest = 0;
	protected int index = 0;
	protected Student[] hashTable;
	
	public int getLength() {
		return length;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void insert(Student stu) {
		root = insert_Recurr(root, stu);
	}

	BSTnode insert_Recurr(BSTnode temp, Student stu) {
		if(temp == null) {
			temp = new BSTnode(stu);
		}
		else if(stu.compareTo(temp.student) < 0) {
			temp.left = insert_Recurr(temp.left, stu);
		}
		else {	
			temp.right = insert_Recurr(temp.right,stu);
		}
		return temp;
	}

	public void inorderTraveral(BSTnode top) {
		inorder_Recurr(top);	
	}

	private void inorder_Recurr(BSTnode top) {
		if(top != null) {
			inorder_Recurr(top.left);
			System.out.println(top.student + "");
			inorder_Recurr(top.right);
		}
	}

	public void getLargest(BSTnode top) {
		
		if(top != null) {
			String input = top.student.getID().substring(top.student.getID().length()-2);
			int num  = Integer.parseInt(input);
			
			getLargest(top.left);
			if(largest < num) {
				largest = num;
			}
			getLargest(top.right);
		}
	}
	
	public void deleteStudent(Student key, BSTnode top) {
		root = delete(key, top);
	}

    public void deleteIndex(Student[] hashArray,Student toDel) {
    	index = Integer.parseInt(toDel.getID().substring(toDel.getID().length()-2));
    	if(hashArray[index].getID().equals(toDel.getID())) {
    	hashArray[index] = null;
    	System.out.println("Student Removed From Hash");
		}
		System.out.println("Student Not found in Hash");
	}

	private BSTnode delete(Student s, BSTnode t) {
    	if(t == null) {
            System.out.println("Empty!!");
            return t;
        }
        else {
            if(s.compareTo(t.student) < 0)
                t.left = delete(s, t.left);
            else if(s.compareTo(t.student) > 0)
                t.right = delete(s, t.right);
            else {
                if(t.right == null)
                    return t.left;
                if(t.left == null)
                    return t.right;
                t.student = findMax(t.left);
                t.left = delete(t.student, t.left);
            }
        }
        return t;
    }

    private Student findMax(BSTnode left) {
		if(root == null) {
			 System.out.println("Empty!!");
			 return null;
		}
		BSTnode t = root;
		while(t.right != null) {
			t = t.right;
		}
		return t.student;
	}

	public void listSearchID(BSTnode top, Student key) {
		if(top != null) {
			listSearchID(top.left, key);
			if(top.student.getID().equals(key.getID())) {
				BSTstu = top.student;
				return;
			}
			listSearchID(top.right, key);
		}
	}

	public void listSearchName(BSTnode top, Student key) {
		if(top != null) {
			int x =	top.student.getfirstName().compareToIgnoreCase(key.getfirstName());
			int y = top.student.getlastName().compareToIgnoreCase(key.getlastName());
			
			listSearchName(top.left, key);
			if(x == 0 && y == 0) {
				BSTstu = top.student;
				return;
			}
			listSearchName(top.right, key);
		}
	}

	public Student[] saveArray(BSTnode top, Student[] Array) {
		if(top != null) {
			saveArray(top.left, Array);
			Array[index++] = top.student;
			saveArray(top.right, Array);
		}
		return Array;
	}
	
	public void saveFile(Student[] array) throws IOException {
		FileWriter writer = new FileWriter("C:/Users/parti/Desktop/eclipse-workspace/CS313 Project 3/students.txt");
		for(int i = 0; i < array.length; i++) {
			if(array[i] != null) {
			writer.write(array[i]+"\n");
			}
		}
		writer.close();
	}
	
	public Student newStudent(String first, String last){
		Student stu = new Student(first,last,null);
		return stu;
	}

	
	public void hashFill(Student[] hashTable, Student[] array) {
		for(int i = 0; i < array.length; i++) {
			int position = Integer.parseInt(array[i].getID().substring(array[i].getID().length()-2));
			hashTable[position] = array[i];
		}
		largest = 0;
	}
		
		boolean hashSearch(Student stu, Student []hashTable) {
			int lastTwo = Integer.parseInt(stu.getID().substring(stu.getID().length() - 2));
			if(stu.equals(hashTable[lastTwo])) {
				System.out.println("Student Found");
				return true;
			}
			System.out.println("Student Not Found");
			return false;
	}
	
}
