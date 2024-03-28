package ru.diasoft.otusspring.dao;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ru.diasoft.otusspring.domain.Question;

import java.io.*;
import java.util.*;

@RequiredArgsConstructor
@Component
public class QuestionDaoImpl implements QuestionDao{
    @Override
    public List<Question> getQuestions(String filePath) {
        return readQuestion(readCsvFile(filePath));
    }

    private static List<Question> readQuestion(List<String> data) {
        List<Question> questions = new ArrayList<>();
        if (!CollectionUtils.isEmpty(data)) {
            for (String string : data) {
                String[] parts = string.split(";");
                Question question = new Question(parts[0], parts[1]);
                questions.add(question);
            }
        }
        return questions;
    }

    private static List<String> readCsvFile(String filePath) {
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
