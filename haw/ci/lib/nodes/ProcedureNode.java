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

}
