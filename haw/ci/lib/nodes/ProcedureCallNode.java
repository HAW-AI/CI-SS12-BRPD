package haw.ci.lib.nodes;

public class ProcedureCallNode extends AbstractNode {
	private static final long serialVersionUID = 2157301143846556904L;
	private IdentNode ident;
	private ActualParametersNode actualParameters;

	public ProcedureCallNode(IdentNode ident,
			ActualParametersNode actualParameters) {
		this.ident = ident;
		this.actualParameters = actualParameters;
	}
	
	public int hashCode() {
		final int prime = 43;
        int result = 1;
        result = prime * result + ((ident == null) ? 0 : ident.hashCode());
        result = prime * result + ((actualParameters == null) ? 0 : actualParameters.hashCode());
        return result;
	}
	
	public boolean equals(Object object) {
		if (this == object) { return true; }
		if (object == null) { return false; }
		if (object instanceof ProcedureCallNode) {
			ProcedureCallNode otherNode = (ProcedureCallNode) object;
			if (ident == null && otherNode.ident != null) {
				return false;
			} else if (!ident.equals(otherNode.ident)) {
				return false;
			} else if (actualParameters == null && otherNode.actualParameters != null) {
				return false;
			} else if (!actualParameters.equals(otherNode.actualParameters)) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "ProcedureCallNode [ident=" + ident + ", actualParameters="
				+ actualParameters + "]";
	}

}
