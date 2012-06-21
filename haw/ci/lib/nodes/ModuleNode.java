package haw.ci.lib.nodes;

import haw.ci.lib.SymbolTable;
import haw.ci.lib.descriptor.Descriptor;

public class ModuleNode extends AbstractNode {
	private static final long serialVersionUID = 5975246380655596518L;

	private IdentNode ident;
	private DeclarationNode declaration;
	private StatementSequenceNode statementSequence;
	
	public ModuleNode(IdentNode ident, DeclarationNode declaration, StatementSequenceNode statementSequence) {
		this.ident = ident;
		this.declaration = declaration;
		this.statementSequence = statementSequence;
	}
	
	public StatementSequenceNode getStatementSequence() {
		return statementSequence;
	}

	public DeclarationNode getDeclaration() {
		return declaration;
	}

	public IdentNode getIdent() {
		return ident;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((declaration == null) ? 0 : declaration.hashCode());
		result = prime * result + ((ident == null) ? 0 : ident.hashCode());
		result = prime
				* result
				+ ((statementSequence == null) ? 0 : statementSequence
						.hashCode());
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
		ModuleNode other = (ModuleNode) obj;
		if (declaration == null) {
			if (other.declaration != null)
				return false;
		} else if (!declaration.equals(other.declaration))
			return false;
		if (ident == null) {
			if (other.ident != null)
				return false;
		} else if (!ident.equals(other.ident))
			return false;
		if (statementSequence == null) {
			if (other.statementSequence != null)
				return false;
		} else if (!statementSequence.equals(other.statementSequence))
			return false;
		return true;
	}

	@Override
	public String toString(int indentation) {
		String result = toString(indentation, this.getClass().getName() + "\n");
		if(ident != null) {
		    result += ident.toString(indentation+1);
		}
		if(declaration != null) {
		    result += declaration.toString(indentation+1);
		}
		if(statementSequence != null) {
		    result += statementSequence.toString(indentation+1);
		}

	    return result;
	}
	
	@Override
	public Descriptor compile(SymbolTable symbolTable) {
		write("PUSHS, " + ident.getValue());
		write("JMP, 0");
		declaration.compile(symbolTable);
		write("LABEL, 0");
	    pushI(symbolTable.size());
	    write("SETSP");
	    statementSequence.compile(symbolTable);
	    write("STOP");

	    debug(symbolTable.toString());
	    return null;
	}

}
