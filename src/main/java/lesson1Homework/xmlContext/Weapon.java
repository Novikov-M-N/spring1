package lesson1Homework.xmlContext;

public interface Weapon {
    void shoot(); // Огонь
    void foldTheButt(); // Сложить приклад
    void setBurstShooting(boolean isBurstShooting); // Режим ведения огня: очередь (true) либо одиночными (false)
    void setMagazine(Magazine magazine); // Установка магазина с патронами
    void setIsDay(boolean isDay); // Внешние условия: день либо ночь

}
