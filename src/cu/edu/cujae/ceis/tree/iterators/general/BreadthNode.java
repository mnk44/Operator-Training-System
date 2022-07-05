package cu.edu.cujae.ceis.tree.iterators.general;

import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;

public class BreadthNode<E>{
	
	private BinaryTreeNode<E> node;
	private int level;
	
	//Constructor con parametros
	public BreadthNode(BinaryTreeNode<E> node) {		
		
		this.node = node;
		level = 0;
	
	}

	//Constructor con parametros
	public BreadthNode(BinaryTreeNode<E> node, int fatherLevel) {		
		
		this.node = node;
		level = fatherLevel + 1;
	
	}
	
	//**********************************Metodos get y set*****************************************
	public int getLevel() {
		
		return level;
	
	}

	public void setLevel(int level) {
		
		this.level = level;
	
	}

	public BinaryTreeNode<E> getNode() {
		
		return node;
	
	}

	public void setNode(BinaryTreeNode<E> node) {
		
		this.node = node;
	
	}

	public E getInfo(){
		
		return node.getInfo();
	
	}
	
}//fin de la clase