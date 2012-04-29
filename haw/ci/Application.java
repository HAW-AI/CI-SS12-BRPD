package haw.ci;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import haw.ci.lib.ITokenStream;
import haw.ci.lib.Tokenizer;
import haw.ci.lib.Yytoken;

public class Application {
	public static void main(String[] args) throws IOException {
		Reader fileReader = new FileReader(new File("./example.ob"));
		ITokenStream scanner = new Tokenizer(fileReader);

		Yytoken token;
		while ((token = scanner.nextToken()) != null) {
			System.out.println(token);
		}
	}
}
