public class P2LexerMain {
    public static void main(String[] args)
    {

        String input = """
                x = 5;
                if (x > 3) {
                y = x + 2;
                } else {
                y = x * (2 + 3);
                }
                print y;
                """;

        P2Lexer lexer=new P2Lexer(input);
        for(P2Lexer.Token token:lexer)
        {
            System.out.println(token);
        }
    }
}
