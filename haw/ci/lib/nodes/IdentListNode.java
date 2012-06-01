package haw.ci.lib.nodes;

import java.util.ArrayList;
import java.util.List;

public class IdentListNode extends AbstractNode {
	private static final long serialVersionUID = 1L;
	private final List<IdentNode> nodes = new ArrayList<IdentNode>();

	public int hashCode() {
		final int prime = 43;
        int result = 1;
        result = prime * result + ((nodes == null) ? 0 : nodes.hashCode());
        return result;
	}

	public boolean equals(Object object) {
		if (this == object) { return true; }
		if (object == null) { return false; }
		if (object instanceof IdentNode) {
			IdentListNode otherNode = (IdentListNode) object;
			if (nodes == null && otherNode.nodes != null) {
				return false;
			} else if (!nodes.equals(otherNode.nodes)) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	public void add(IdentNode node) {
		nodes.add(node);
	}
	
	@Override
	public String toString(int indentation) {
		String output = toString(indentation, this.getClass().getName() + "\n");
		
		for (AbstractNode node : nodes) {
			output += node.toString(indentation+1) + "\n";
		}
		return  output;
	}

}
