package haw.ci.lib.nodes;

public class ConstNode extends AbstractNode {
	private static final long serialVersionUID = 4386388009177664496L;
	
	private IdentNode ident;
	private AbstractNode expression;

	public ConstNode(IdentNode ident, AbstractNode expression) {
		this.ident = ident;
		this.expression = expression;
	}

	public IdentNode getIdent() {
		return ident;
	}
	
	@Override
	public String toString(int indentation) {
		String result = toString(indentation, this.getClass().getName() + "\n");
		if(ident != null) {
		    result += ident.toString(indentation+1) + "\n";
		}
		if(expression != null) {
		    result += expression.toString(indentation+1);
		}
	    return result;
	}
}
