package cu.edu.cujae.ceis.tree.general;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.ceis.tree.Tree;
import cu.edu.cujae.ceis.tree.TreeNode;
import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.iterators.general.BreadthNode;
import cu.edu.cujae.ceis.tree.iterators.general.InBreadthIterator;
import cu.edu.cujae.ceis.tree.iterators.general.InBreadthIteratorWithLevels;
import cu.edu.cujae.ceis.tree.iterators.general.InDepthIterator;

public class GeneralTree<E> extends Tree<E> implements Serializable{	
	
	private static final long serialVersionUID = 1L;

	//Constructor
	public GeneralTree(){
		
		super();
	
	}

	//Constructor con parametros
	public GeneralTree(BinaryTreeNode<E> root){
		
		super(root);
	
	}

	//total de nodos
	public int totalNodes(){
		
		int count = 0;

		//iterador para recorrer el arbol a
		InDepthIterator<E> iterator = inDepthIterator();		

		while(iterator.hasNext()){
			
			iterator.next();
			count++;			
		
		}		

		return count;
	
	}//fin de la funcion totalNodes 

	//eliminar un nodo
	public E deleteNode(BinaryTreeNode<E> node){
		
		E info = null;

		if(node != null){	
			
			if(node.equals(root))
				
				root = null;
			
			else{
				
				//iterador para recorrer el arbol a 
				InDepthIterator<E> iterator = inDepthIterator();

				boolean foundedNode = false;

				while(iterator.hasNext() && !foundedNode){
					
					BinaryTreeNode<E> father = iterator.nextNode();

					//search if node its son of father
					if(father.getLeft() != null){
						
						if(father.getLeft().equals(node)){
							
							foundedNode = true;
							father.setLeft(node.getRight());
						
						}//fin del if(father.getLeft().equals(node))
						
						else{
							
							BinaryTreeNode<E> prev = father.getLeft();
							BinaryTreeNode<E> cursor = prev.getRight();

							while(cursor != null && !foundedNode){
								
								if(cursor.equals(node)){
									
									foundedNode = true;
									prev.setRight(cursor.getRight());
								
								}//fin del if(cursor.equals(node))
								
								else{
									
									prev = cursor;
									cursor = cursor.getRight();
								
								}//fin del else
							
							}//fin del while(cursor != null && !foundedNode)
							
						}//fin del else
						
					}//fin del if(father.getLeft() != null)				
				
				}//fin del while(iterator.hasNext() && !foundedNode)

				if(foundedNode)
					
					info = node.getInfo();
			
			}//fin del else
		
		}//fin del if(node != null)

		return info;
	
	}//fin de la funcion deleteNode 

	//padre de un nodo
	public BinaryTreeNode<E> getFather(BinaryTreeNode<E> node){
		
		BinaryTreeNode<E> father = null;

		if(node != null && !isEmpty() || !root.equals(node)){
			
			//iterador para recorrer el arbol a 
			InDepthIterator<E> iterator = inDepthIterator();

			boolean foundedNode = false;

			while(iterator.hasNext() && !foundedNode){
				
				BinaryTreeNode<E> cursor = iterator.nextNode();

				if (node.equals(((BinaryTreeNode<E>) cursor).getLeft())){ 
					
					father = cursor;
					foundedNode = true;
				
				}
				
				else{
					
					if(cursor.getLeft() != null){
						
						BinaryTreeNode<E> aux = cursor.getLeft();

						while(aux.getRight() != null && !foundedNode){
							
							aux = aux.getRight();

							if(aux.equals(node)){
								
								foundedNode = true;
								father = cursor;
							
							}//fin del if(aux.equals(node))												
						
						}//fin del while(aux.getRight() != null && !foundedNode)
					
					}//fin del if(cursor.getLeft() != null)
				
				}//fin del else
				
			}//fin del while(iterator.hasNext() && !foundedNode)		
		
		}//fin del if(node != null && !isEmpty() || !root.equals(node))

		return father;
	
	}//fin de la funcion getFather 

