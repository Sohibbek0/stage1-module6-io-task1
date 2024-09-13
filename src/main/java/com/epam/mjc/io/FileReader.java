package com.epam.mjc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileReader {

    private static final Logger LOGGER = Logger.getLogger(FileReader.class.getName());

    public Profile getDataFromFile(File file) {
        String name = null;
        Integer age = null;
        String email = null;
        Long phone = null;

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
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
                            try {
                                age = Integer.parseInt(value);
                            } catch (NumberFormatException e) {
                                LOGGER.log(Level.WARNING, "Ошибка преобразования возраста: " + e.getMessage());
                            }
                            break;
                        case "Email":
                            email = value;
                            break;
                        case "Phone":
                            try {
                                phone = Long.parseLong(value);
                            } catch (NumberFormatException e) {
                                LOGGER.log(Level.WARNING, "Ошибка преобразования телефона: " + e.getMessage());
                            }
                            break;
                        default:
                            LOGGER.log(Level.WARNING, "Неизвестный ключ: " + key);
                            break;
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Ошибка чтения файла: " + e.getMessage());
        }

        return new Profile(name, age, email, phone);
    }
}
