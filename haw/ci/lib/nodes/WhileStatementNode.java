package haw.ci.lib.nodes;

public class WhileStatementNode extends AbstractNode {
	private static final long serialVersionUID = 1L;
	private final AbstractNode expression;
	private final StatementSequenceNode statementSequence;

	public WhileStatementNode(AbstractNode expression, StatementSequenceNode statementSequence) {
		this.expression = expression;
		this.statementSequence = statementSequence;
	}

	public int hashCode() {
		final int prime = 43;
        int result = 1;
        result = prime * result + ((expression == null) ? 0 : expression.hashCode());
        result = prime * result + ((statementSequence == null) ? 0 : statementSequence.hashCode());
        return result;
	}

	public boolean equals(Object object) {
		if (this == object) { return true; }
		if (object == null) { return false; }
		if (object instanceof WhileStatementNode) {
			WhileStatementNode otherNode = (WhileStatementNode) object;
			if (expression == null && otherNode.expression != null) {
				return false;
			} else if (!expression.equals(otherNode.expression)) {
				return false;
			} else if (statementSequence == null && otherNode.statementSequence != null) {
				return false;
			} else if (!statementSequence.equals(otherNode.statementSequence)) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
}
