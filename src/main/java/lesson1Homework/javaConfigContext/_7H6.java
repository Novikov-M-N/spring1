package lesson1Homework.javaConfigContext;

import org.springframework.stereotype.Component;

@Component("simpleCartridge")
public class _7H6 implements Cartridge {
    public void shoot() {
        System.out.println("[Патрон 7Н6] пуля ушла");
    }
    public String toString() {
        return ("Патрон 7Н6");
    }
}
