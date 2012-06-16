package haw.ci.lib.nodes;

import java.util.ArrayList;
import java.util.List;

public class FormalParameterNode extends AbstractNode {
	private static final long serialVersionUID = 4475881480864750952L;
	private List<FormalParameterSectionNode> nodes;
	
	public FormalParameterNode() {
		this.nodes = new ArrayList<FormalParameterSectionNode>();
	}

	public FormalParameterNode(List<FormalParameterSectionNode> nodes) {
		this.nodes = nodes;
	}

	public void add(FormalParameterSectionNode node) {
		nodes.add(node);
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
		FormalParameterNode other = (FormalParameterNode) obj;
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
		
		for (AbstractNode node : nodes) {
			output += node.toString(indentation+1) + "\n";
		}
		return  output;
	}
}
