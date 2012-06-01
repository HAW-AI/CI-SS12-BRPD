package haw.ci.lib.nodes;

public class ProcedureHeadingNode extends AbstractNode {
	private static final long serialVersionUID = -6415807066447172897L;
	
	private IdentNode ident;
	private FormalParameterNode formalParameter;
	
	public ProcedureHeadingNode(IdentNode ident,
			FormalParameterNode formalParameter) {
		this.ident = ident;
		this.formalParameter = formalParameter;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((formalParameter == null) ? 0 : formalParameter.hashCode());
		result = prime * result + ((ident == null) ? 0 : ident.hashCode());
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
		ProcedureHeadingNode other = (ProcedureHeadingNode) obj;
		if (formalParameter == null) {
			if (other.formalParameter != null)
				return false;
		} else if (!formalParameter.equals(other.formalParameter))
			return false;
		if (ident == null) {
			if (other.ident != null)
				return false;
		} else if (!ident.equals(other.ident))
			return false;
		return true;
	}



	@Override
	public String toString(int indentation) {
		String result = toString(indentation, this.getClass().getName() + "\n");
		if(ident != null) {
		    result += ident.toString() + "\n";
		}
		if(formalParameter != null) {
		    result += formalParameter.toString() + "\n";
		}

	    return result;
	}

}
