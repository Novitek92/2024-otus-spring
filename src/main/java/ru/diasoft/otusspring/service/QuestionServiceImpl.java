package ru.diasoft.otusspring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.diasoft.otusspring.dao.QuestionDao;
import ru.diasoft.otusspring.domain.Question;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

@RequiredArgsConstructor
@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;
    private final IoService ioService;
    @Override
    public boolean testingStudent() {
        List<Question> questions = dao.getQuestions();
        AtomicReference<Integer> grade = new AtomicReference<>(0);
        Scanner scanner = new Scanner(System.in);
        ioService.printMessage("greetings", null);
        scanner.nextLine();
        ioService.printMessage("questions", null);
        questions.forEach(question -> {
            ioService.printMessage(question.getQuestion(), null);
            String answer = scanner.nextLine();
            if (answer.equals(question.getAnswer())) {
                grade.getAndSet(grade.get() + 1);
                ioService.printMessage("correctAnswer", answer);
            } else {
                ioService.printMessage("incorrectAnswer", null);
            }
        });
        ioService.printMessage("testResult", grade.get().toString());

        return grade.get() >= 4;
    }

    @Override
    public void resultTestingStudent(Boolean result) {
        if (result) {
            ioService.printMessage("testResultMessage", null);
        } else {
            ioService.printMessage("testResultMessageNo", null);
        }
    }

}
