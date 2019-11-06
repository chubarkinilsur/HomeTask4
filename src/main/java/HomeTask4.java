import java.lang.reflect.Field;
import java.util.*;

public class HomeTask4 {


    public static String cleanup(Object object, Set<String> fieldsToCleanup, Set<String> fieldsToOutput) throws IllegalAccessException {
        Class<?> clazz = object.getClass();

        Field[] declaredFields = clazz.getDeclaredFields();
        int k = 0;
        StringBuilder toConsole = new StringBuilder();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            String typeName = field.getType().getName();
            if (fieldsToCleanup.contains(field.getName())) {
                k++;
                switch (typeName) {
                    case ("int"):
                        field.setInt(object, 0);
                        break;
                    case ("long"):
                        field.setLong(object, 0L);
                        break;
                    case ("byte"):
                        field.setByte(object, (byte) 0);
                        break;
                    case ("short"):
                        field.setShort(object, (short) 0);
                        break;
                    case ("char"):
                        field.setChar(object, '\u0000');
                        break;
                    case ("boolean"):
                        field.setBoolean(object, false);
                        break;
                    case ("float"):
                        field.setFloat(object, 0.0f);
                        break;
                    case ("double"):
                        field.setDouble(object, 0.0);
                        break;
                    default:
                        field.set(object, null);
                        break;
                }
            }
            if (fieldsToOutput.contains(field.getName())) {
                k++;
                //toConsole+=field.get(object);
                switch (typeName) {
                    case ("int"):
                        toConsole.append(String.valueOf(field.getInt(object)));
                        break;
                    case ("long"):
                        toConsole.append(String.valueOf(field.getLong(object)));
                        break;
                    case (("byte")):
                        toConsole.append(String.valueOf(field.getByte(object)));
                        break;
                    case ("short"):
                        toConsole.append(String.valueOf(field.getShort(object)));
                        break;
                    case ("char"):
                        toConsole.append(String.valueOf(field.getChar(object)));
                        break;
                    case ("boolean"):
                        toConsole.append(String.valueOf(field.getBoolean(object)));
                        break;
                    case ("float"):
                        toConsole.append(String.valueOf(field.getFloat(object)));
                        break;
                    case ("double"):
                        toConsole.append(String.valueOf(field.getDouble(object)));
                        break;
                    default:
                        toConsole.append(field.get(object).toString());
                }
            }
        }
        Class<?>[] interfaces = clazz.getInterfaces();
        if (Arrays.asList(interfaces).contains(Map.class)) {

            Map map = new HashMap();
            map.putAll((Map) object);
            for (Object key : map.keySet()) {
                if (fieldsToCleanup.contains(key)) {
                    k++;
                    ((Map) object).remove(key);
                }
                if (fieldsToOutput.contains(key)) {
                    k++;
                    toConsole.append(((Map) object).get(key));
                }
            }
        }
        if (k == 0) throw new IllegalArgumentException("There is no sush fields");
        return toConsole.toString();

    }
}