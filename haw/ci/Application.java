package haw.ci;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import haw.ci.lib.ITokenStream;
import haw.ci.lib.Parser;
import haw.ci.lib.ParserAcceptError;
import haw.ci.lib.Tokenizer;
import haw.ci.lib.nodes.AbstractNode;

public class Application {
	public static void main(String[] args) throws IOException {
		File f = null;
		PrintStream backup = System.out;
		PrintStream out = null;
		
		if (args.length > 0) {
			f = new File(args[0]);
			out = new PrintStream(new File("TestRun.txt"));
		}
		if(f == null || !f.exists()) {
			f = new File("./TestRun.ob");
		}
		Reader fileReader = new FileReader(f);
		ITokenStream tokenStream = new Tokenizer(fileReader);
		Parser parser = new Parser(tokenStream);
		AbstractNode node;
		try {
			node = parser.build();
			if (out == null) {
				System.out.println(node.toString());
				System.out.println("Code:");
			}
			else System.setOut(out);
			
			node.compile();
			
			if (out != null) System.setOut(backup);
			
		} catch (ParserAcceptError e) {
			System.out.println(String.format("Failure (cur: %s, nex: %s)", parser.getCurrent(),parser.getNext()));
			e.printStackTrace();
		}
	}
}
