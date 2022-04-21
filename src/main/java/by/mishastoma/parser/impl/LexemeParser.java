package by.mishastoma.parser.impl;

import by.mishastoma.entity.SymbolLeaf;
import by.mishastoma.entity.TextComponent;
import by.mishastoma.entity.TextComponentType;
import by.mishastoma.entity.TextComposite;
import by.mishastoma.parser.TextParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser implements TextParser {
    private static final String LEXEME_DELIMITER_REGEX = "\\S+";
    private static final String WORD_DELIMITER_REGEX = "[A-z\\w]+";
    private final TextParser wordParser = new WordParser();
    private final TextParser expressionParser = new ExpressionParser();

    @Override
    public TextComposite parse(String data) {
        TextComposite composite = new TextComposite(TextComponentType.SENTENCE);
        Pattern lexemePattern = Pattern.compile(LEXEME_DELIMITER_REGEX);
        Matcher lexemes = lexemePattern.matcher(data);
        while (lexemes.find()) {
            TextComposite component = new TextComposite(TextComponentType.LEXEME);
            String lexeme = lexemes.group();
            if (lexeme.matches(WORD_DELIMITER_REGEX)) {
                TextComponent wordComponent = wordParser.parse(lexeme);
                component.addChild(wordComponent);
            } else {
                String possibleWord = lexeme.substring(0, lexeme.length() - 1);
                if (possibleWord.matches(WORD_DELIMITER_REGEX)) {
                    TextComponent wordComponent = wordParser.parse(possibleWord);
                    component.addChild(wordComponent);
                    component.addChild(new SymbolLeaf(TextComponentType.SYMBOL, lexeme.charAt(possibleWord.length())));
                } else {
                    TextComponent expressionComponent = expressionParser.parse(lexeme);
                    component.addChild(expressionComponent);
                }
            }
            composite.addChild(component);
        }
        return composite;
    }
}
