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
	
	@Override
	public String toString(int indentation) {
		String result = toString(indentation, this.getClass().getName() + "\n");
		if(statementSequence != null) {
		    result += statementSequence.toString() + "\n";
		}
		if(declaration != null) {
		    result += declaration.toString() + "\n";
		}

	    return result;
	}


}
