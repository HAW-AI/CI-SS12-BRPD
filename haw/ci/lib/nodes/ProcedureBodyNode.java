package haw.ci.lib.nodes;

public class ProcedureBodyNode extends AbstractNode {
	private static final long serialVersionUID = -7312376636705994625L;
	private StatementSequenceNode statementSequence;
	private DeclarationNode declaration;

	public ProcedureBodyNode(DeclarationNode declaration,
			StatementSequenceNode statementSequence) {
		this.declaration = declaration;
		this.statementSequence = statementSequence;
	}

}
