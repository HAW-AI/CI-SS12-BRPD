package haw.ci.lib;

import java.io.IOException;

public interface ITokenStream {
	public Yytoken nextToken() throws IOException;
}
