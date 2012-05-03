package haw.ci.lib.nodes;

public class StatementNode extends AbstractNode {
	private static final long serialVersionUID = -4328693297321257313L;
	private AbstractNode node;

	public StatementNode(AbstractNode node) {
		this.node = node;
	}
}
