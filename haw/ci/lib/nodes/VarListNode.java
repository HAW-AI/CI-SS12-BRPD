package haw.ci.lib.nodes;

import haw.ci.lib.SymbolTable;
import haw.ci.lib.descriptor.Descriptor;

import java.util.ArrayList;
import java.util.List;

public class VarListNode extends AbstractNode {
	private static final long serialVersionUID = 7461659742425146944L;

	private List<VarNode> nodes = new ArrayList<VarNode>();

	public void add(VarNode node) {
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
		VarListNode other = (VarListNode) obj;
		if (nodes == null) {
			if (other.nodes != null)
				return false;
		} else if (!nodes.equals(other.nodes))
			return false;
		return true;
	}

	@Override
	public String toString(int indentation) {
		String output = toString(indentation, this.getClass().getName() + "\n");
		
		for (AbstractNode node : nodes) {
			output += node.toString(indentation+1) + "\n";
		}
		return  output;
	}
	
	public Descriptor compile(SymbolTable symbolTable) {
		for (VarNode node : nodes) {
			node.compile(symbolTable);
		}
		
		return null;
	}

	
	
}
