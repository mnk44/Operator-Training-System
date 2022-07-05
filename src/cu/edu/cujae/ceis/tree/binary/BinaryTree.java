package cu.edu.cujae.ceis.tree.binary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.ceis.tree.Tree;
import cu.edu.cujae.ceis.tree.TreeNode;
import cu.edu.cujae.ceis.tree.iterators.binary.PosOrderIterator;
import cu.edu.cujae.ceis.tree.iterators.binary.PreorderIterator;
import cu.edu.cujae.ceis.tree.iterators.binary.SymmetricIterator;

public class BinaryTree<E> extends Tree<E> implements Serializable{

	private static final long serialVersionUID = 1L;

	//Constructor
	public BinaryTree(){
		
		super();
	
	}

	//Constructor con parametros
	public BinaryTree(TreeNode<E> root){
		
		super(root);
	
	}

	//Constructor con parametros
	public BinaryTree(BinaryTreeNode<E> root){
		
		this.root = root;
	
	}
	
	//**********************************Metodos get y set*****************************************
	public TreeNode<E> getRoot(){
			
		return root;
			
	}
	
	//
	protected int level(BinaryTreeNode<E> cursor){
		
		if(cursor != null){
			
			//el level de la izquierda
			int levelLST = level(cursor.getLeft());
			//el level de la derecha
			int levelRST = level(cursor.getRight());
			
			//si se cumple lo que esta dentro del parantesis devuelvo levelLST si no devuelvo levelRST
			return ((levelLST >= levelRST) ? levelLST : levelRST) + 1;
		
		}//fin del if (cursor != null)
		
		return -1;
	
	}//fin de la funcion level

	public int treeLevel(){
		
		int level = -1;
		
		if(root != null)
			
			level = 0;
		
		return level;
	
	}//fin de la funcion treeLevel

	//el level de un nodo
	public int nodeLevel(TreeNode<E> node){
		
		if(node != null){
			
			//si el nodo es la raiz devuelve cero sino devuelve el level del padre + 1 que seria el de el
			return node.equals(root) ? 0 : nodeLevel(getFather((BinaryTreeNode<E>)node)) + 1;
		
		}
		
		return -1;
	
	}//fin de la funcion nodeLevel		

	//eliminar un nodo
	public E deleteNode(BinaryTreeNode<E> node){
		
		if(node != null){
			
			//si es la raiz
			if(root != null && root.equals(node)){
				
				//elimino la raizs
				this.root = null;
			
			} 
			
			else{
				
				//le pido el padre al nodo que quiero eliminar
				BinaryTreeNode<E> father = getFather(node);
				
				//invoco a la funcion que es la que me elimina el nodo
				deleteNotRoot(node, father);
			
			}//fin del else
			
			return node.getInfo();
		
		}//fin del if(node != null)
		
		return null;
	
	}//fin de la funcion deleteNode

	//eliminar un nodo que no es raiz
	private void deleteNotRoot(BinaryTreeNode<E> node, BinaryTreeNode<E> father){
		
		if(node != null && father != null){			
			
			//ver si el nodo es hijo izquierdo del padre
			if(father.getLeft() != null && father.getLeft().equals(node))
				
				//quito el hijo izquierdo del padre
				father.setLeft(null);
			
			else
				
				//ver si el nodo es hijo derecho del padre
				if(father.getRight() != null && father.getRight().equals(node))
					
					//quito el hijo derecho del padre
					father.setRight(null);			
		
		}//fin del if if(node != null && father != null) 
	
	}//fin de la funcion deleteNotRoot

	@SuppressWarnings("unused")
	private boolean isAllNodesDegreeTwo(){
		
		//iterador para recorrer el arbol en preorden
		PreorderIterator<E> iterator = preOrderIterator();

		boolean stop = false;

		while(iterator.hasNext() && !stop){
			
			BinaryTreeNode<E> node = iterator.nextNode();

			if(nodeDegree(node) == 1)
				
				stop = true;
		
		}//fin del while(iterator.hasNext() && !stop)

		return !stop;
	
	}//fin de la funcion isAllNodesDegreeTwo

	//
	public int nodeDegree(TreeNode<E> node){
		
		int degree = 0;

		if(((BinaryTreeNode<E>)node).getLeft() != null)
			
			degree++;

		if(((BinaryTreeNode<E>)node).getRight() != null)
			
			degree++;

		return degree;
	
	}//fin de la funcion nodeDegree
	
