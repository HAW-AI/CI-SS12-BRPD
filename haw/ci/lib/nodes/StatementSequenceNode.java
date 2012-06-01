package haw.ci.lib.nodes;

import java.util.ArrayList;
import java.util.List;

public class StatementSequenceNode extends AbstractNode {
	private static final long serialVersionUID = 1L;
	private List<AbstractNode> nodes = new ArrayList<AbstractNode>();
	
	
	public int hashCode() {
		return nodes.hashCode();
	}
	
	public boolean equals(Object object) {
		return nodes.equals(object);
	}

	public void add(AbstractNode abstractNode) {
		nodes.add(abstractNode);
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
