package haw.ci.lib.nodes;

import haw.ci.lib.SymbolTable;
import haw.ci.lib.descriptor.Descriptor;

public class IdentNode extends AbstractNode {
	private static final long serialVersionUID = 1L;
	private final String value;

	public IdentNode(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		IdentNode other = (IdentNode) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}


	@Override
	public String toString(int indentation) {
		String result = toString(indentation, this.getClass().getName() + "\n");
		if(value != null) {
		    result += toString(indentation, "Valuename: " + value.toString() + "\n");
		}

	    return result;
	}
	
	@Override
	public Descriptor compile(SymbolTable symbolTable) {
		write("PUSHI, " + symbolTable.addressOf(value));
		// if we are in a local scope get add FP offset to address
		if (symbolTable.isLocal(value)) {
			write("GETFP");
			write("ADD");
		}
		return null;
	}
	
	public String getIdentifierName() {
		return value;
	}
}
