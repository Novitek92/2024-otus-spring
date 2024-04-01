package ru.diasoft.otusspring.dao;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ru.diasoft.otusspring.domain.Question;
import ru.diasoft.otusspring.util.ReaderCSV;

import java.util.*;
@Data
@RequiredArgsConstructor
@Component
@ConfigurationProperties(prefix = "daoproperties")
public class QuestionDaoImpl implements QuestionDao{
    private String filePath;

    private final ReaderCSV readerCSV;
    @Override
    public List<Question> getQuestions() {
        return readQuestion(readerCSV.readCsvFile(filePath));
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

}
