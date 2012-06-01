package haw.ci.lib.nodes;

import haw.ci.lib.Tokens;

public class ExpressionNode extends AbstractNode {
	private static final long serialVersionUID = 1L;
	private final Tokens binOpToken;
	private final AbstractNode left, right;
	
	public ExpressionNode(Tokens binOpToken, AbstractNode left, AbstractNode right) {
		this.binOpToken = binOpToken;
		this.left = left;
		this.right = right;
	}
	
	public int hashCode() {
		final int prime = 43;
        int result = 1;
        result = prime * result + ((binOpToken == null) ? 0 : binOpToken.hashCode());
        result = prime * result + ((left == null) ? 0 : left.hashCode());
        result = prime * result + ((right == null) ? 0 : right.hashCode());
        return result;
	}
	
	public boolean equals(Object object) {
		if (this == object) { return true; }
		if (object == null) { return false; }
		if (object instanceof ExpressionNode) {
			ExpressionNode otherNode = (ExpressionNode) object;
			if (binOpToken == null && otherNode.binOpToken != null) {
				return false;
			} else if (!binOpToken.equals(otherNode.binOpToken)) {
				return false;
			} else if (left == null && otherNode.left != null) {
				return false;
			} else if (!left.equals(otherNode.left)) {
				return false;
			} else if (right == null && otherNode.right != null) {
				return false;
			} else if (!right.equals(otherNode.right)) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public String toString(int indentation) {
		String result = toString(indentation, this.getClass().getName() + "\n");
		if(binOpToken != null) {
		    result += binOpToken.toString() + "\n";
		}
		if(left != null) {
		    result += left.toString(indentation+1);
		}
		if(right != null) {
		    result += right.toString(indentation+1);
		}
	    return result;
	}


	
}
