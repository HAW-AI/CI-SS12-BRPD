package haw.ci.lib.nodes;

import java.util.ArrayList;
import java.util.List;

public class DeclarationNode extends AbstractNode {
	private static final long serialVersionUID = 4197797757312180194L;

	private List<AbstractNode> nodes = new ArrayList<AbstractNode>();
	
	public void add(AbstractNode node) {
		nodes.add(node);
	}

	@Override
	public String toString(int indentation) {
		String result = toString(indentation, this.getClass().getName() + "\n");
		
		for (AbstractNode node : nodes) {
			result += node.toString(indentation+1) + "\n";
		}
		
	    return result;
	}
}
