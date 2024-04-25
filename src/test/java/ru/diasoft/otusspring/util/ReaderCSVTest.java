package ru.diasoft.otusspring.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("класс ReaderCSV")
class ReaderCSVTest {
    @InjectMocks
    private ReaderCSV readerCSV;

    @DisplayName("должен считывать CSV")
    @Test
    void readCsvFile() {
        assertNotNull(readerCSV.readCsvFile("questions.csv"));
    }

    @DisplayName("должен выбросить ошибку, если не нашел файл")
    @Test
    void readCsvFileException() {
        assertThrows(RuntimeException.class, () -> readerCSV.readCsvFile("src/questions.csv"));
    }


}