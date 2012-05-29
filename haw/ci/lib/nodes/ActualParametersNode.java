package haw.ci.lib.nodes;

import java.util.ArrayList;
import java.util.List;

public class ActualParametersNode extends AbstractNode {
	private static final long serialVersionUID = 4324447615915518226L;

	private List<AbstractNode> nodes = new ArrayList<AbstractNode>();

	public void add(AbstractNode expression) {
		nodes.add(expression);
	}

}