	//el padre de un nodo
	public BinaryTreeNode<E> getFather(BinaryTreeNode<E> node){
		
		BinaryTreeNode<E> returnNode = null;

		if(node != null && !node.equals(root)){							
			
			//iterador para recorrer el arbol en preorden
			PreorderIterator<E> iterator = preOrderIterator();

			boolean stop = false;

			while(iterator.hasNext() && !stop){
				
				BinaryTreeNode<E> iterNode = iterator.nextNode();

				if((node.equals(((BinaryTreeNode<E>)iterNode).getLeft())) || (node.equals(((BinaryTreeNode<E>)iterNode).getRight()))){
					
					stop = true;
					returnNode = iterNode;
				
				}
			
			}//fin del while(iterator.hasNext() && !stop)							
		
		}//fin del if(node != null && !node.equals(root)) 		

		return returnNode;
	
	}//fin de la funcion getFather

	@SuppressWarnings("unused")
	//
	private BinaryTreeNode<E> firstIncompletedNode(BinaryTreeNode<E> cursor){			
		
		BinaryTreeNode<E> searchedNode = null;
		
		//iterador para recorrer el arbol en preorden
		PreorderIterator<E> iterator = preOrderIterator();

		boolean stop = false;

		while(iterator.hasNext() && !stop){
			
			BinaryTreeNode<E> node = iterator.nextNode();

			if(nodeDegree(node) < 2){
				
				stop = true;
				searchedNode = node;
			
			}//fin del if(nodeDegree(node) < 2)
		
		}//fin del while(iterator.hasNext() && !stop)

		return searchedNode;
	
	}//fin de la funcion firstIncompletedNode

	//
	public List<TreeNode<E>> getLeaves(){
		
		List<TreeNode<E>> leavesList = new ArrayList<TreeNode<E>>();

		//iterador para recorrer el arbol en preorden
		PreorderIterator<E> iterator = preOrderIterator();

		while(iterator.hasNext()){
			
			BinaryTreeNode<E> node = iterator.nextNode();

			if(node.getLeft() == null && node.getRight() == null) 
				
				leavesList.add(node);
		
		}//fin del while(iterator.hasNext())

		return leavesList;
	
	}//fin de la funcion getLeaves

	@SuppressWarnings("unused")
	//
	private void getNodeSubTree(BinaryTreeNode<E> root, BinaryTreeNode<E> node, BinaryTree<E> tree){
		
		if(root != null && !root.equals(node)){
			
			BinaryTreeNode<E> cursor = new BinaryTreeNode<E>(root.getInfo());

			if(root.getLeft() != null && !root.getLeft().equals(node)){
				
				getNodeSubTree(root.getLeft(), node, tree);
				cursor.setLeft((BinaryTreeNode<E>) tree.getRoot());
			
			}//fin del if (root.getLeft() != null && !root.getLeft().equals(node)) 
			
			else{
				
				cursor.setLeft(null);
			
			}//fin del else
			
			if(root.getRight() != null && root.getRight().equals(node)){
				
				getNodeSubTree(root.getRight(), node, tree);
				cursor.setRight((BinaryTreeNode<E>) tree.getRoot());
			
			}//fin del if(root.getRight() != null && root.getRight().equals(node))
			
			else{
				
				cursor.setRight(null);
			
			}//fin del else

			tree.setRoot(cursor);
		
		}//fin del if (root != null && !root.equals(node))
	
	}//fin de la funcion getNodeSubTree

	//hijos de un nodo
	public List<BinaryTreeNode<E>> getSons(BinaryTreeNode<E> node){
		
		List<BinaryTreeNode<E>> sons = new ArrayList<BinaryTreeNode<E>>();

		if(node != null){
			
			if(node.getLeft() != null){
				
				//agrego el hijo izquierdo del nodo
				sons.add(node.getLeft());
			
			}//fin del if(node.getLeft() != null)
			
			if(node.getRight() != null){
				
				//agrego el hijo derecho del nodo
				sons.add(node.getRight());
			
			}//fin del if(node.getRight() != null)
		
		}//fin del if (node != null)
		
		return sons;
	
	}//fin de la funcion getSons

