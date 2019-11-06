import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class HomeTask4Test {

    @org.junit.jupiter.api.Test
    void cleanup() throws IllegalAccessException {
        Test t = new Test("Surik", true, 32, "5707", 86.6, 182.4f);
        Set<String> fieldsToCleanup = new HashSet<String>();
        fieldsToCleanup.add("weight");
        fieldsToCleanup.add("height");
        fieldsToCleanup.add("pas");
        Set<String> fieldsToOutput = new HashSet<String>();
        fieldsToOutput.add("name");
        fieldsToOutput.add("male");
        fieldsToOutput.add("age");
        String cleanup = HomeTask4.cleanup(t, fieldsToCleanup, fieldsToOutput);
        assertEquals("Suriktrue32", cleanup);
        assertEquals("Surik", t.name);
        assertTrue(t.male);
        assertEquals(32, t.age);
        assertNull(t.pas);
        assertEquals(0.0, t.weight);
        assertEquals(0f, t.height);
        HashMap map = new HashMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");
        fieldsToCleanup.add("key2");
        fieldsToOutput.add("key3");
        fieldsToOutput.add("key4");
        String cleanup2 = HomeTask4.cleanup(map, fieldsToCleanup, fieldsToOutput);
        assertEquals(3, map.size());
        assertEquals("value3value4", cleanup2);
        assertFalse(map.containsKey("key2"));
        assertThrows(IllegalArgumentException.class, () -> HomeTask4.cleanup("Hello", fieldsToCleanup, fieldsToOutput));
    }
}

class Test {
    String name = "Ilsur";
    boolean male;
    int age;
    String pas;
    double weight;
    float height;

    Test(String name, boolean male, int age, String pas, double weight, float height) {
        this.name = name;
        this.male = male;
        this.age = age;
        this.pas = pas;
        this.weight = weight;
        this.height = height;
    }
}