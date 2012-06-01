package haw.ci.lib.nodes;

public class PrintNode extends AbstractNode {
	private static final long serialVersionUID = 1664634323229382354L;
	private AbstractNode expression;

	public PrintNode(AbstractNode expression) {
		this.expression = expression;
	}
	
	public int hashCode() {
		final int prime = 43;
        int result = 1;
        result = prime * result + ((expression == null) ? 0 : expression.hashCode());
        return result;
	}
	
	public boolean equals(Object object) {
		if (this == object) { return true; }
		if (object == null) { return false; }
		if (object instanceof PrintNode) {
			PrintNode otherNode = (PrintNode) object;
			if (expression == null && otherNode.expression != null) {
				return false;
			} else {
				return expression.equals(otherNode.expression);
			}
		} else {
			return false;
		}
	}
	
	@Override
	public String toString(int indentation) {
		String result = toString(indentation, this.getClass().getName() + "\n");
		if(expression != null) {
		    result += expression.toString() + "\n";
		}

	    return result;
	}

}
