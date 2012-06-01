package haw.ci.lib.nodes;

public class IdentifierTypeNode extends TypeNode {
	private static final long serialVersionUID = -6958497112703299089L;
	private String identifier;
	
	public IdentifierTypeNode(String identifier) {
		this.identifier = identifier;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((identifier == null) ? 0 : identifier.hashCode());
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
		IdentifierTypeNode other = (IdentifierTypeNode) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;
	}


	@Override
	public String toString(int indentation) {
		String result = toString(indentation, this.getClass().getName() + "\n");
		if(identifier != null) {
		    result += identifier.toString() + "\n";
		}

	    return result;
	}
	
}
