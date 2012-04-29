package haw.ci.lib;

public class Yytoken {
	private Tokens token;
	
	private int line;
	private int column;
	private Object value;
	
	public Yytoken(Tokens token, int line, int column, Object value) {
		this.token = token;
		this.line = line;
		this.column = column;
		this.value = value;
	}
	
	public String toString() {
		return String.format("%s@%d:%d", token, line, column);
	}

	/**
	 * Static constructor
	 * @param token
	 * @return
	 */
	public static Yytoken token(Tokens token, int line, int column, Object value) {
		return new Yytoken(token, line, column, value);
	}
	public static Yytoken token(Tokens token, int line, int column) {
		return token(token, line, column, null);
	}
	
	public Tokens getToken() {
		return token;
	}
}
