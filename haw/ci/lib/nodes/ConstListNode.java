package haw.ci.lib.nodes;

import java.util.ArrayList;
import java.util.List;

public class ConstListNode extends AbstractNode {
	private static final long serialVersionUID = -9072220073427419520L;
	
	private List<ConstNode> nodes = new ArrayList<ConstNode>();

	public void add(ConstNode node) {
		nodes.add(node);
	}
}
