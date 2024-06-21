import java.util.ArrayList;
import java.util.List;
import java.util.spi.ToolProvider;

public class Lexer {

    private final String input;

    private final List<Token> tokens;
    public Lexer(String input)
    {
        this.input=input;
        this.tokens=new ArrayList<Token>();
        tokenSize();
    }

    private void tokenSize()
    {

    }
    static class Token
    {
        final TokenType type;
        final String value;
        Token(TokenType type,String value)
        {
        this.type=type;
        this.value=value;
        }

        @Override
        public String toString() {
            return "Token{" +
                    "type=" + type +
                    ", value='" + value + '\'' +
                    '}';
        }
    }
    enum TokenType
    {
        CONFIG,UPDATE,COMPUTE,SHOW,CONFIGS,STRING,NUMBER,IDENTIFIER,REFERENCES,ASSIGNMENT
    }
}