	//
	public List<TreeNode<E>> getLeaves(){
		
		ArrayList<TreeNode<E>> leavesList = new ArrayList<TreeNode<E>>();

		if(!isEmpty()){	
			
			//iterador para recorrer el arbol a 
			InDepthIterator<E> iterator = inDepthIterator();

			while(iterator.hasNext()){
				
				BinaryTreeNode<E> node = iterator.nextNode();

				if(((BinaryTreeNode<E>) node).getLeft() == null)
					
					leavesList.add(node);
			
			}//fin del while(iterator.hasNext())						
		
		}//fin del if(!isEmpty())

		return leavesList;
	
	}//fin de la funcion getLeaves

	//los hijos de un nodo
	public List<BinaryTreeNode<E>> getSons(BinaryTreeNode<E> node){
		
		List<BinaryTreeNode<E>> sonsList = new ArrayList<BinaryTreeNode<E>>();

		if(node != null){
			
			if(node.getLeft() != null){
				
				sonsList.add(node.getLeft());
				
				if(node.getLeft().getRight() != null){
					
					node = node.getLeft();
					
					while(node.getRight() != null){
						
						sonsList.add(node.getRight());
						node = node.getRight();
					
					}//fin del while(node.getRight() != null)
				
				}//fin del if(node.getLeft().getRight() != null)				
			
			}//fin del if(node.getLeft() != null)			
		
		}//fin del if(node != null)

		return sonsList;
	
	}//fin de la funcion getSons

	//la informacion de los hijos de un nodo
	public List<E> getSonsInfo(BinaryTreeNode<E> node){
		
		List<E> sonsInfoList = new ArrayList<E>();

		if(node != null){
			
			if(node.getLeft() != null){
				
				sonsInfoList.add(node.getLeft().getInfo());

				if(node.getLeft().getRight() != null){
					
					node = node.getLeft();
					
					while(node.getRight() != null){
						
						sonsInfoList.add((E) node.getRight().getInfo());
						node = node.getRight();
					
					}//fin del while(node.getRight() != null)
				
				}//fin del if(node.getLeft().getRight() != null)
			
			}//fin del if(node.getLeft() != null)
		
		}//fin del if(node != null)

		return sonsInfoList;
	
	}//fin de la funcion getSonsInfo	

	//insertar un nodo
	public boolean insertNode(BinaryTreeNode<E> node, BinaryTreeNode<E> father){
		
		boolean inserted = false;

		if(node != null){
			
			if(isEmpty()){
				
				if(father == null){
					
					setRoot(node);
					inserted = true;
				
				}//fin del if(father == null) 			
			
			}//fin del if(isEmpty()) 
			
			else{
				
				if(father != null){							
					
					//iterador para recorrer el arbol a 
					InDepthIterator<E> iterator = inDepthIterator();

					boolean stop = false;

					while(iterator.hasNext() && !stop){
						
						BinaryTreeNode<E> iterNode = iterator.nextNode();

						if(iterNode.equals(father)){
							
							stop = true;
							BinaryTreeNode<E> cursor = father.getLeft();

							if(cursor == null){
								
								father.setLeft(node);
							
							}//fin del if(cursor == null) 
							
							else{
								
								while(cursor.getRight() != null){
									
									cursor = cursor.getRight();
								
								}//fin del while(cursor.getRight() != null)
								
								cursor.setRight(node);
							
							}//fin del else
						
						}//fin del if(iterNode.equals(father))

						inserted = true;
					
					}//fin del while(iterator.hasNext() && !stop)				
				
				}//fin del if(father != null) 
				
				else{
					
					if(((BinaryTreeNode<E>) root).getRight() != null){
						
						BinaryTreeNode<E> cursor = ((BinaryTreeNode<E>) root).getRight();

						while(cursor.getRight() != null){
							
							cursor = cursor.getRight();
						
						}//fin del while(cursor.getRight() != null)
						
						cursor.setRight(node);
					
					}//fin del if(((BinaryTreeNode<E>) root).getRight() != null) 
					
					else {
						
						((BinaryTreeNode<E>) root).setRight(node);
					
					}//fin del else

					inserted = true;
				
				}//fin del else
			
			}//fin del else
		
		}//fin del if(node != null)

		return inserted;
	
	}//fin de la funcion insertNode

