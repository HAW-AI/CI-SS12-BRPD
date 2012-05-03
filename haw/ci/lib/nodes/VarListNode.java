package haw.ci.lib.nodes;

import java.util.ArrayList;
import java.util.List;

public class VarListNode extends AbstractNode {
	private static final long serialVersionUID = 7461659742425146944L;

	private List<VarNode> nodes = new ArrayList<VarNode>();

	public void add(VarNode node) {
		nodes.add(node);
	}
	
	
}
