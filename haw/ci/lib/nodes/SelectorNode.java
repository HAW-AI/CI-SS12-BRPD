package haw.ci.lib.nodes;

import haw.ci.lib.SymbolTable;
import haw.ci.lib.descriptor.ArrayDescriptor;
import haw.ci.lib.descriptor.Descriptor;
import haw.ci.lib.descriptor.RecordDescriptor;

public class SelectorNode extends AbstractNode {
	private static final long serialVersionUID = 1L;
	private IdentNode ident;
	private SelectorNode selector;
	private AbstractNode expression;
	
	public SelectorNode(IdentNode ident, SelectorNode selector) {
		this.ident = ident;
		this.selector = selector;
	}

	public SelectorNode(AbstractNode expression, SelectorNode selector) {
		this.expression = expression;
		this.selector = selector;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((expression == null) ? 0 : expression.hashCode());
		result = prime * result + ((ident == null) ? 0 : ident.hashCode());
		result = prime * result
				+ ((selector == null) ? 0 : selector.hashCode());
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
		SelectorNode other = (SelectorNode) obj;
		if (expression == null) {
			if (other.expression != null)
				return false;
		} else if (!expression.equals(other.expression))
			return false;
		if (ident == null) {
			if (other.ident != null)
				return false;
		} else if (!ident.equals(other.ident))
			return false;
		if (selector == null) {
			if (other.selector != null)
				return false;
		} else if (!selector.equals(other.selector))
			return false;
		return true;
	}

	@Override
	public String toString(int indentation) {
		String result = toString(indentation, this.getClass().getName() + "\n");
		if(ident != null) {
		    result += ident.toString(indentation+1) + "\n";
		}
		if(selector != null) {
		    result += selector.toString(indentation+1) + "\n";
		}

	    return result;
	}
	
	@Override
	public Descriptor compile(SymbolTable symbolTable) {
		return compile(symbolTable, null, ident);
	}
	
	public Descriptor compile(SymbolTable symbolTable, Descriptor descriptor, IdentNode parentIdent) {
		if (ident != null) {
			Descriptor d = symbolTable.descriptorFor(ident.getIdentifierName());
			if (d != null) {
				if (d instanceof RecordDescriptor) {
					RecordDescriptor r = (RecordDescriptor) d;
					symbolTable = r.fields();
				}
				ident.compile(symbolTable);
			}
			else { 
				System.out.println("Descriptor for " + ident.getIdentifierName() + " is null");
			}
		}
		if (expression != null) {
			expression.compile(symbolTable);
			Descriptor d = symbolTable.descriptorFor(parentIdent.getIdentifierName());
			pushI(((ArrayDescriptor)d).type().size());
			write("MUL");
			write("ADD");
		}
		if (selector != null) {
			if (ident != null) {
				parentIdent = ident;
			}
			selector.compile(symbolTable, descriptor, parentIdent);
			write("ADD");
		}
	    return null;
	}
}
