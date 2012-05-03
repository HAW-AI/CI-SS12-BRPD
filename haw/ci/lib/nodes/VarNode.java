package haw.ci.lib.nodes;

public class VarNode extends AbstractNode {
	private static final long serialVersionUID = -8257259901064314426L;
	private IdentListNode identList;
	private TypeNode type;
	
	public VarNode(IdentListNode identList, TypeNode type) {
		this.identList = identList;
		this.type = type;
	}
}
