package cu.edu.cujae.ceis.tree.iterators.general;

import java.util.ArrayDeque;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.general.GeneralTree;
import cu.edu.cujae.ceis.tree.iterators.ITreeIterator;

public class InBreadthIterator<E> implements ITreeIterator<E> {
	
	private ArrayDeque<BinaryTreeNode<E>> deque;
	private BinaryTreeNode<E> currentNode;
	private BinaryTreeNode<E> nextNode;
	GeneralTree<E> tree;
	
	//Constructor con parametros
	public InBreadthIterator(GeneralTree<E> tree) {	
		
		this.tree = tree;		
		currentNode = null;
		nextNode = (BinaryTreeNode<E>)tree.getRoot();
		deque = new ArrayDeque<BinaryTreeNode<E>>();
		
		if(nextNode != null)
			
			deque.addAll(tree.getSons(nextNode));
	
	}//fin del constructor

	//tiene siguiente
	public boolean hasNext() {	
		
		return nextNode != null;
	
	}//fin de la funcion hasNext

	//informacion del siguiente nodo
	public E next() {
		
		E returnInfo = null;
		currentNode = nextNode;
		
		if(nextNode != null){
			
			returnInfo = nextNode.getInfo();			
			
			if(deque.isEmpty())
				
				nextNode = null;
			
			else{
				
				nextNode = deque.poll();
				
				if(!tree.nodeIsLeaf(nextNode))
					
					deque.addAll(tree.getSons(nextNode));
			
			}//fin del else
		
		}//fin del if(nextNode != null)
		
		return returnInfo;
	
	}//fin de la funcion next 

	//nodo siguiente
	public BinaryTreeNode<E> nextNode(){
		
		currentNode = nextNode;
		
		if(nextNode != null){							
			
			if(deque.isEmpty())
				
				nextNode = null;
			
			else{
				
				nextNode = deque.poll();
				
				if(!tree.nodeIsLeaf(nextNode))
					
					deque.addAll(tree.getSons(nextNode));
			
			}//fin del else
		
		}//fin del if(nextNode != null)
		
		return currentNode;
	
	}//fin de la funcion nextNode
	
	//eliminar un nodo
	public void remove() {
		
		tree.deleteNode(currentNode);
	
	}//fin de la funcion remove

}//fin de la clase