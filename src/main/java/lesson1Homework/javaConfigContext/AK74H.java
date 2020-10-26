package lesson1Homework.javaConfigContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("AK74HComponent")
public class AK74H extends AK74 {
    public AK74H(String name) {
        super(name);
    }

    @Autowired
    public AK74H(Magazine magazine, @Value("AK74HComponent") String name) {
        super(name);
        super.setMagazine(magazine);
    }

    public void foldTheButt() {
        super.foldTheButt();
        // Приклад АК-74Н не складывается
        System.out.println("[" + toString() + "] не ломай, слон, казённое имущество");
    }

}
