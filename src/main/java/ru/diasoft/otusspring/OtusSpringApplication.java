package ru.diasoft.otusspring;

import com.opencsv.exceptions.CsvValidationException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.diasoft.otusspring.service.QuestionService;

import java.io.IOException;

public class OtusSpringApplication {

	public static void main(String[] args) throws CsvValidationException, IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
		QuestionService service = context.getBean(QuestionService.class);
		service.testingStudent();
    }

}
