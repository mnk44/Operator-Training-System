package cu.edu.cujae.ceis.tree.iterators.binary;

import java.util.ArrayDeque;
import cu.edu.cujae.ceis.tree.Tree;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.iterators.ITreeIterator;
import cu.edu.cujae.ceis.tree.iterators.StackNode;

public class PosOrderIterator<E> implements ITreeIterator<E>{
	
	private StackNode<E> nextNode;
	private BinaryTreeNode<E> currentNode;
	private Tree<E> tree;	
	private ArrayDeque<StackNode<E>> stack;

	//Constructor con parametros
	public PosOrderIterator(Tree<E> tree){
		
		this.tree = tree;
		stack = new ArrayDeque<StackNode<E>>();
		this.currentNode = null;
		this.nextNode = null;
		
		if(!tree.isEmpty())		
			
			this.nextNode = new StackNode<E>(moveCursorToLastLeftOrRightNode((BinaryTreeNode<E>)tree.getRoot()));
		
		this.tree = tree;	
	
	}

	//nodo siguiente
	public BinaryTreeNode<E> nextNode(){
		
		currentNode = null;

		if(nextNode != null){
			
			currentNode = nextNode.getNode();

			if(nextNode.getRight() != null && nextNode.getCount() != 2){					
				
				nextNode.incrementCount();
				nextNode.incrementCount();
				stack.push(nextNode);
				nextNode = new StackNode<E>(moveCursorToLastLeftOrRightNode(nextNode.getRight()));
			
			}
			
			else{ 
				
				nextNode = null;

				if(!stack.isEmpty()){					
					
					StackNode<E> father = stack.pop();
					nextNode = father;

					if(father.getCount() == 1 && father.getRight() != null){
						
						father.incrementCount();
						stack.push(father);
						nextNode = new StackNode<E>(moveCursorToLastLeftOrRightNode(father.getRight()));
					
					}//fin del if(father.getCount() == 1 && father.getRight() != null) 
				
				}//fin del if(!stack.isEmpty())
			
			}//fin del else
		
		}//fin del if(nextNode != null)

		return currentNode;
	
	}//fin de la funcion nextNode

	//tiene siguiente
	public boolean hasNext(){
		
		return nextNode != null;
	
	}//fin de la funcion hasNext

	//informacion del siguiente nodo
	public E next(){
		
		E currentInfo = null;
		BinaryTreeNode<E> current = nextNode();

		if(current != null)
			
			currentInfo = current.getInfo();
		
		return currentInfo;
	
	}//fin de la funcion next 

	//eliminar un nodo
	public void remove(){
		
		tree.deleteNode(currentNode);
	
	}//fin de la funcion remove

	//
	private BinaryTreeNode<E> moveCursorToLastLeftOrRightNode(BinaryTreeNode<E> initialNode){
		
		BinaryTreeNode<E> cursor = initialNode;

		while(cursor.getLeft() != null){
			
			StackNode<E> node = new StackNode<E>(cursor);			
			node.incrementCount();
			stack.push(node);
			cursor = cursor.getLeft();
		
		}//fin del while(cursor.getLeft() != null)

		if(cursor.getRight() != null){
			
			StackNode<E> stackNode = new StackNode<E>(cursor);
			stackNode.incrementCount();
			stackNode.incrementCount();
			stack.push(stackNode);
			cursor = moveCursorToLastLeftOrRightNode(cursor.getRight());
		
		}//fin del if(cursor.getRight() != null)
		
		return cursor;
	
	}//fin de la funcion moveCursorToLastLeftOrRightNode
	
}//fin de la clase