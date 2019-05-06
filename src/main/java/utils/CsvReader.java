package utils;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import model.UserTransactions;

/**
 * Reads contents from a CSV file
 */
public class CsvReader {

    public static List<UserTransactions> readFile(File csvFile) throws Exception {
        MappingIterator<UserTransactions> transIter = new CsvMapper().readerWithTypedSchemaFor(UserTransactions.class).readValues(csvFile);

        return transIter.readAll();
    }
}