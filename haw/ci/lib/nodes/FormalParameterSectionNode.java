package haw.ci.lib.nodes;

public class FormalParameterSectionNode extends AbstractNode {
	private static final long serialVersionUID = -4416688308390577830L;
	private IdentListNode identList;
	private TypeNode type;
	
	public FormalParameterSectionNode(IdentListNode identList, TypeNode type) {
		this.identList = identList;
		this.type = type;
	}
}
