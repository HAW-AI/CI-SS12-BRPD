package haw.ci.lib.nodes;

public class WhileStatementNode extends AbstractNode {
	private static final long serialVersionUID = 1L;
	private final AbstractNode expression;
	private final StatementSequenceNode statementSequence;

	public WhileStatementNode(AbstractNode expression, StatementSequenceNode statementSequence) {
		this.expression = expression;
		this.statementSequence = statementSequence;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((expression == null) ? 0 : expression.hashCode());
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
		WhileStatementNode other = (WhileStatementNode) obj;
		if (expression == null) {
			if (other.expression != null)
				return false;
		} else if (!expression.equals(other.expression))
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
		if(expression != null) {
		    result += expression.toString() + "\n";
		}
		if(statementSequence != null) {
		    result += statementSequence.toString() + "\n";
		}

	    return result;
	}
}
