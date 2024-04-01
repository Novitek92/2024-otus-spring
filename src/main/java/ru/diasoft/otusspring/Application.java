package ru.diasoft.otusspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.diasoft.otusspring.service.QuestionService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		QuestionService service = context.getBean(QuestionService.class);
		service.resultTestingStudent(service.testingStudent());
	}

}
