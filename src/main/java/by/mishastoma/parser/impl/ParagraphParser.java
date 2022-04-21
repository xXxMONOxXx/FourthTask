package by.mishastoma.parser.impl;

import by.mishastoma.entity.TextComponent;
import by.mishastoma.entity.TextComponentType;
import by.mishastoma.entity.TextComposite;
import by.mishastoma.parser.TextParser;

public class ParagraphParser implements TextParser {

    private final TextParser parser = new SentenceParser();

    @Override
    public TextComposite parse(String data) {
        TextComposite composite = new TextComposite(TextComponentType.TEXT);
        String[] paragraphs = data.split(TextComponentType.PARAGRAPH.getDelimiter());
        for(String paragraph : paragraphs){
            TextComponent paragraphComponent = parser.parse(paragraph);
            composite.addChild(paragraphComponent);
        }
        return composite;
    }
}
