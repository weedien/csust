package cn.weedien.csust.medium.ee.expe04.test03;

import cn.weedien.csust.medium.ee.expe04.test02.Person;
import cn.weedien.csust.medium.ee.expe04.test02.Speakable;

public class PersonProxy implements Speakable {
    private Person target;

    public PersonProxy(Person target) {
        this.target = target;
    }

    @Override
    public void speak(String message) {
        System.out.println("Class begin");
        target.speak(message);
        System.out.println("Class end");
    }

    @Override
    public String toString() {
        return target.toString();
    }
}
