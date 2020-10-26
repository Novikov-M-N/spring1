package lesson1Homework.javaConfigContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("")
public class AppConfig {
    private int countOfMagazines = 0;

    @Bean(name = "magazine")
    @Scope("prototype")
    public Magazine simpleCartridgesMagazine() {
        countOfMagazines++;
        return new Magazine("magazine " + countOfMagazines);
    }

    @Bean(name = "AKC74WithoutMagazine")
    public Weapon AKC74WithoutMagazine() {
        return new AKC74("AKC74WithoutMagazine");
    }

    @Bean(name = "AKC74WithMagazine")
    public Weapon AKC74WithSimpleCartridgesMagazine(@Qualifier("magazine") Magazine magazine) {
        Weapon weapon = new AKC74("AKC74WithMagazine");
        weapon.setMagazine(magazine);
        return weapon;
    }

}
