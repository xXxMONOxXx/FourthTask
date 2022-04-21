package by.mishastoma.parser.impl;

import by.mishastoma.entity.TextComposite;
import by.mishastoma.parser.TextParser;

public class WordParser implements TextParser {


    private final LetterParser parser = new LetterParser();

    @Override
    public TextComposite parse(String data) {
        return parser.parse(data);
    }
}
