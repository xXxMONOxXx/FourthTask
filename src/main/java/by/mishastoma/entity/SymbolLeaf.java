package by.mishastoma.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SymbolLeaf implements TextComponent{

    private static final Logger logger = LogManager.getLogger();
    private TextComponentType type;
    private char symbol;

    public SymbolLeaf(TextComponentType type, char symbol){
        this.symbol = symbol;
        this.type = type;
    }

    @Override
    public void addChild(TextComponent textComponent) {
        logger.error("Unsupported operation: addChild, for {}", type.name());
        throw new UnsupportedOperationException(String.format("Unsupported operation: addChild, for %s", type.name()));
    }

    @Override
    public void removeChild(TextComponent textComponent) {
        logger.error("Unsupported operation: removeChild, for {}", type.name());
        throw new UnsupportedOperationException(String.format("Unsupported operation: removeChild, for %s", type.name()));
    }

    @Override
    public TextComponentType getType() {
        return type;
    }

    @Override
    public List<TextComponent> getChildren() {
        logger.error("Unsupported operation: add, for punctuation");
        throw new UnsupportedOperationException(String.format("Unsupported operation: getChildren, for %s", type.name()));
    }

    public char getSymbol(){
        return symbol;
    }

    @Override
    public String toString() {
        return  String.valueOf(symbol);
    }
}
