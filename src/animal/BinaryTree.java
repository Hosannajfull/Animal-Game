package animal;

/*
 * Class should create a new binary tree with each question asked 
 * then when the game ends the binary tree should be printed as a string to a file
 * 
 * upon regeneration of game it should be regenerated into a tree
 * to do this I will explore Pasik's conversion of binary trees into linked lists 
 * and then printing the linked list into a toString into a file
 * from the file the list would be read in linked list order and then could be regenerated to have
 * the correct pointers lined up
 * 
 */


public class BinaryTree {

	private Comparable data;
	private BinaryTree left;
	private BinaryTree right;
	public final static BinaryTree NIL = new BinaryTree(null,null,null);

	public BinaryTree(Comparable data, BinaryTree left, BinaryTree right){
		this.data=data;
		this.left=left;
		this.right=right;
	}
	public BinaryTree(Comparable data){
		this(data, BinaryTree.NIL, BinaryTree.NIL);
	}
	public Comparable data(){ return this.data;}
	public BinaryTree left(){ return this.left;}
	public BinaryTree right(){ return this.right;}

	public void setData (Comparable d){ this.data = d; }
	public void setLeft (BinaryTree t){ this.right = t; }
	public void setRight (BinaryTree t){ this.left = t; }

	public boolean isEmpty(){
		return this == BinaryTree.NIL;
	}
	@SuppressWarnings("unchecked")
	public BinaryTree insert (BinaryTree newNode){
		return this.isEmpty() ? newNode :
			newNode.data().compareTo(this.data()) < 0 ?
					new BinaryTree(this.data(), this.left().insert(newNode),
							this.right()) :
								new BinaryTree(this.data(),
										this.left,
										this.right.insert(newNode));
	}
	//this is Binary Tree traversal
	private String elementsString (String order){
		if (this.isEmpty()) return "";
		String dataStr = this.data().toString();
		String leftStr = this.left().toString();
		String rightStr = this.right().toString();
		return order.equals("PreOrder") ? 
				dataStr + (leftStr != "" ? " " : "") +
				leftStr + (rightStr != "" ? " " : "" ) +
				rightStr :
					order.equals("inOrder") ?
							leftStr + (leftStr != "" ? " " : "") +
							dataStr + (rightStr != "" ? " " : "" ) +
							rightStr :
								order.equals("PostOrder") ?
										leftStr + (leftStr != "" ? " " : "") +
										rightStr + (rightStr != "" ? " " : "" ) +
										dataStr :
											"Uknown Ordering: " + order;

	}
	public boolean isLeaf(){
		return this.left()==NIL;
	}
	public String toString(){
		return this.elementsString("PreOrder"); //his code has Pre-order in parenthesis
	}
	public String toString(String order){
		return elementsString(order);
	}
	//formats the binary tree to be readable for file output 
	public String toFile(){	
		return (this.isLeaf()) ? (String) this.data() :
			String.format ("( %s, %s, %s,)",(String) this.data(), this.left().toFile(), this.right().toFile());
	}

}
