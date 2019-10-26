package luyen.ninh.wallpaperx;

import org.junit.Test;

/**
 * Created by luyen_ninh on 2019-09-01.
 */
public class TestOOP {

    @Test
    public void test() {
        Person person = new Person("Thanh");
        rename(person);
        System.out.println("Name is " + person.getName());

    }

    private void rename(Person person) {
        String nameDefault ="Luyen";
        person.setName(nameDefault);
        StringBuffer stringBuffer = new StringBuffer(nameDefault);

    }

}

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
