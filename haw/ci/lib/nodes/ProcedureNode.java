package haw.ci.lib.nodes;

public class ProcedureNode extends AbstractNode {
	private static final long serialVersionUID = -8384343829441859287L;
	private ProcedureHeadingNode procedureHeading;
	private ProcedureBodyNode procedureBody;

	public ProcedureNode(ProcedureHeadingNode procedureHeading,
			ProcedureBodyNode procedureBody) {
		this.procedureHeading = procedureHeading;
		this.procedureBody = procedureBody;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((procedureBody == null) ? 0 : procedureBody.hashCode());
		result = prime
				* result
				+ ((procedureHeading == null) ? 0 : procedureHeading.hashCode());
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
		ProcedureNode other = (ProcedureNode) obj;
		if (procedureBody == null) {
			if (other.procedureBody != null)
				return false;
		} else if (!procedureBody.equals(other.procedureBody))
			return false;
		if (procedureHeading == null) {
			if (other.procedureHeading != null)
				return false;
		} else if (!procedureHeading.equals(other.procedureHeading))
			return false;
		return true;
	}

	@Override
	public String toString(int indentation) {
		String result = toString(indentation, this.getClass().getName() + "\n");
		if(procedureHeading != null) {
		    result += procedureHeading.toString() + "\n";
		}
		if(procedureBody != null) {
		    result += procedureBody.toString() + "\n";
		}

	    return result;
	}


}
