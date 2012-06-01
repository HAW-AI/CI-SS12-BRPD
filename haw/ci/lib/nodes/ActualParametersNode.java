package haw.ci.lib.nodes;

import java.util.ArrayList;
import java.util.List;

public class ActualParametersNode extends AbstractNode {
	
	@Override
	public String toString(int indentation) {
		String output = toString(indentation, this.getClass().getName() + "\n");
		
		for (AbstractNode node : nodes) {
			output += node.toString(indentation+1) + "\n";
		}
		return  output;
	}

	private static final long serialVersionUID = 4324447615915518226L;

	private List<AbstractNode> nodes = new ArrayList<AbstractNode>();

	public void add(AbstractNode expression) {
		nodes.add(expression);
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
		ActualParametersNode other = (ActualParametersNode) obj;
		if (nodes == null) {
			if (other.nodes != null)
				return false;
		} else if (!nodes.equals(other.nodes))
			return false;
		return true;
	}
}
