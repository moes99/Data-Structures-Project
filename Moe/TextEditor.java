package project;

public class TextEditor extends LinkedStrings{
	private LinkedStrings removed;
	private BinarySearchTree bst;
	
	public TextEditor() {
		super();
		removed = new LinkedStrings();
		bst = new BinarySearchTree();
	}
	
	@Override
	public String undo() {
		String removedString = super.undo();
		if(!removedString.equals("")) {
			removed.appendText(removedString);
		}
		return removedString;
	}
	
	public void redo() {
		if(removed.isEmpty()) {
			System.out.println("You didn't undo any text!");
		}
		else {
			String removedString = removed.undo();
			appendText(removedString);
			bst.insert(removedString);
		}
	}
}
