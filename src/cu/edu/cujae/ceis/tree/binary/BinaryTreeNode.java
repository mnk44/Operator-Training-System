package cu.edu.cujae.ceis.tree.binary;

import java.io.Serializable;
import cu.edu.cujae.ceis.tree.TreeNode;

public class BinaryTreeNode<E> extends TreeNode<E> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected BinaryTreeNode<E> left;
	protected BinaryTreeNode<E> right;
	
	//Constructor con parametros
	public BinaryTreeNode() {
		
		this.info = null;
		this.left = null;
		this.right = null;
	
	}
	
	//Constructor con parametros
	public BinaryTreeNode(E info) {
		
		this.info = info;
		this.left = null;
		this.right = null;
	
	}
	
	//**********************************Metodos get y set*****************************************
	public E getInfo() {
		
		return info;
	
	}
	
    public void setInfo(E info) {
		
    	this.info = info;
	
    }
    
    public BinaryTreeNode<E> getLeft() {
		
    	return left;
	
    }
    
    public void setLeft(BinaryTreeNode<E> left) {
		
    	this.left = left;
	
    }
    
    public BinaryTreeNode<E> getRight() {
		
    	return right;
	
    }
    
    public void setRight(BinaryTreeNode<E> right) {
		
    	this.right = right;
	
    }
    
}//fin de la clase
