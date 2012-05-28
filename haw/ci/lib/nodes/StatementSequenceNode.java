package haw.ci.lib.nodes;

import java.util.ArrayList;
import java.util.List;

public class StatementSequenceNode extends AbstractNode {
	private static final long serialVersionUID = 1L;
	private List<StatementNode> nodes = new ArrayList<StatementNode>();
	
	
	public int hashCode() {
		return nodes.hashCode();
	}
	
	public boolean equals(Object object) {
		return nodes.equals(object);
	}

	public void add(AbstractNode abstractNode) {
		nodes.add((StatementNode) abstractNode);
	}
}
