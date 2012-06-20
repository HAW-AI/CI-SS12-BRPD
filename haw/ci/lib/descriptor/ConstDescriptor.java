package haw.ci.lib.descriptor;

import haw.ci.lib.nodes.ConstNode;

public class ConstDescriptor implements TypeDescriptor {

	private ConstNode node;

	public ConstDescriptor(ConstNode node) {
		this.node = node;
	}

	@Override
	public int size() {
		return 0;
	}

	public ConstNode getNode() {
		return node;
	}
}
