package lesson1Homework.xmlContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Weapon rifleAKC74 = context.getBean("rifleAKC74", AKC74.class);
        Weapon rifleAK74HInnerBean = context.getBean("rifleAK74HInnerBean", AK74H.class);
        Weapon rifleAK74HConstructor = context.getBean("rifleAK74HConstructor", AK74H.class);
        rifleAKC74.setIsDay(false);
        rifleAK74HInnerBean.setIsDay(false);
        rifleAK74HConstructor.setIsDay(false);
        rifleAKC74.shoot();
        rifleAK74HInnerBean.shoot();
        rifleAK74HConstructor.shoot();
        rifleAKC74.setIsDay(true);
        for (int i = 0; i < 32; i++) {
            rifleAKC74.shoot();
        }
        rifleAKC74.setMagazine(new Magazine("annotationCartridgeMagazine",
                context.getBean("trasserCartridge",_7T3.class))); // Последний будет отстрелян трассирующий
        rifleAKC74.setBurstShooting(true);
        for (int i = 0; i < 11; i++) {
            rifleAKC74.shoot();
        }
        rifleAKC74.foldTheButt();
        rifleAK74HInnerBean.foldTheButt();
        rifleAK74HConstructor.foldTheButt();
    }
}
