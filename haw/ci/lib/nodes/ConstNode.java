package haw.ci.lib.nodes;

public class ConstNode extends AbstractNode {
	private IdentNode ident;
	private ExpressionNode expression;

	public ConstNode(IdentNode ident, ExpressionNode expression) {
		this.ident = ident;
		this.expression = expression;
	}

}