	//lo que queda del arbol a partir de un nodo
	public BinaryTree<E> getSubTree(BinaryTreeNode<E> node){
		
		BinaryTree<E> tree = null;
		
		if(node != null){
			
			//iterador para recorrer el arbol en preorden
			PreorderIterator<E> iter = preOrderIterator();
			boolean found = false;
			
			while(iter.hasNext() && !found){
				
				BinaryTreeNode<E> cursor = iter.nextNode();
				
				if(cursor.equals(node)){
					
					found = true;
					
					BinaryTreeNode<E> newRoot = new BinaryTreeNode<E>(node.getInfo());
					
					buildSubTree(node, newRoot);	
					
					tree = new BinaryTree<E>(newRoot);
				
				}//fin del if(cursor.equals(node))
			
			}//fin del while(iter.hasNext() && !found)
			
		}//fin del if(node != null)
					
		return tree;
	
	}//fin de la funcion getSubTree
	
	//Construir un arbol a partir de un nodo
	private void buildSubTree(BinaryTreeNode<E> srcFather, BinaryTreeNode<E> newFather){
		
		if(srcFather.getLeft() != null){
			
			BinaryTreeNode<E> newLeft = new BinaryTreeNode<E>(srcFather.getLeft().getInfo());
			
			newFather.setLeft(newLeft);
			
			buildSubTree(srcFather.getLeft(), newFather.getLeft());
		
		}//fin del if(srcFather.getLeft() != null)
		
		if(srcFather.getRight() != null){
			
			BinaryTreeNode<E> newRight = new BinaryTreeNode<E>(srcFather.getRight().getInfo());
			
			newFather.setRight(newRight);
			
			buildSubTree(srcFather.getRight(), newFather.getRight());
		
		}//fin del if(srcFather.getRight() != null)
	
	}//fin de la funcion buildSubTree

	//insertar un nodo
	public boolean insertNode(BinaryTreeNode<E> node, char type, BinaryTreeNode<E> father){		
		
		boolean inserted = false;

		if(node != null){
			
			if(type == 'R' && father == null){
				
				if(isEmpty())
					
					setRoot(node);
				
				else{
					
					node.setLeft((BinaryTreeNode<E>)root);
					setRoot(node);
				
				}//fin del else
				
				inserted = true;
			
			}//fin del if (type == 'R' && father == null) 
			
			else{								
				
				//iterador para recorrer el arbol en preorden
				PreorderIterator<E> iterator = preOrderIterator();

				boolean existsFather = false;

				while(iterator.hasNext() && !existsFather){
					
					BinaryTreeNode<E> currentNode = iterator.nextNode();

					if(currentNode.equals(father))
						
						existsFather = true;
				
				}//fin del while(iterator.hasNext() && !existsFather)

				if(existsFather){
					
					if(type == 'L'){//izq
						
						node.setLeft(father.getLeft());
						father.setLeft(node);
					
					}//fin del if (type == 'L') 
					
					else{//der
						
						node.setRight(father.getRight());
						father.setRight(node);
					
					}//fin del else
					
					inserted = true;
				
				}//fin del if if(existsFather)
			
			}//fin del else
		
		}//fin del if(node != null)

		return inserted;
	
	}//fin de la funcion insertNode	

	//total de nodos
	public int totalNodes(){		
		
		int count = 0;

		//iterador para recorrer el arbol en preorden
		PreorderIterator<E> iterator = preOrderIterator();		

		while(iterator.hasNext()){
			
			iterator.next();
			count++;			
		
		}

		return count;
	
	}//fin de la funcion totalNodes

	//
	public boolean nodeIsLeaf(TreeNode<E> node){		
		
		return ((BinaryTreeNode<E>)node).getLeft() == null && ((BinaryTreeNode<E>)node).getRight() == null;
	
	}

	@Override
	//
	public int treeHeight(){
		
		return level((BinaryTreeNode<E>) root);
	
	}
	
	//*****************************Iteradores para los arboles***********************************
	public PreorderIterator<E> preOrderIterator(){
		
		return new PreorderIterator<E>(this);
	
	}

	public SymmetricIterator<E> symmetricIterator(){
		
		return new SymmetricIterator<E>(this);
	
	}

	public PosOrderIterator<E> posOrderIterator(){
		
		return new PosOrderIterator<E>(this);
	
	}

}//fin de la clase