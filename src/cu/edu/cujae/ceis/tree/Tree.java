package cu.edu.cujae.ceis.tree;

import java.util.List;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;

abstract public class Tree<E> {

	protected TreeNode<E> root;
	
	//Constructor
	public Tree() {
		
		root = null;
	
	}

	//Constructor con parametros
	public Tree(TreeNode<E> root) {
		
		this.root = root;
	
	}

	//**********************************Metodos get y set*****************************************
	public TreeNode<E> getRoot() {
		
		return root;
	
	}

	public void setRoot(TreeNode<E> root) {
		
		this.root = root;
	
	}

	//Ver si el arbol esta vacio, no tiene raiz
	public boolean isEmpty() {
		
		return root == null;
	
	}
	
	//*******************************Funciones abstractas************************************
	//Total de nodos del arbol
	public abstract int totalNodes();
	
	//
	public abstract List<TreeNode<E>> getLeaves();
	
	//el nivel de un nodo
	public abstract int nodeLevel(TreeNode<E> node);
	
	//el nivel de un arbol
	public abstract int treeLevel();
	
	public abstract int treeHeight();
	
	public abstract boolean nodeIsLeaf(TreeNode<E> node);
	
	//el grado de un nodo
	public abstract int nodeDegree(TreeNode<E> node);
	
	//elimina un nodo
	public abstract E deleteNode(BinaryTreeNode<E> node);	

}//fin de la clase 
