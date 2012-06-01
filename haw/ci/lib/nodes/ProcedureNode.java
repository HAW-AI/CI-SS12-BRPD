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
