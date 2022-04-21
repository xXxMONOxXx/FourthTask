package by.mishastoma.service;

import by.mishastoma.entity.TextComponent;
import by.mishastoma.entity.TextComponentType;
import by.mishastoma.entity.TextComposite;

import java.util.*;
import java.util.stream.Collectors;

public class TextService {

    private static final List<String> VOWELS = Arrays.asList("a", "o", "u", "i", "e", "y");
    private static final List<String> CONSONANTS = Arrays.asList("c", "b", "d", "f", "g", "h", "j", "k", "m", "l", "n", "p", "q", "r", "s", "t", "v", "w", "x", "z");

    public List<TextComponent> sortParagraphsByNumOfSentences(TextComposite text) {
        return text.getChildren().stream()
                .sorted(new TextComposite.NumberOfSentencesComparator())
                .toList();
    }

    public List<TextComponent> findSentenceWithLongestWord(TextComposite text) {
        int maxSize = findLongestWordSize(text);
        List<TextComponent> finalSentences = new ArrayList<>();
        List<TextComponent> paragraphs = text.getChildren();
        for (TextComponent paragraph : paragraphs) {
            List<TextComponent> sentences = paragraph.getChildren();
            for (TextComponent sentence : sentences) {
                List<TextComponent> lexemes = sentence.getChildren();
                boolean added = false;
                for (TextComponent lexeme : lexemes) {
                    if (added) {
                        break;
                    }
                    List<TextComponent> components = lexeme.getChildren();
                    for (TextComponent component : components) {
                        if (component.getType() == TextComponentType.WORD && maxSize == component.getChildren().toString().length()) {
                            finalSentences.add(sentence);
                            added = true;
                            break;
                        }
                    }
                }
            }
        }
        return finalSentences;
    }

    public void deleteSentencesWithLowerAmountOfWords(TextComposite text, int minNumberOfWords) {
        List<TextComponent> paragraphs = text.getChildren();
        for (int i = 0; i < paragraphs.size(); i++) {
            deleteSentencesWithLowerAmountOfWordsFromParagraph(paragraphs.get(i), minNumberOfWords);
            if (paragraphs.get(i).getChildren().isEmpty()) {
                text.removeChild(paragraphs.get(i));
            }
        }
    }

    public Map<String, Long> countIdenticalWords(TextComposite text) {
        Map<String, Long> identicalWords = text.getChildren().stream()
                .flatMap(paragraph -> paragraph.getChildren().stream())
                .flatMap(sentence -> sentence.getChildren().stream())
                .flatMap(lexeme -> lexeme.getChildren().stream())
                .filter(wordOrExpr -> wordOrExpr.getType().equals(TextComponentType.WORD))
                .collect(Collectors.groupingBy(word -> word.toString().toLowerCase(), Collectors.counting()));
        identicalWords.entrySet().removeIf(word -> word.getValue() == 1);
        return identicalWords;
    }

    private void deleteSentencesWithLowerAmountOfWordsFromParagraph(TextComponent paragraph, int number) {
        List<TextComponent> sentences = paragraph.getChildren();
        int numOfWordsInSentence;
        TextComponent currentSentence;
        for (int i = 0; i < sentences.size(); i++) {
            currentSentence = sentences.get(i);
            numOfWordsInSentence = getNumberOfWordsInSentence(currentSentence);
            if (numOfWordsInSentence < number) {
                paragraph.removeChild(currentSentence);
            }
        }
    }

    private int getNumberOfWordsInSentence(TextComponent sentence) {
        int number = 0;
        List<TextComponent> lexemes = sentence.getChildren();
        for (TextComponent lexeme : lexemes) {
            List<TextComponent> lexemeParts = lexeme.getChildren();
            for (TextComponent lexemePart : lexemeParts) {
                if (TextComponentType.WORD == lexemePart.getType()) {
                    number++;
                }
            }
        }
        return number;
    }

    public int countNumOfVowels(TextComposite text) {
        List<TextComponent> paragraphs = text.getChildren();
        int result = 0;
        for (TextComponent paragraph : paragraphs) {
            List<TextComponent> sentences = paragraph.getChildren();
            for (TextComponent sentence : sentences) {
                List<TextComponent> lexemes = sentence.getChildren();
                for (TextComponent lexeme : lexemes) {
                    List<TextComponent> words = lexeme.getChildren();
                    for (TextComponent word : words) {
                        if (TextComponentType.WORD == word.getType()) {
                            List<TextComponent> symbols = word.getChildren();
                            for (TextComponent letter : symbols) {
                                result += VOWELS.contains(letter.toString().toLowerCase(Locale.ROOT)) ? 1 : 0;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public int countNumConsonants(TextComposite text) {
        List<TextComponent> paragraphs = text.getChildren();
        int result = 0;
        for (TextComponent paragraph : paragraphs) {
            List<TextComponent> sentences = paragraph.getChildren();
            for (TextComponent sentence : sentences) {
                List<TextComponent> lexemes = sentence.getChildren();
                for (TextComponent lexeme : lexemes) {
                    List<TextComponent> words = lexeme.getChildren();
                    for (TextComponent word : words) {
                        if (TextComponentType.WORD == word.getType()) {
                            List<TextComponent> symbols = word.getChildren();
                            for (TextComponent letter : symbols) {
                                result += VOWELS.contains(letter.toString().toLowerCase(Locale.ROOT)) ? 1 : 0;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    private int findLongestWordSize(TextComposite text) {
        int maxSize = 0;
        List<TextComponent> paragraphs = text.getChildren();
        for (TextComponent paragraph : paragraphs) {
            List<TextComponent> sentences = paragraph.getChildren();
            for (TextComponent sentence : sentences) {
                List<TextComponent> lexemes = sentence.getChildren();
                for (TextComponent lexeme : lexemes) {
                    List<TextComponent> words = lexeme.getChildren();
                    for (TextComponent word : words) {
                        if (word.getType() == TextComponentType.WORD) {
                            maxSize = Math.max(maxSize, word.getChildren().toString().length());
                        }
                    }
                }
            }
        }
        return maxSize;
    }
}
