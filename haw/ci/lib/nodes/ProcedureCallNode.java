package haw.ci.lib.nodes;

import java.nio.channels.WritableByteChannel;

import haw.ci.lib.SymbolTable;
import haw.ci.lib.descriptor.Descriptor;
import haw.ci.lib.descriptor.ProcedureDescriptor;

public class ProcedureCallNode extends AbstractNode {
	private static final long serialVersionUID = 2157301143846556904L;
	private IdentNode ident;
	private ActualParametersNode actualParameters;

	public ProcedureCallNode(IdentNode ident,
			ActualParametersNode actualParameters) {
		this.ident = ident;
		this.actualParameters = actualParameters;
	}
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((actualParameters == null) ? 0 : actualParameters.hashCode());
		result = prime * result + ((ident == null) ? 0 : ident.hashCode());
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
		ProcedureCallNode other = (ProcedureCallNode) obj;
		if (actualParameters == null) {
			if (other.actualParameters != null)
				return false;
		} else if (!actualParameters.equals(other.actualParameters))
			return false;
		if (ident == null) {
			if (other.ident != null)
				return false;
		} else if (!ident.equals(other.ident))
			return false;
		return true;
	}



	@Override
	public String toString(int indentation) {
		String result = toString(indentation, this.getClass().getName() + "\n");
		if(ident != null) {
		    result += ident.toString() + "\n";
		}
		if(actualParameters != null) {
//		    result += actualParameters.toString() + "\n";
		}

	    return result;
	}
	
	@Override
	public Descriptor compile(SymbolTable symbolTable) {
		ProcedureDescriptor procedureDescriptor = (ProcedureDescriptor) symbolTable.descriptorFor(ident.getIdentifierName());

		write("INIT, " + procedureDescriptor.framesize());
		write("CALL, " + procedureDescriptor.startAddress());
		
		return null;
	}

}
