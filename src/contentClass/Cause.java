package contentClass;

import cu.edu.cujae.ceis.tree.general.GeneralTree;

public class Cause {
	
	private String description;
	protected GeneralTree<String> tree;
	
	public Cause(String description) {
		super();
		this.description = description;
	}
	
	public Cause() {
		super();
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public GeneralTree<String> getTree() {
		return tree;
	}
	public void setTree(GeneralTree<String> tree) {
		this.tree = tree;
	}
}
