package by.mishastoma.parser.impl;

import by.mishastoma.entity.*;
import by.mishastoma.parser.TextParser;

public class LetterParser implements TextParser {
    @Override
    public TextComposite parse(String data) {
        TextComposite composite = new TextComposite(TextComponentType.WORD);
        for (int i = 0; i < data.length(); i++) {
            if(Character.isLetter(data.charAt(i))){
                composite.addChild(new SymbolLeaf(TextComponentType.LETTER, data.charAt(i)));
            }
            else{
                composite.addChild(new SymbolLeaf(TextComponentType.SYMBOL, data.charAt(i)));
            }
        }
        return composite;
    }
}
