package cu.edu.cujae.ceis.tree.iterators.binary;

import java.util.ArrayDeque;
import cu.edu.cujae.ceis.tree.Tree;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.iterators.ITreeIterator;
import cu.edu.cujae.ceis.tree.iterators.StackNode;

public class PreorderIterator<E> implements ITreeIterator<E>{
	
	private BinaryTreeNode<E> nextNode;
	private BinaryTreeNode<E> currentNode;
	private Tree<E> tree;	
	private ArrayDeque<StackNode<E>> stack;

	//Constructor con parametros
	public PreorderIterator(Tree<E> tree) {
		
		this.currentNode = null;
		stack = new ArrayDeque<StackNode<E>>();
		this.nextNode = (BinaryTreeNode<E>) tree.getRoot();
		this.tree = tree;					
	
	}

	//tiene siguiente
	public boolean hasNext() {
		
		return nextNode != null; 			
	
	}//fin de la funcion hasNext

	//informacion del siguiente nodo
	public E next() {
		
		E currentInfo = null;
		BinaryTreeNode<E> current = nextNode();
		
		if(current != null)
			
			currentInfo = current.getInfo();
		
		return currentInfo;		
	
	}//fin de la funcion next 

	//eliminar un nodo
	public void remove() {
		
		if (currentNode != null)
			
			tree.deleteNode(currentNode);
	
	}//fin de la funcion remove
	
	//nodo siguiente
	public BinaryTreeNode<E> nextNode() {
		
		BinaryTreeNode<E> returnNode = nextNode;
		currentNode = nextNode;
		
		if(nextNode != null){				
			
			if(nextNode.getLeft() != null){
				
				StackNode<E> newStackNode = new StackNode<E>(nextNode); 
				newStackNode.incrementCount();
				stack.push(newStackNode);
				nextNode = nextNode.getLeft();
			
			}//fin del if(nextNode.getLeft() != null)
			
			else{								
				
				if(nextNode.getRight() != null){
					
					StackNode<E> newStackNode = new StackNode<E>(nextNode); 
					newStackNode.incrementCount();
					stack.push(newStackNode);
					StackNode<E> node = stack.pop();
					node.incrementCount();
					stack.push(node);											
					nextNode = nextNode.getRight();
				
				}//fin del if(nextNode.getRight() != null)
				
				else{										
					
					boolean foundedNextNode = false;
					
					while(!stack.isEmpty() && !foundedNextNode){
						
						StackNode<E> father = stack.pop();
						
						if(father.getRight() != null && father.getCount() == 1){
							
							foundedNextNode = true;
							nextNode = father.getRight();
							father.incrementCount();
							stack.push(father);
						
						}//fin del if(father.getRight() != null && father.getCount() == 1)						
					
					}//fin del while(!stack.isEmpty() && !foundedNextNode)
						
					if(!foundedNextNode)
						
						nextNode = null;						
				
				}//fin del else
				
			}//fin del else
			
		}//fin del if(nextNode != null)
		
		return returnNode;
	
	}//fin de la funcion nextNode	

}//fin de la clase