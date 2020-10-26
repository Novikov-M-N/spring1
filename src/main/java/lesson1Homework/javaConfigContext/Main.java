package lesson1Homework.javaConfigContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Weapon AKC74WithoutMagazine = context.getBean("AKC74WithoutMagazine", AKC74.class);
        Weapon AKC74WithMagazine = context.getBean("AKC74WithMagazine",AKC74.class);
        Weapon AK74HComponent = context.getBean("AK74HComponent",AK74H.class);

        Magazine newMagazine = context.getBean("magazine",Magazine.class);

        AKC74WithoutMagazine.setIsDay(false);
        AKC74WithoutMagazine.shoot();
        AKC74WithoutMagazine.setIsDay(true);
        AKC74WithoutMagazine.shoot();

        AKC74WithMagazine.shoot();
        AKC74WithMagazine.getMagazine().addCartridge(context.getBean("simpleCartridge",_7H6.class));
        AKC74WithMagazine.getMagazine().addCartridge(context.getBean("simpleCartridge",_7H6.class));
        AKC74WithMagazine.shoot();
        AKC74WithMagazine.shoot();
        newMagazine.addCartridge(context.getBean("trasserCartridge",_7T3.class));
        newMagazine.addCartridge(context.getBean("trasserCartridge",_7T3.class));
        newMagazine.addCartridge(context.getBean("trasserCartridge",_7T3.class));
        AKC74WithoutMagazine.setMagazine(newMagazine);
        AKC74WithoutMagazine.shoot();
        AKC74WithoutMagazine.shoot();

        AK74HComponent.shoot();

        AKC74WithoutMagazine.foldTheButt();
        AKC74WithMagazine.foldTheButt();
        AK74HComponent.foldTheButt();
    }
}
