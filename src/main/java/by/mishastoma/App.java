package by.mishastoma;

import by.mishastoma.entity.TextComposite;
import by.mishastoma.exception.TextException;
import by.mishastoma.parser.TextParser;
import by.mishastoma.parser.impl.ParagraphParser;
import by.mishastoma.reader.FileReader;
import by.mishastoma.reader.impl.FileReaderImpl;
import by.mishastoma.service.TextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App
{

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args )
    {
        FileReader reader = new FileReaderImpl();
        try {
            String actual = reader.read("src/main/resources/data/data.txt");
            TextParser parser = new ParagraphParser();
            TextComposite text = parser.parse(actual);
            logger.info(text.toString());
            TextService service = new TextService();
            logger.info(service.countIdenticalWords(text));
            logger.info(service.countNumOfVowels(text));
            logger.info(service.countNumConsonants(text));
            logger.info(service.findSentenceWithLongestWord(text));
            service.deleteSentencesWithLowerAmountOfWords(text, 20);
            logger.info(text.toString());
        }
        catch (TextException e){
            logger.error(e.getMessage());
        }
    }
}
