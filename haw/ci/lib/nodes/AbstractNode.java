package haw.ci.lib.nodes;

import java.io.Serializable;

public abstract class AbstractNode implements Serializable {
	private static final long serialVersionUID = 1L;

	public String toString() {
		return this.getClass().getName();
	}
}
