package lesson1Homework.xmlContext;

import org.springframework.stereotype.Component;

@Component("trasserCartridge")
public class _7T3 implements Cartridge {
    public void shoot() {
        System.out.println("[Патрон 7Т3] трассирующая пуля ушла");
    }

    public String toString() {
        return "Патрон 7Т3";
    }
}
