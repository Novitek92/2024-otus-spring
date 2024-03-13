package ru.diasoft.otusspring.dao;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ru.diasoft.otusspring.domain.Question;

import java.io.*;
import java.util.*;
@Data
@RequiredArgsConstructor
@Component
@ConfigurationProperties(prefix = "daoproperties")
public class QuestionDaoImpl implements QuestionDao{
    private String filePath;
    @Override
    public Map<Question, String> getQuestions() throws IOException, CsvValidationException {
        return readQuestion(readCsvFile(filePath));
    }

    public Map<Question, String> readQuestion(List<String> data) {
        Map<Question, String> questions = new HashMap<>();
        if (!CollectionUtils.isEmpty(data)) {
            for (String string : data) {
                String[] parts = string.split(";");
                Question question = new Question(parts[0]);
                questions.put(question, parts[1]);
            }
        }
        return questions;
    }

    public static List<String> readCsvFile(String filePath) throws IOException, CsvValidationException {
        ClassPathResource resource = new ClassPathResource(filePath);
        InputStream inputStream = resource.getInputStream();
        Reader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<String> data = new ArrayList<>();

        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',')
                .withIgnoreQuotations(true)
                .build();

        CSVReader csvReader = new CSVReaderBuilder(reader)
                .withSkipLines(0)
                .withCSVParser(parser)
                .build();

        Collections.addAll(data, csvReader.readNext());
        return data;
    }


}
