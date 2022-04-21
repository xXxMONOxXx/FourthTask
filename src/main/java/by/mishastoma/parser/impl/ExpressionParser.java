package by.mishastoma.parser.impl;

import by.mishastoma.entity.TextComponent;
import by.mishastoma.entity.TextComponentType;
import by.mishastoma.entity.TextComposite;
import by.mishastoma.parser.TextParser;

public class ExpressionParser implements TextParser {

    private final TextParser parser = new LetterParser();

    @Override
    public TextComposite parse(String data) {
        TextComposite composite = new TextComposite(TextComponentType.EXPRESSION);
        TextComponent component = parser.parse(data);
        composite.addChild(component);
        return composite;
    }
}
