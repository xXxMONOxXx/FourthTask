package by.mishastoma.reader;

import by.mishastoma.exception.TextException;

public interface FileReader {
    String read(String filePath) throws TextException;
}
