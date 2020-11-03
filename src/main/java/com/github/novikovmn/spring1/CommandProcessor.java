package com.github.novikovmn.spring1;

public class CommandProcessor {
    private static CommandProcessor instance;
    private Utils utils;
    private ConsoleUI ui;
    private DAO dao;

    public static CommandProcessor getInstance(Utils utils, ConsoleUI ui, DAO dao) {
        if (instance == null) instance = new CommandProcessor(utils, ui, dao);
        return instance;
    }

    private CommandProcessor(Utils utils, ConsoleUI ui, DAO dao) {
        this.utils = utils;
        this.ui = ui;
        this.dao = dao;
    }

    public void help() {
        System.out.println("/s <entity> [option] - отобразить записи из таблицы entity");
        System.out.println("option:");
        System.out.println("    -a              подробное отображение (работает не для всех сущностей)");
        System.out.println("    -cname <name>   отобразить товары, которые приобрёл покупатель с именем name");
        System.out.println("                    (действительно только для таблицы 'product')");
        System.out.println("    -cid <id>       отобразить товары, которые приобрёл покупатель с id = id");
        System.out.println("                    (действительно только для таблицы 'product')");
        System.out.println("    -ptitle <title> отобразить покупателей, которые приобрёли товары с наименованием title");
        System.out.println("                    (действительно только для таблицы 'customers')");
        System.out.println("    -pid <id>       отобразить покупателей, которые приобрёли товары с id = id");
        System.out.println("                    (действительно только для таблицы 'customers')");
        System.out.println("/d <entity> <field> <value> - удалить из таблицы entity запись со значением поля field = value");
    }

    public void showEntity(String[] params) {
        String entity = params[0];
        String option = "none";
        if (params.length > 1) { option = params[1]; }
        switch (entity) {
            case "customers":
                switch (option) {
                    case "-ptitle":
                        if (params.length > 2) {
                            String title = "";
                            for (int i = 2; i < params.length; i++) {
                                if (i != 2) title += ' ';
                                title += params[i];
                            }
                            ui.showTable(utils.buysAdvancedAsTable(dao.getBuysByProductTitle(title)));
                        } else {
                            System.out.println("Не указан необходимый параметр title. /h - помощь по командам");
                        }
                        break;
                    case "-pid":
                        if (params.length > 2) {
                            Long id = -1L;
                            try {
                                id = Long.valueOf(params[2]);
                            } catch (NumberFormatException e) {
                            }
                            ui.showTable(utils.buysAdvancedAsTable(dao.getBuysByProductId(id)));
                        } else {
                            System.out.println("Не указан необходимый параметр id. /h - помощь по командам");
                        }
                        break;
                    case "none":
                        ui.showTable(utils.customersAsTable(dao.getCustomers()));
                        break;
                    default:
                        System.out.println("Неизвестная опция " + option + ". /h - помощь по командам");
                        break;
                }
                break;
            case "products":
                switch (option) {
                    case "-cname":
                        if (params.length > 2) {
                            String name = "";
                            for (int i = 2; i < params.length; i++) {
                                if (i != 2) name += ' ';
                                name += params[i];
                            }
                            ui.showTable(utils.buysAdvancedAsTable(dao.getBuysByCustomerTitle(name)));
                        } else {
                            System.out.println("Не указан необходимый параметр name. /h - помощь по командам");
                        }
                        break;
                    case "-cid":
                        if (params.length > 2) {
                            Long id = -1L;
                            try {
                                id = Long.valueOf(params[2]);
                            } catch (NumberFormatException e) {
                            }
                            ui.showTable(utils.buysAdvancedAsTable(dao.getBuysByCustomerId(id)));
                        } else {
                            System.out.println("Не указан необходимый параметр id. /h - помощь по командам");
                        }
                        break;
                    case "none":
                        ui.showTable(utils.productsAsTable(dao.getProducts()));
                        break;
                    default:
                        System.out.println("Неизвестная опция " + option + ". /h - помощь по командам");
                        break;
                }
                break;
            case "buys":
                switch (option) {
                    case "-a":
                        ui.showTable(utils.buysAdvancedAsTable(dao.getBuys()));
                        break;
                    case "none":
                        ui.showTable(utils.buysAsTable(dao.getBuys()));
                        break;
                    default:
                        System.out.println("Неизвестная опция " + option + ". /h - помощь по командам");
                        break;
                }
                break;
            default:
                System.out.println("В базе нет сущности " + entity);
                break;
        }
    }

    public void delete(String[] params) {
        if (params.length < 3) {
            System.out.println("Неполная команда. /h - помощь по командам");
        }
        String entity = params[0];
        String field = params[1];
        String value = params[2];
        switch (entity) {
            case "customers":
                switch (field) {
                    case "id":
                        Long id = -1L;
                        try {
                            id = Long.valueOf(value);
                        } catch (NumberFormatException e) {
                        }
                        dao.deleteCustomerById(id);
                        System.out.println("Удалён покупатель с id = " + id);
                        break;
                    case "name":
                        dao.deleteCustomerByName(value);
                        System.out.println("Удалён покупатель с name = " + value);
                        break;
                    default:
                        System.out.println("Поля " + field + " в таблице " + entity + " нет, либо удаление по этому полю не предусмотрено");
                        break;
                }
                break;
            case "products":
                switch (field) {
                    case "id":
                        break;
                    case "name":
                        break;
                    default:
                        System.out.println("Поля " + field + " в таблице " + entity + " нет, либо удаление по этому полю не предусмотрено");
                        break;
                }
                break;
            default:
                System.out.println("В базе нет сущности " + entity);
                break;
        }
    }
}
