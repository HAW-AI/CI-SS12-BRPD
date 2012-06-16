package haw.ci.lib.nodes;

import haw.ci.lib.SymbolTable;
import haw.ci.lib.descriptor.Descriptor;

import java.util.ArrayList;
import java.util.List;

public class DeclarationNode extends AbstractNode {
	private static final long serialVersionUID = 4197797757312180194L;

	private List<AbstractNode> nodes = new ArrayList<AbstractNode>();
	
	public void add(AbstractNode node) {
		nodes.add(node);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nodes == null) ? 0 : nodes.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeclarationNode other = (DeclarationNode) obj;
		if (nodes == null) {
			if (other.nodes != null)
				return false;
		} else if (!nodes.equals(other.nodes))
			return false;
		return true;
	}



	@Override
	public String toString(int indentation) {
		String result = toString(indentation, this.getClass().getName() + "\n");
		
		for (AbstractNode node : nodes) {
			result += node.toString(indentation+1) + "\n";
		}
		
	    return result;
	}
	
	public Descriptor compile(SymbolTable symbolTable){
		for(AbstractNode node : nodes){
			node.compile(symbolTable);
		}

		return null;
	}
}
