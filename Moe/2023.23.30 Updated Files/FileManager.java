package project;

import java.util.Locale;
import java.util.Scanner;

public class FileManager {
	private FileNode head;
	private FileNode tail;
	private int openFileCount;
	
	public FileManager() {
		head = null;
		tail = null;
		openFileCount = 0;
	}
	
	public int getOpenFileCount() {
		return openFileCount;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public void createNewFile(TextEditor te) {
		FileNode fn = new FileNode(te);
		if(isEmpty()) {
			head = fn;
			tail = fn;
			head.setNext(tail);
			tail.setPrev(head);
		}
		else{
			tail.setNext(fn);
			fn.setPrev(tail);
			tail = fn;
		}
		openFileCount++;
	}
	
	public void closeFile(TextEditor openFile) {
		if(head.getFile() == openFile) {
			head = head.getNext();
			head.setPrev(null);
		}
		else if(tail.getFile() == openFile) {
			tail = tail.getPrev();
			tail.setNext(null);
		}
		else {
			FileNode current = head;
			while(current != null) {
				if(current.getFile() == openFile) {
					current.getPrev().setNext(current.getNext());
					current.getNext().setPrev(current.getPrev());
					break;
				}
			}
		}
		openFileCount--;
	}
	
	public void closeAll() {
		Scanner ui = new Scanner(System.in);
		while(!isEmpty()) {
			FileNode current = head;
			System.out.print("Do you want to save " + current.getFile().getSaveFileName() + " before closing? (Y/N) ");
			if(ui.next().equals("Y")) {
				current.getFile().saveToFile();
			}
			closeFile(current.getFile());
		}
		ui.close();
	}
	
	public TextEditor navigateToFile(String targetFile) {
		if(!isEmpty()) {
			FileNode current = head;
			while(current != null) {
				if(current.getFile().getSaveFileName().equals(targetFile)) {
					return current.getFile();
				}
			}
		}
		return null;
	}
	
	public String displayOpenFiles() {
		if(!isEmpty()) {
			String openFiles = "Currently open files:\n";
			FileNode current = head;
			for(int i = 1; i<= openFileCount; i++) {
				openFiles += String.format(Locale.US,"\t%d. %s\n",i,current.getFile().getSaveFileName());
				current = current.getNext();
			}
			return openFiles;
		}
		return "";
	}
}
