package stc21;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class HomeTask4Test {

    @BeforeEach
    void setUp() {
    }

    @Test
    void cleanupObject() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        HomeTask4 ht4Class = new HomeTask4();
        MyTestClass testClass = new MyTestClass("Surik", true, 32, "5707", 86.6, 182.4f);
        Set<String> fieldsToCleanup = new HashSet<String>();
        fieldsToCleanup.add("weight");
        fieldsToCleanup.add("height");
        fieldsToCleanup.add("pas");
        Set<String> fieldsToOutput = new HashSet<String>();
        fieldsToOutput.add("name");
        fieldsToOutput.add("male");
        fieldsToOutput.add("age");
        String cleanup = ht4Class.cleanup(testClass, fieldsToCleanup, fieldsToOutput);
        assertEquals("Suriktrue32", cleanup);
        assertEquals("Surik", testClass.name);
        assertTrue(testClass.male);
        assertEquals(32, testClass.age);
        assertNull(testClass.pas);
        assertEquals(0.0, testClass.weight);
        assertEquals(0f, testClass.height);

    }

    @Test
    @DisplayName("Throw illegalArgumentException")
    public void cleanExc() {
        assertThrows(IllegalArgumentException.class,
                () -> new HomeTask4().cleanup("Hello", new HashSet<>(), new TreeSet<>()));
    }

    @Test
    public void cleanMap() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        HashMap map = new HashMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");
        Set<String> fieldsToCleanup = new HashSet<String>();
        Set<String> fieldsToOutput = new HashSet<String>();
        fieldsToCleanup.add("key2");
        fieldsToOutput.add("key3");
        fieldsToOutput.add("key4");
        String cleanup2 = new HomeTask4().cleanup(map, fieldsToCleanup, fieldsToOutput);
        assertEquals(3, map.size());
        assertEquals("value3value4", cleanup2);
        assertFalse(map.containsKey("key2"));
    }
}

class MyTestClass {
    String name;
    boolean male;
    int age;
    String pas;
    double weight;
    float height;

    MyTestClass(String name, boolean male, int age, String pas, double weight, float height) {
        this.name = name;
        this.male = male;
        this.age = age;
        this.pas = pas;
        this.weight = weight;
        this.height = height;
    }
}