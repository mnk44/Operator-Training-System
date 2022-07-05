package cu.edu.cujae.ceis.tree;

abstract public class TreeNode<E> {
	
	protected E info;
	
	//Constructor
	public TreeNode() {
		
		this.info = null;	
	
	}
	
	//Constructor con parametros
	public TreeNode(E info) {
		
		this.info = info;
	
	}
	
}
