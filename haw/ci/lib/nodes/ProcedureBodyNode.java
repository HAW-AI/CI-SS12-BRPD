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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((declaration == null) ? 0 : declaration.hashCode());
		result = prime
				* result
				+ ((statementSequence == null) ? 0 : statementSequence
						.hashCode());
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
		ProcedureBodyNode other = (ProcedureBodyNode) obj;
		if (declaration == null) {
			if (other.declaration != null)
				return false;
		} else if (!declaration.equals(other.declaration))
			return false;
		if (statementSequence == null) {
			if (other.statementSequence != null)
				return false;
		} else if (!statementSequence.equals(other.statementSequence))
			return false;
		return true;
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
