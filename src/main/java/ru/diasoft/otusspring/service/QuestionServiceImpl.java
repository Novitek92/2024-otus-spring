package ru.diasoft.otusspring.service;

import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.diasoft.otusspring.dao.QuestionDao;
import ru.diasoft.otusspring.domain.Question;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

@RequiredArgsConstructor
@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;
    private final MessageSource messageSource;
    @Override
    public void testingStudent() throws CsvValidationException, IOException {
        Locale locale = Locale.getDefault();
        Map<Question, String> questions = dao.getQuestions();
        AtomicReference<Integer> grade = new AtomicReference<>(0);
        Scanner scanner = new Scanner(System.in);
        System.out.println(messageSource.getMessage("greetings", null, locale));
        scanner.nextLine();
        System.out.println(messageSource.getMessage("questions", null, locale));
        questions.forEach((question, s) -> {
            System.out.println(messageSource.getMessage(question.getData(), null, locale));
            String answer = scanner.nextLine();
            if (answer.equals(s)) {
                grade.getAndSet(grade.get() + 1);
                System.out.println(messageSource.getMessage("correctAnswer", null, locale) + answer);
            } else {
                System.out.println(messageSource.getMessage("incorrectAnswer", null, locale));
            }
        });
        System.out.println(messageSource.getMessage("testResult", null, locale) + grade.get());
    }


}
