import AST.*;
import Parser.*;
import Utils.*;
import Utils.error.*;
import Frontend.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;


import java.io.FileInputStream;
import java.io.InputStream;

public class main {
    public static void main(String[] args) throws Exception {
        String name = "test.txt";
        InputStream input = new FileInputStream(name);
        try {
            RootNode ASTRoot;
            GlobalScope gScope;

            MxLexer lexer = new MxLexer(CharStreams.fromStream(input));
            lexer.removeErrorListeners();
            lexer.addErrorListener(new MxErrorListener());
            MxParser parser = new MxParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new MxErrorListener());
            ParseTree parserTreeRoot = parser.file_input();
            ASTBuilder astBuilder = new ASTBuilder(gScope);
            ASTRoot = (RootNode) astBuilder.visit(parserTreeRoot);
            new SymbolCollector(gScope).visit(RootNode);
            new SemanticChecker(gScope).visit(RootNode);
        } catch (error err) {
            System.err.println(err.toString());
            throw new RuntimeException();
        }
    }
}