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
	
	public void remove(String k) {
		if(!isEmpty()) {
			remove(k,root);
		}
	}
	
	private void remove(String k, BstNode node) {
		if(node != null) {
			if(k.equals(node.getStr())) {
				node.decrementCount();
			}
			else if(k.compareTo(node.getStr()) < 0) {
				remove(k,node.getLeft());
			}
			else {
				remove(k,node.getRight());
			}
		}
	}
	
	public void replace(String oldWord, String newWord) {
		insert(newWord);
		remove(oldWord);
	}
	
//	public void display() {
//		if(isEmpty()) {
//			System.out.println("This tree is empty!");
//		}
//		else {
//			displayTree(root, 0);
//		}
//	}
	
//	private void displayTree(BstNode node, int lvl) {
//		if(node != null) {
//			displayTree(node.getRight(),lvl+1);
//			for(int i = 1; i <= lvl; i++) {
//				System.out.print("\t");
//			}
//			System.out.println(node.getStr()+"("+node.getCount()+")");
//			displayTree(node.getLeft(), lvl+1);
//		}
//	}
	
	public int searchTree(String str) {
		if(isEmpty()) {
			return 0;
		}
		else {
			return searchTree(root, str);
		}
	}
	
	private int searchTree(BstNode node, String str) {
		if(node.getStr().equals(str)) {
			return node.getCount();
		}
		else if(str.compareTo(node.getStr()) < 0) {
			node = node.getLeft();
			if(node == null) {
				return 0;
			}
			else {
				return searchTree(node, str);
			}
		}
		else {
			node = node.getRight();
			if(node == null) {
				return 0;
			}
			else {
				return searchTree(node, str);
			}
		}
	}
}
