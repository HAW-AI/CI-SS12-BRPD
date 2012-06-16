package haw.ci.lib.nodes;

import haw.ci.lib.SymbolTable;
import haw.ci.lib.descriptor.Descriptor;

public class IntegerNode extends AbstractNode implements IntegerValue {
	private static final long serialVersionUID = 1L;
	private final Integer value;
	
	public IntegerNode(Integer value) {
		this.value = value;
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
		IntegerNode other = (IntegerNode) obj;
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
			result += toString(indentation, "Value: " + value.toString() + "\n");
		}

	    return result;
	}
	
	@Override
	public Descriptor compile(SymbolTable symbolTable) {
		write("PUSHI, " + value);
		return null;
	}


	@Override
	public int getValue() {
		return value;
	}

}
