package lesson1Homework.javaConfigContext;

public class AKC74 extends AK74 {

    public AKC74(String name) {
        super(name);
    }

    public AKC74(Magazine magazine, String name) {
        super(name);
        setMagazine(magazine);
    }

    public void shoot() {
        if (!isDay()) { // АКС-74 не имеет ночного прицела
            System.out.println("[" + toString() + "] слишком темно для ведения стрельбы");
            return;
        }
        super.shoot();
    }

    public void foldTheButt() {
        super.foldTheButt();
        System.out.println("[" + toString() + "] Приклад сложен");
    }

}
