package haw.ci.lib;

import java.util.ArrayList;
import java.util.Arrays;

public class StatementSequenceNode extends AbstractNode {
	private static final long serialVersionUID = 1L;
	private final ArrayList<StatementNode> statements;
	
	public StatementSequenceNode(StatementNode statement) {
		this.statements = (ArrayList<StatementNode>) Arrays.asList(statement);
	}

	public void addStatement(StatementNode statement) {
		if (statement != null) {
			this.statements.add(statement);
		}
	}
	
	public ArrayList<StatementNode> getStatements() {
		return new ArrayList<StatementNode>(this.statements);
	}
	
	public int hashCode() {
		return statements.hashCode();
	}
	
	public boolean equals(Object object) {
		return statements.equals(object);
	}
}
