package com.epam.mjc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.epam.mjc.io.FileProcessor;

public class FileProcessor { // Переименуйте класс для избежания конфликта

    private static final Logger LOGGER = Logger.getLogger(FileProcessor.class.getName());

    public Profile getDataFromFile(File file) {
        String name = null;
        Integer age = null;
        String email = null;
        Long phone = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) { // Используйте правильный конструктор
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(": ");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();

                    switch (key) {
                        case "Name":
                            name = value;
                            break;
                        case "Age":
                            age = parseInteger(value);
                            break;
                        case "Email":
                            email = value;
                            break;
                        case "Phone":
                            phone = parseLong(value);
                            break;
                        default:
                            LOGGER.log(Level.WARNING, String.format("Неизвестный ключ: %s", key));
                            break;
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, String.format("Ошибка чтения файла: %s", e.getMessage()));
        }

        return new Profile(name, age, email, phone);
    }

    private Integer parseInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, String.format("Ошибка преобразования возраста: %s", e.getMessage()));
            return null;
        }
    }

    private Long parseLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, String.format("Ошибка преобразования телефона: %s", e.getMessage()));
            return null;
        }
    }
}
