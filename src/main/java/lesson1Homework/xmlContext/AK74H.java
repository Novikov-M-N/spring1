package lesson1Homework.xmlContext;

public class AK74H extends AK74 {
    public AK74H(String name) {
        super(name);
    }

    public AK74H(Magazine magazine, String name) {
        super(name);
        super.setMagazine(magazine);
    }

    public void foldTheButt() {
        super.foldTheButt();
        // Приклад АК-74Н не складывается
        System.out.println("[" + toString() + "] не ломай, слон, казённое имущество");
    }

}
