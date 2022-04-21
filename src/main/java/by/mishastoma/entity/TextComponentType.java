package by.mishastoma.entity;

public enum TextComponentType {
    TEXT("\n"),
    PARAGRAPH("\n\t"),
    SENTENCE(""),
    LEXEME(" "),
    WORD(""),
    LETTER(""),
    SYMBOL(""),
    EXPRESSION("");

    private String delimiter;
    TextComponentType(String delimiter){
        this.delimiter = delimiter;
    }

    public String getDelimiter(){
        return this.delimiter;
    }

}
