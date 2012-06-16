package haw.ci.lib;

public class Yytoken {
	private Tokens token;
	
	private int line;
	private int column;
	private String value;
	
	public Yytoken(Tokens token, int line, int column, String value) {
		this.token = token;
		this.line = line;
		this.column = column;
		this.value = value;
	}
	
	public String toString() {
		return token.toString();
	}

	/**
	 * Static constructor
	 * @param token
	 * @return
	 */
	public static Yytoken token(Tokens token, int line, int column, String value) {
		return new Yytoken(token, line, column, value);
	}
	public static Yytoken token(Tokens token, int line, int column) {
		return token(token, line, column, null);
	}
	
	public Tokens getToken() {
		return token;
	}
	
	public int getLine() {
		return line;
	}
	
	public int getColumn() {
		return column;
	}
	
	public String getValue() {
		return value;
	}
}
