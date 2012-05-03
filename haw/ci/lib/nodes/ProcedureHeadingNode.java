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
}
