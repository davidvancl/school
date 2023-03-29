import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String row = myReader.nextLine();
                System.out.println(row);
                parseString(row);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An file error occurred.");
            e.printStackTrace();
        }
    }

    public static void parseString(String row){
        GramatikaLexer lexer;
        lexer = new GramatikaLexer(CharStreams.fromString(row));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GramatikaParser parser = new GramatikaParser(tokens);
        GramatikaParser.RContext c = parser.r();
    }
}