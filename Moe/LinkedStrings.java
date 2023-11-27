package project;

public class LinkedStrings {
	private Node head;
	private Node tail;
	
	public LinkedStrings() {
		head = null;
		tail = null;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public void appendText(String str) {
		Node p = new Node(str);
		if(isEmpty()) {
			head = p;
			tail = p;
		}
		else {
			tail.setNext(p);
			tail = p;
			tail.setNext(null);
		}
	}
	
	public String undo() {
		if(isEmpty()) {
			System.out.println("There is no text to undo!");
			return "";
		}
		else if(head.getNext() == null) {
			String removedString = head.getText();
			head = null;
			tail = null;
			return removedString;
		}
		else {
			Node current = head;
			while(current.getNext() != tail) {
				current = current.getNext();
			}
			String removedString = tail.getText();
			current.setNext(null);
			tail = current;
			return removedString;
		}
	}
	
	public String getText() {
		if(isEmpty()) {
			return "";
		}
		else {
			Node current = head;
			String text = "";
			while(current.getNext() != null) {
				text += current.getText() + " ";
				current = current.getNext();
			}
			return text;
		}
	}
}
