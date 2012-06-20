package haw.ci.lib.nodes;

import haw.ci.lib.SymbolTable;
import haw.ci.lib.descriptor.Descriptor;

import java.util.ArrayList;
import java.util.List;

public class StatementSequenceNode extends AbstractNode {
	private static final long serialVersionUID = 1L;
	private List<AbstractNode> nodes = new ArrayList<AbstractNode>();

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
		StatementSequenceNode other = (StatementSequenceNode) obj;
		if (nodes == null) {
			if (other.nodes != null)
				return false;
		} else if (!nodes.equals(other.nodes))
			return false;
		return true;
	}

	public void add(AbstractNode abstractNode) {
		nodes.add(abstractNode);
	}
	
	@Override
	public String toString(int indentation) {
		String output = toString(indentation, this.getClass().getName() + "\n");
		
		for (AbstractNode node : nodes) {
			if (node != null) {
				output += node.toString(indentation+1) + "\n";
			}
		}
		return  output;
	}
	
	@Override
	public Descriptor compile(SymbolTable symbolTable) {
	    for (AbstractNode node : nodes) {
	    	if (node != null) {
	    		node.compile(symbolTable);
	    	}
	    }

	    return null;
	}

}
