package by.mishastoma.parser.impl;

import by.mishastoma.entity.TextComponent;
import by.mishastoma.entity.TextComponentType;
import by.mishastoma.entity.TextComposite;
import by.mishastoma.parser.TextParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser implements TextParser {

    private static final String SENTENCE_REGEX = ".+?[.?!…](?=\\s)|$"; // …

    private final TextParser parser = new LexemeParser();

    @Override
    public TextComposite parse(String data) {
        Pattern pattern = Pattern.compile(SENTENCE_REGEX);
        Matcher sentences = pattern.matcher(data);
        TextComposite composite = new TextComposite(TextComponentType.PARAGRAPH);
        while(sentences.find()){
            TextComponent sentenceComponent = parser.parse(sentences.group());
            composite.addChild(sentenceComponent);
        }
        return composite;
    }
}
