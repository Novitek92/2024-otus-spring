package ru.diasoft.otusspring.util;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Component
public class ReaderCSV {
    public List<String> readCsvFile(String filePath) {
        List<String> data = new ArrayList<>();
        try {
            ClassPathResource resource = new ClassPathResource(filePath);
            InputStream inputStream = resource.getInputStream();
            Reader reader = new BufferedReader(new InputStreamReader(inputStream));

            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(',')
                    .withIgnoreQuotations(true)
                    .build();

            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(0)
                    .withCSVParser(parser)
                    .build();

            Collections.addAll(data, csvReader.readNext());


        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException("Ошибка чтения файла, либо такого файла " + filePath + " не сущестует");
        }

        return data;
    }
}
