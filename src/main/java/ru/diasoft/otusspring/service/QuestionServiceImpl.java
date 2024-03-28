package ru.diasoft.otusspring.service;

import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.diasoft.otusspring.dao.QuestionDao;
import ru.diasoft.otusspring.domain.Question;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

@RequiredArgsConstructor
@Service
@PropertySource("/application.properties")
public class QuestionServiceImpl implements QuestionService {

    @Value("${filePath}")
    private String filePath;

    private final QuestionDao dao;
    @Override
    public void testingStudent() {
        List<Question> questions = dao.getQuestions(filePath);
        AtomicReference<Integer> grade = new AtomicReference<>(0);
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is your last name and first name?");
        scanner.nextLine();
        System.out.println("Please answer the questions:");
        questions.forEach(question -> {
            System.out.println(question.getQuestion());
            String answer = scanner.nextLine();
            if (answer.equals(question.getAnswer())) {
                grade.getAndSet(grade.get() + 1);
                System.out.println("Correct, answer: " + answer);
            } else {
                System.out.println("Unfortunately incorrect");
            }
        });
        System.out.println("Test result: " + grade.get());
    }


}
