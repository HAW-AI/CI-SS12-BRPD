package haw.ci.lib.nodes;

import java.util.ArrayList;
import java.util.List;

public class VarListNode extends AbstractNode {
	private static final long serialVersionUID = 7461659742425146944L;

	private List<VarNode> nodes = new ArrayList<VarNode>();

	public void add(VarNode node) {
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
