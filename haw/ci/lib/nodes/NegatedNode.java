package haw.ci.lib.nodes;

import haw.ci.lib.SymbolTable;
import haw.ci.lib.descriptor.Descriptor;

public class NegatedNode extends AbstractNode {
	private static final long serialVersionUID = 1L;
	private final AbstractNode node;
	
	public NegatedNode(AbstractNode node) {
		this.node = node;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((node == null) ? 0 : node.hashCode());
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
		NegatedNode other = (NegatedNode) obj;
		if (node == null) {
			if (other.node != null)
				return false;
		} else if (!node.equals(other.node))
			return false;
		return true;
	}


	@Override
	public String toString(int indentation) {
		String result = toString(indentation, this.getClass().getName() + "\n");
		if(node != null) {
		    result += node.toString() + "\n";
		}

	    return result;
	}

	public Descriptor compile(SymbolTable symbolTable) {
		node.compile(symbolTable);
		write("PUSHI, 0");
		write("SUB");
		return null;
	}

}
