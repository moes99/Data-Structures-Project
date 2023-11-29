package project;

public class FileNode {
	private TextEditor file;
	private FileNode next;
	private FileNode prev;
	
	public FileNode(TextEditor te) {
		file = te;
		next = null;
		prev = null;
	}

	public TextEditor getFile() {
		return file;
	}

	public void setFile(TextEditor file) {
		this.file = file;
	}

	public FileNode getNext() {
		return next;
	}

	public void setNext(FileNode next) {
		this.next = next;
	}

	public FileNode getPrev() {
		return prev;
	}

	public void setPrev(FileNode prev) {
		this.prev = prev;
	}
}
