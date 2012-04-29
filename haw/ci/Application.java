package haw.ci;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import haw.ci.lexer.Scanner;
import haw.ci.lexer.Yytoken;

public class Application {
	public static void main(String[] args) throws IOException {
		System.out.println("open file");
		Reader fileReader = new FileReader(new File("./example2.ob"));
		System.out.println("create lexer");
		Scanner scanner = new Scanner(fileReader);
		System.out.println("tokens: ");
		Yytoken token;
		while ((token = scanner.yylex()) != null) {
			System.out.println(token);
		}
	}
}
