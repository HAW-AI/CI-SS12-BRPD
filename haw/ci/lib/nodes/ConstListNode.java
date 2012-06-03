package haw.ci.lib.nodes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConstListNode extends AbstractNode {
	private static final long serialVersionUID = -9072220073427419520L;
	
	private List<ConstNode> nodes = new ArrayList<ConstNode>();

	public void add(ConstNode node) {
		nodes.add(node);
	}
	
	public List<ConstNode> getList() {
		return nodes;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nodes == null) ? 0 : nodes.hashCode());
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
		ConstListNode other = (ConstListNode) obj;
		if (nodes == null) {
			if (other.nodes != null)
				return false;
		} else if (!nodes.equals(other.nodes))
			return false;
		return true;
	}

	@Override
	public String toString(int indentation) {
		String output = toString(indentation, this.getClass().getName() + "\n");
		
		for (ConstNode node : nodes) {
			output += node.toString(indentation+1) + "\n";
		}
		return  output;
	}
}
