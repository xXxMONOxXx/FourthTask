package by.mishastoma.entity;

import java.util.List;

public interface TextComponent {
    void addChild(TextComponent textComponent);
    void removeChild(TextComponent textComponent);
    TextComponentType getType();
    List<TextComponent> getChildren();
}
