package haw.ci.lib.nodes;

public class ModuleNode extends AbstractNode {
	private static final long serialVersionUID = 5975246380655596518L;

	private IdentNode ident;
	private DeclarationNode declaration;
	private StatementSequenceNode statementSequence;
	
	public ModuleNode(IdentNode ident, DeclarationNode declaration, StatementSequenceNode statementSequence) {
		this.ident = ident;
		this.declaration = declaration;
		this.statementSequence = statementSequence;
	}
	
	public StatementSequenceNode getStatementSequence() {
		return statementSequence;
	}

	public DeclarationNode getDeclaration() {
		return declaration;
	}

	public IdentNode getIdent() {
		return ident;
	}

	public String toString() {
		return String.format("%s(%s){%s}", getClass().getName(), getIdent(), getStatementSequence());
	}
}
