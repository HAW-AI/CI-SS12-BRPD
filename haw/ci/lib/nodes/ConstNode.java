package haw.ci.lib.nodes;

public class ConstNode extends AbstractNode {
	private IdentNode ident;
	private AbstractNode expression;

	public ConstNode(IdentNode ident, AbstractNode expression) {
		this.ident = ident;
		this.expression = expression;
	}

}
