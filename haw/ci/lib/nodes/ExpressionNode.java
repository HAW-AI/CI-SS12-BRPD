package haw.ci.lib.nodes;

import haw.ci.lib.SymbolTable;
import haw.ci.lib.Tokens;
import haw.ci.lib.descriptor.Descriptor;

public class ExpressionNode extends AbstractNode {
	private static final long serialVersionUID = 1L;
	private final Tokens binOpToken;
	private final AbstractNode left, right;
	
	public ExpressionNode(Tokens binOpToken, AbstractNode left, AbstractNode right) {
		this.binOpToken = binOpToken;
		this.left = left;
		this.right = right;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((binOpToken == null) ? 0 : binOpToken.hashCode());
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExpressionNode other = (ExpressionNode) obj;
		if (binOpToken != other.binOpToken)
			return false;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		return true;
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

	public Descriptor compile(SymbolTable symbolTable) {
		left.compile(symbolTable);

		if (left instanceof IdentNode) {
			write("CONT, 1");
		}
		right.compile(symbolTable);

		if (right instanceof IdentNode) {
			write("CONT, 1");
		}

		if (binOpToken.equals(Tokens.MATH_ADD)) {
			write("ADD");
		} else if (binOpToken.equals(Tokens.MATH_SUB)) {
			write("SUB");
		} else if (binOpToken.equals(Tokens.MATH_MUL)) {
			write("MUL");
		} else if (binOpToken.equals(Tokens.MATH_DIV)) {
			write("DIV");
		} else if (binOpToken.equals(Tokens.EQUAL)) {
			write("EQ");
		} else if (binOpToken.equals(Tokens.MORE)) {
			write("GT");
		} else if (binOpToken.equals(Tokens.LESS)) {
			write("LT");
		} else if (binOpToken.equals(Tokens.MORE_EQUAL)) {
			write("GE");
		} else if (binOpToken.equals(Tokens.LESS_EQUAL)) {
			write("LE");
		} else if (binOpToken.equals(Tokens.NOT_EQUAL)) {
			write("NEQ");
		} else {
			write("UNEXPECTED SYMBOL IN: ExpressionNode("+binOpToken+")");
		}
		return null;
	}

	
}
