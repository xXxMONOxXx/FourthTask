package by.mishastoma.reader.impl;

import by.mishastoma.exception.TextException;
import by.mishastoma.reader.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.FileUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileReaderImpl implements FileReader {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public String read(String filePath) throws TextException {
        try {
            return Files.readString(Paths.get(filePath));
        } catch (Exception e) {
            logger.fatal("Error with file {}", filePath);
            e.printStackTrace();
            throw new TextException(String.format("Error with file %s", filePath));
        }
    }
}
