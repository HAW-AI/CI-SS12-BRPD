package haw.ci.lib.nodes;

public class ConstNode extends AbstractNode {
	private static final long serialVersionUID = 4386388009177664496L;
	
	private IdentNode ident;
	private AbstractNode expression;

	public ConstNode(IdentNode ident, AbstractNode expression) {
		this.ident = ident;
		this.expression = expression;
	}

}