	//insertar un nodo como primer hijo
	public boolean insertAsFirstSon(BinaryTreeNode<E> node, BinaryTreeNode<E> father){		
		
		boolean founded = false;

		if(node != null && father != null){
			
			//iterador para recorrer el arbol a 
			InDepthIterator<E> iter = inDepthIterator();

			while(iter.hasNext() && ! founded){
				
				BinaryTreeNode<E> elem = iter.nextNode();

				if(father.equals(elem)){
					
					founded = true;

					if(father.getLeft() == null)
						
						father.setLeft(node);
					
					else{
						
						BinaryTreeNode<E> h = father.getLeft();
						node.setRight(h);
						father.setLeft(node);
					
					}//fin del else				
				
				}//fin del if(father.equals(elem))
			
			}//fin del while(iter.hasNext() && ! founded)
		
		}//fin del if(node != null && father != null)

		return founded;		
	
	}//fin de la funcion insertAsFirstSon

	//
	public int nodeLevel(TreeNode<E> node){
		
		int level = -1;

		if(node != null){
			
			if(node.equals(root))
				
				level = 0;
			
			else{
				
				//iterador para recorrer el arbol a 
				InBreadthIteratorWithLevels<E> iter = inBreadthIteratorWithLevels();

				boolean found = false;			

				while(iter.hasNext() && !found){
					
					BreadthNode<E> cursor =  iter.nextNodeWithLevel();

					if(cursor.getNode().equals(node)){
						
						found = true;
						level = cursor.getLevel();
					
					}//fin del if(cursor.getNode().equals(node))
				
				}//fin del while(iter.hasNext() && !found)
			
			}//fin del else
		
		}//fin del if(node != null)
		
		return level;
	
	}//fin de la funcion nodeLevel

	public int treeLevel(){				
		
		return nodeLevel(root);
	
	}

	//
	public boolean nodeIsLeaf(TreeNode<E> node){
		
		if(node != null)
			
			return ((BinaryTreeNode<E>)node).getLeft() == null;

		return false;
	
	}//fin de la funcion nodeIsLeaf

	//
	public int nodeDegree (TreeNode<E> node){
		
		int degree = -1;

		if(node != null){
			
			degree = 0;

			if(((BinaryTreeNode<E>)node).getLeft () != null) 
				
				degree = 1 + rightBrotherCount(((BinaryTreeNode<E>)node).getLeft());
		
		}//fin del if(node != null)
		
		return degree;
	
	}//fin de la funcion nodeDegree 

	//Contar los hermanos de un nodo
	@SuppressWarnings("rawtypes")
	private int rightBrotherCount(BinaryTreeNode node){
		
		int brother = 0;
		
		if (node.getRight() != null) 
			
			brother = 1 + rightBrotherCount(node.getRight());
		
		return brother;
	
	}//fin de la funcion rightBrotherCount

	//*****************************Iteradores para los arboles***********************************
	public InDepthIterator<E> inDepthIterator(){
		
		return new InDepthIterator<E>(this);
	
	}

	public InBreadthIterator<E> inBreadthIterator(){
		
		return new InBreadthIterator<E>(this);
	
	}

	public InBreadthIteratorWithLevels<E> inBreadthIteratorWithLevels(){
		
		return new InBreadthIteratorWithLevels<E>(this);
	
	}

	//
	public int treeHeight(){
		
		int height = -1;
		InBreadthIteratorWithLevels<E> iter = inBreadthIteratorWithLevels();
		BreadthNode<E> lastNode = null;

		while(iter.hasNext())
			
			lastNode = iter.nextNodeWithLevel();

		if(lastNode != null)	
			
			height = lastNode.getLevel();

		return height;
	
	}//fin de la funcion treeHeight 

}//fin de la clase
































