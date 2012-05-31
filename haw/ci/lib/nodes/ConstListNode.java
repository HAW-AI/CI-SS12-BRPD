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
	public String toString(int indentation) {
		String output = toString(indentation,"ConstListNode\n");
		
		for (ConstNode node : nodes) {
			output += node.toString(indentation+1) + "\n";
		}
		return  output;
	}
}
