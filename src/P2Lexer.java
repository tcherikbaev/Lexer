import java.util.ArrayList;
import java.util.Currency;
import java.util.Iterator;
import java.util.List;

public class P2Lexer implements  Iterable<P2Lexer.Token>{

    private final String input;

    private final List<Token> tokens;

    private int curr;
    public P2Lexer(String input)
    {
        this.input=input;
        this.tokens=new ArrayList<>();
        this.curr=0;
        tokenize();
    }
    private boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }

    private void tokenize()
    {
        while (curr<input.length())
        {
            char ch=input.charAt(curr);
            switch (ch)
            {
                case ' ':
                case '\t':
                case '\n':
                case '\r':
                    curr++;
                    break;
                case ';':
                    tokens.add(new Token(TokenType.ENDLINE,";"));
                    curr++;
                case '=':
                    tokens.add(new Token(TokenType.ASSIGNMENT,"="));
                    curr++;
                    break;
                case '+':
                    tokens.add(new Token(TokenType.OPERATOR,"+"));
                    curr++;
                    break;
                case '*':
                    tokens.add(new Token(TokenType.OPERATOR,"*"));
                    curr++;
                    break;
                case '>':
                    tokens.add(new Token(TokenType.COMPARATOR,">"));
                    curr++;
                    break;
                case '<':
                    tokens.add(new Token(TokenType.COMPARATOR,">"));
                    curr++;
                    break;
                case '(':
                    tokens.add(new Token(TokenType.LEFT_PARENTHESIS,"("));
                    curr++;
                    break;
                case ')':
                    tokens.add(new Token(TokenType.RIGHT_PARENTHESIS,")"));
                    curr++;
                    break;
                case '{':
                    tokens.add(new Token(TokenType.LEFT_BRACE,"{"));
                    curr++;
                    break;
                case '}':
                    tokens.add(new Token(TokenType.RIGHT_BRACE,"}"));
                    curr++;
                    break;

                default:
                    if(isDigit(ch))
                    {
                        tokens.add(new Token(TokenType.NUMBER,readNumber()));
                    }
                    else if(isAlpha(ch))
                    {
                        String identifier=readIdentifier();
                        tokens.add(new Token(deriveTokenType(identifier), identifier));
                    }
            }
        }
    }

    private TokenType deriveTokenType(String identifier) {
        return switch (identifier) {
            case "print" -> TokenType.OUTPUT;
            case "if", "else" -> TokenType.CONDITION;
            default -> TokenType.IDENTIFIER;
        };
    }

    private boolean isAlpha(char c) {
        return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || c == '_';
    }

    private String readNumber() {
        StringBuilder builder = new StringBuilder();
        while (curr < input.length() && isDigit(input.charAt(curr))) {
            builder.append(input.charAt(curr));
            curr++;
        }
        return builder.toString();
    }
    private boolean isAlphanumeric(char c) {
        return isAlpha(c) || isDigit(c);
    }
    private String readIdentifier() {
        StringBuilder builder = new StringBuilder();
        while (curr < input.length() && isAlphanumeric(input.charAt(curr))) {
            builder.append(input.charAt(curr));
            curr++;
        }
        return builder.toString();
    }

    @Override
    public Iterator<Token> iterator() {
        return tokens.iterator();
    }

    static class Token
    {
        final TokenType type;
        final String value;

        Token(TokenType type, String value) {
            this.type = type;
            this.value = value;
        }
        @Override
        public String toString() {
            return "Token{" +
                    "type=" + type +
                    ", value='" + value + '\'' +
                    '}';
        }
    }
    enum TokenType {
        UPDATE, COMPUTE, SHOW, COMPARATOR,STRING, NUMBER, IDENTIFIER, REFERENCES, ASSIGNMENT, OPERATOR, LEFT_PARENTHESIS, RIGHT_PARENTHESIS, LEFT_BRACE, RIGHT_BRACE, OUTPUT, ENDLINE, CONDITION
    }


}
