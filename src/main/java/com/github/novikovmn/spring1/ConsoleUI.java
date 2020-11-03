package com.github.novikovmn.spring1;

import com.github.novikovmn.spring1.domain.Customer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsoleUI {
    private static ConsoleUI instance;

    public static ConsoleUI getInstance() {
        if (instance == null) instance = new ConsoleUI();
        return instance;
    }

    // Генерация строки с текстом, выровненным по центру относительно поля заданного размера
    // Пустые места заполняются пробелами
    private String centerAlign(String string, int fieldSize) {
        if (string.length() > fieldSize) return string;
        int rightIdent = (fieldSize - string.length())/2;
        int leftIdent = fieldSize - string.length() - rightIdent;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < leftIdent; i++) { result.append(' '); }
        result.append(string);
        for (int i = 0; i < rightIdent; i++) { result.append(' '); }
        return result.toString();
    }

    // Вывод таблицы на основе двумерного списка строк
    public void showTable(List<List<String>> table) {
        List<String> headers = table.get(0);
        final int FIELD_COUNT = headers.size();

        // Если в таблице нет данных, печатаем пустую шапку и выходим из функции
        if (table.size() == 1) {
            for (int i = 0; i < FIELD_COUNT; i++) {
                System.out.print(headers.get(i));
                if (i != FIELD_COUNT - 1) {
                    System.out.print('|');
                } else {
                    System.out.println();
                }
            }
            for (int i = 0; i < FIELD_COUNT; i++) {
                for (int j = 0; j < headers.get(i).length(); j++) { System.out.print('-'); }
                if (i != FIELD_COUNT - 1) {
                    System.out.print('+');
                } else {
                    System.out.println();
                }
            }
            return;
        }
        List<List<String>> rows = table.subList(1,table.size());

        // Вычисление максимальной ширины полей
        List<Integer> maxLength = new ArrayList<>();
        for (String header : headers) { maxLength.add(header.length()); }
        for (List<String> row : rows) {
            for (int i = 0; i < FIELD_COUNT; i++) {
                if (row.get(i).length() > maxLength.get(i)) maxLength.set(i, row.get(i).length());
            }
        }

        // Вывод заголовков столбцов
        StringBuilder headerString = new StringBuilder();
        for (int i = 0; i < FIELD_COUNT; i++) {
            if (i != 0) headerString.append("|");
            headerString.append(centerAlign(headers.get(i), maxLength.get(i)));
        }
        System.out.println(headerString.toString());
        // Вывод линии под шапкой
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < FIELD_COUNT; i++) {
            if (i != 0) line.append('+');
            for (int j = 0; j < maxLength.get(i); j++) {
                line.append('-');
            }
        }
        System.out.println(line.toString());
        // Вывод полей с данными
        for (List<String> row : rows) {
            for (int i = 0; i < FIELD_COUNT; i++) {
                String format = "%" + maxLength.get(i) + "s";
                if (i == FIELD_COUNT - 1) {
                    format += "\n";
                } else { format += "|"; }
                System.out.printf(format,row.get(i));
            }
        }
    }
}
