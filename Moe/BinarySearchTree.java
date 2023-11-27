package project;

public class BinarySearchTree {
	private BstNode root;
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void insert(String k) {
		BstNode p = new BstNode(k);
		if(isEmpty()) {
			root = p;
		}
		else {
			insertIntoBinaryTree(k, root);
		}
	}
	
	private void insertIntoBinaryTree(String k, BstNode node) {
		BstNode parent = node;
		if(k.compareTo(node.getStr()) < 0) {
			node = node.getLeft();
			if(node == null) {
				parent.setLeft(new BstNode(k));
			}
			else {
				insertIntoBinaryTree(k, node);
			}
		}
		else if(k.compareTo(node.getStr()) > 0) {
			node = node.getRight();
			if(node == null) {
				parent.setRight(new BstNode(k));
			}
			else {
				insertIntoBinaryTree(k, node);
			}
		}
		else {
			node.incrementCount();
		}
	}
	
	public void display() {
		if(isEmpty()) {
			System.out.println("This tree is empty!");
		}
		else {
			displayTree(root, 0);
		}
	}
	
	private void displayTree(BstNode node, int lvl) {
		if(node != null) {
			displayTree(node.getRight(),lvl+1);
			for(int i = 1; i <= lvl; i++) {
				System.out.print("\t");
			}
			System.out.println(node.getStr());
			displayTree(node.getLeft(), lvl+1);
		}
	}
}
