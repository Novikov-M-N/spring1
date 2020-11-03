package com.github.novikovmn.spring1;

import com.github.novikovmn.spring1.domain.Buy;
import com.github.novikovmn.spring1.domain.Customer;
import com.github.novikovmn.spring1.domain.Product;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory ef = new Configuration()
                .configure("hibernate_create.xml")
                .buildSessionFactory();
        EntityManager em = ef.createEntityManager();

        DAO dao = DAO.getInstance(em); // Объект, манипулирующий с сущностями и БД
        ConsoleUI ui = ConsoleUI.getInstance(); // Объект, отвечающий за пользовательский интерфейс
        Utils utils = Utils.getInstance(em); // Служебный класс - свалка громоздких функций
        CommandProcessor cp = CommandProcessor.getInstance(utils, ui, dao); // Класс-обработчик консольных команд

        // Создаём список товаров
        List<Product> products = new ArrayList<>();
            products.add(new Product("Подвес прямой 300x30x0.9 мм",660));
            products.add(new Product("Уголок крепежный соединительный 40х40х40х1.8 мм",570));
            products.add(new Product("Уголок крепежный 25х14х25х1.75 мм",400));
            products.add(new Product("Шайба пружинная DIN 127B 4x9 мм, 15 шт.",410));
            products.add(new Product("Пластина бытовая 50х14х1.5 мм, сталь",650));
            products.add(new Product("Винт для крепления цилиндра M5x70 мм",720));

        // Создаём список покупателей
        List<Customer> customers = new ArrayList<>();
            customers.add(new Customer("Константин"));
            customers.add(new Customer("Пётр"));
            customers.add(new Customer("Сергей"));

        // Покупатели делают покупки
        List<Buy> buys = new ArrayList<>();
        buys.add(new Buy(customers.get(0),products.get(0),1));
        buys.add(new Buy(customers.get(1),products.get(1),2));
        buys.add(new Buy(customers.get(1),products.get(2),3));
        buys.add(new Buy(customers.get(1),products.get(3),2));
        buys.add(new Buy(customers.get(2),products.get(3),4));
        buys.add(new Buy(customers.get(2),products.get(4),5));
        buys.add(new Buy(customers.get(2),products.get(5),6));

        //Сохраняем всё это добро в базе
        em.getTransaction().begin();
        for(Product product:products) { em.persist(product); }
        for (Customer customer:customers) { em.persist(customer); }
        for (Buy buy:buys) { em.persist(buy); }
        em.getTransaction().commit();

        // Собственно приложение
        Scanner in = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("Введите команду. /q - выход, /h - помощь по командам");
            String[] command = in.nextLine().split(" ");
            String[] options = new String[]{"none"};
            if (command.length > 1) {
                options = Arrays.copyOfRange(command, 1, command.length);
            }
            switch (command[0]){
                case "/q":
                    exit = true;
                    break;
                case "/h":
                    cp.help();
                    break;
                case "/s":
                    cp.showEntity(options);
                    break;
                case "/d":
                    cp.delete(options);
                    break;
                default:
                    System.out.println("Неизвестная команда. /h - помощь по командам.");
                    break;
            }

            if (command.equals("q")) { break; }
        }

    }

}
