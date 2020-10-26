package lesson1Homework.xmlContext;

import java.util.ArrayList;
import java.util.List;

public class Magazine {
    private List<Cartridge> cartridges;
    private boolean withTrassers;
    private String name;

    public Magazine(String name) {
        this.cartridges = new ArrayList<Cartridge>();
        this.withTrassers = false;
        this.name = name;
        System.out.println("[" + name + "] создан");
        load();
    }

    public Magazine(boolean withTrassers, String name) {
        this.cartridges = new ArrayList<Cartridge>();
        this.withTrassers = withTrassers;
        this.name = name;
        System.out.println("[" + name + "] создан");
        load();
    }

    public Magazine(String name, Cartridge... cartridges) {
        this.cartridges = new ArrayList<Cartridge>();
        this.withTrassers = false;
        this.name = name;
        System.out.println("[" + name + "] создан");
        for (Cartridge cartridge: cartridges) { this.addCartridge(cartridge); }
        load();
    }

    public boolean shoot() {
        if (cartridges.size() > 0) {
            cartridges.get(cartridges.size()-1).shoot();
            cartridges.remove(cartridges.size()-1);
            System.out.println("[" + name + "] минус 1 патрон");
            return true;
        } else {
            System.out.println("[" + name + "] пуст");
            return false;
        }
    }

    public boolean addCartridge(Cartridge cartridge) {
        if (cartridges.size() < 30) {
            cartridges.add(cartridge);
            return true;
        } else {
            System.out.println("[" + name + "] полон");
            return false;
        }
    }

    public void load() {
        if (withTrassers) {
            while (addCartridge(new _7T3()));
        } else {
            while (addCartridge(new _7H6())) ;
        }
    }

    public String toString() { return name; }
}
