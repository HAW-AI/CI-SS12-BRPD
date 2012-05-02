package haw.ci.lib;

import java.util.List;

public class StatementSequenceNode extends AbstractNode {
	private static final long serialVersionUID = 1L;
	private final List<AbstractNode> statements;
	
	public StatementSequenceNode(List<AbstractNode> list) {
		this.statements = list;
	}
	
	public int hashCode() {
		return statements.hashCode();
	}
	
	public boolean equals(Object object) {
		return statements.equals(object);
	}
}
