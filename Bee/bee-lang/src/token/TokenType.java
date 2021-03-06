package token;

public enum TokenType {

    LOAD,

    TODO, FIX, WARN,

    LET, BE, CHANGE,
    IF, LOOP,
    FUN, TAKE, RETURN,
    CALL, WITH,
    BREAK, CONTINUE,
    ASSERT, THAT,
    TYPE, EXTEND, THIS, SUPER, GET, SET,

    RUN, WAIT,

    PLUS, MINUS, STAR, SLASH,

    EQUAL, BANG, BANG_EQUAL,
    GREATER, GREATER_EQUAL,
    LESS, LESS_EQUAL,
    MINIS_MINUS,

    AND, OR, XOR,

    LEFT_PAREN, RIGHT_PAREN,

    SLASH_SLASH,
    SLASH_STAR,
    STAR_SLASH,

    AT, HASH, COMMA, DOT, ARROW,

    NUMBER, STRING, CHARACTER, IDENTIFIER,
    TRUE, FALSE, NULL,

    END_OF_FILE,
}
