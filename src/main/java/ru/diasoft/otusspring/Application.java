package ru.diasoft.otusspring;

import com.opencsv.exceptions.CsvValidationException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.diasoft.otusspring.service.QuestionService;

import java.io.IOException;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws CsvValidationException, IOException {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		QuestionService service = context.getBean(QuestionService.class);
		service.testingStudent();
	}

}
