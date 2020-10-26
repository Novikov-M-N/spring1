package lesson1Homework.xmlContext;

public class AK74 implements Weapon{
    private Magazine magazine;
    private boolean isOverheat;
    private boolean isBurstShooting;
    private boolean isDay;
    private int countOfShoots;
    private String name;

    public AK74(String name) {
        this.isOverheat = false;
        this.isBurstShooting = false;
        this.isDay = true;
        this.countOfShoots = 0;
        this.name = name;
        System.out.println("[" + name + "] создан");
    }

    // Удалось ли произвести выстрел
    private boolean singleShoot() {
        if (magazine.shoot()) {
            if (isOverheat) {
                System.out.println("[" + name + "] тьфу!"); // При перегреве плюётся
            } else {
                System.out.println("[" + name + "] пух!");
            }
            System.out.println(); // Для удобочитаемости текста в консоли
            heat();
            return true;
        }
        System.out.println("[" + name + "] чик!");
        return false;
    }

    public void shoot() {
        int numberOfShoots = 1;
        if (isBurstShooting) {
            System.out.println("[" + toString() + "] двадцать два");
            numberOfShoots = 3;
        }
        for (int i = 0; i < numberOfShoots; i++) {
            if (!singleShoot()) break;
        }
    }

    @Override
    public void foldTheButt() {
        System.out.println("[" + name + "] пытаемся сложить приклад...");
    }

    @Override
    public void setBurstShooting(boolean isBurstShooting) {
        this.isBurstShooting = isBurstShooting;
        if (isBurstShooting) {
            System.out.println("[" + name + "] установлен режим огня очередью");
        } else {
            System.out.println("[" + name + "] установлен режим одиночного огня");
        }
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
        System.out.println("[" + name + "] вставлен магазин " + magazine.toString());
    }

    public void setIsDay(boolean isDay) {
        this.isDay = isDay;
        if (isDay) {
            System.out.println("[" + name + "] светлое время суток");
        } else {
            System.out.println("[" + name + "] тёмное время суток");
        }
    }

    // Перегрев ствола
    private void heat() {
        countOfShoots++;
        if (countOfShoots > 56) { isOverheat = true; }
    }

    public boolean isDay() { return isDay; }

    public String toString() { return name; }
}
