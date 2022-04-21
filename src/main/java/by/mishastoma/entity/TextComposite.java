package by.mishastoma.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TextComposite implements TextComponent{

    private List<TextComponent> textComponents;
    private TextComponentType type;

    public TextComposite(TextComponentType type){
        textComponents = new ArrayList<>();
        this.type = type;
    }

    @Override
    public void addChild(TextComponent textComponent) {
        textComponents.add(textComponent);
    }

    @Override
    public void removeChild(TextComponent textComponent) {
        textComponents.remove(textComponent);
    }

    @Override
    public TextComponentType getType() {
        return type;
    }

    @Override
    public List<TextComponent> getChildren() {
        return List.copyOf(textComponents);
    }

    public static class NumberOfSentencesComparator implements Comparator<TextComponent> {

        @Override
        public int compare(TextComponent firstParagraph, TextComponent secondParagraph) {
            return Integer.compare(firstParagraph.getChildren().size(), secondParagraph.getChildren().size());
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(TextComponent element : textComponents){
            stringBuilder.append(element.toString());
        }
        return stringBuilder.toString();
    }
}
