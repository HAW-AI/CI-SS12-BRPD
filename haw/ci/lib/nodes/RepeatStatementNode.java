package haw.ci.lib.nodes;

import haw.ci.lib.SymbolTable;
import haw.ci.lib.descriptor.Descriptor;

public class RepeatStatementNode extends AbstractNode {
	private static final long serialVersionUID = 4321921938744342985L;

	private StatementSequenceNode statementSequence;
	private AbstractNode expression;
	
	public RepeatStatementNode(StatementSequenceNode statementSeqeunce,
			AbstractNode expression) {
		this.statementSequence = statementSeqeunce;
		this.expression = expression;
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
		RepeatStatementNode other = (RepeatStatementNode) obj;
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
		if(statementSequence != null) {
		    result += statementSequence.toString() + "\n";
		}
		if(expression != null) {
		    result += expression.toString() + "\n";
		}

	    return result;
	}
	
	public Descriptor compile(SymbolTable symbolTable) {
		int beginningLabel = getNextLabelNumber();
		label(beginningLabel);
		statementSequence.compile(symbolTable);
		expression.compile(symbolTable);
		branchFalse(beginningLabel);

		return null;
	}

}
