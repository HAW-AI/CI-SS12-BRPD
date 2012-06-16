package haw.ci.lib.nodes;

import haw.ci.lib.SymbolTable;
import haw.ci.lib.descriptor.Descriptor;

import java.util.ArrayList;
import java.util.List;

public class IdentListNode extends AbstractNode {
	private static final long serialVersionUID = 1L;
	private final List<IdentNode> nodes = new ArrayList<IdentNode>();


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
		IdentListNode other = (IdentListNode) obj;
		if (nodes == null) {
			if (other.nodes != null)
				return false;
		} else if (!nodes.equals(other.nodes))
			return false;
		return true;
	}

	public void add(IdentNode node) {
		nodes.add(node);
	}
	
	@Override
	public String toString(int indentation) {
		String output = toString(indentation, this.getClass().getName() + "\n");
		
		for (AbstractNode node : nodes) {
			output += node.toString(indentation+1) + "\n";
		}
		return  output;
	}
	
	@Override
	public Descriptor compile(SymbolTable symbolTable, Descriptor descriptor) {
		for (IdentNode node : nodes) {
			symbolTable.declare(node.getIdentifierName(), descriptor);
		}

		return null;
	}

}
