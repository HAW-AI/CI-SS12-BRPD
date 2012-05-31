package haw.ci.lib.descriptor;

public class SimpleTypeDescriptor implements TypeDescriptor {
		public enum Type{
			BOOLEAN, INTEGER, STRING;
		}
		private Type type;
	
		public SimpleTypeDescriptor(Type type) {
			this.type=type;
		}
	
		@Override
		public int size() {
			return 1;
		}
	
		public Type type(){
			return this.type;
		}
}
