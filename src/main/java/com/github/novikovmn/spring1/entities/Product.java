package com.github.novikovmn.spring1.entities;

public class Product {
    private int id;
    private String title;

    //Цена целое число в копейках, т.к. float и double считают с погрешностью, а в бухгалтерии погрешность недопустима
    private int cost;

    //Юзер френдли цена - пара строковых значений: рубли и копейки. Строки - чтобы никто не смог использовать эти
    //значения для математических операций - для расчётов только целочисленная цена в минимальных единицах (копейка).
    //Генерируется здесь же в объекте при изменении поля cost, но, по-хорошему, эту функцию нужно возложить на
    //Пользовательский интерфейс, т.к. используется только для отображения пользователю.
    private String[] ufCost;

    public Product(int id, String title, int cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
        setUfCost();
    }

    public Product(String title, int cost) {
        this.id = -1;
        this.title = title;
        this.cost = cost;
        setUfCost();
    }

    public Product() {
        this.id = -1;
        this.title = "Пустой товар";
        this.cost = 0;
        setUfCost();
    }

    private void setUfCost() {
        if (ufCost == null) ufCost = new String[2];
        ufCost[0] = String.valueOf(cost/100);
        ufCost[1] = String.valueOf(cost % 100);
    }

    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setCost(int cost) {
        this.cost = cost;
        setUfCost();
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public int getCost() { return cost; }
    public String[] getUfCost() { return ufCost; }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Product p = (Product) o;
        return p.id == this.id && p.title.equals(this.title) && p.cost == this.cost;
    }

    @Override
    public String toString() {
        return "Product {" + "id = " + id + " title = " + title + " cost = " + cost + "}";
    }
}