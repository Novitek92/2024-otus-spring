package ru.diasoft.otusspring;

import com.opencsv.exceptions.CsvValidationException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.diasoft.otusspring.service.QuestionService;

import java.io.IOException;
@ComponentScan(basePackages = "ru.diasoft")
public class OtusSpringApplication {

	public static void main(String[] args) throws CsvValidationException, IOException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(OtusSpringApplication.class);
		QuestionService service = context.getBean(QuestionService.class);
		service.testingStudent();
    }

}
