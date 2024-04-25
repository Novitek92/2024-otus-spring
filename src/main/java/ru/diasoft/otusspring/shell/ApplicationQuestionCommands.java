package ru.diasoft.otusspring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.diasoft.otusspring.service.QuestionService;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationQuestionCommands {
    private final QuestionService questionService;

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(@ShellOption(defaultValue = "AnyUser") String userName) {
        return String.format("Добро пожаловать: %s", userName);
    }

    @ShellMethod(value = "Start test", key = {"t", "test"})
    public void quiz() {
        questionService.testingStudent();
    }

}
