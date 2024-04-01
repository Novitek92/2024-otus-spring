package ru.diasoft.otusspring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@RequiredArgsConstructor
@Service
public class IoServiceImpl implements IoService{

    private final MessageSource messageSource;
    @Override
    public void printMessage(String code, String message) {
        //Locale.setDefault(new Locale("en", "US"));
        Locale locale = Locale.getDefault();
        if (message != null) {
            System.out.println(messageSource.getMessage(code, null, locale) + message);
        } else {
            System.out.println(messageSource.getMessage(code, null, locale));
        }

    }

}
