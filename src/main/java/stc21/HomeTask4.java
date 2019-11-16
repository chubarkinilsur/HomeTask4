package stc21;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * домашнее задание №4 для группы stc21
 *
 * @author Чубаркин Ильсур
 */
public class HomeTask4 {

    /**
     * метод реализован согласно заданию
     *
     * @param object          объект для обработки
     * @param fieldsToCleanup коллекция String для обнуления полей
     * @param fieldsToOutput  коллекция String для вывода на консоль
     * @return возвращяет строку согласно заданию
     * @throws IllegalAccessException вслучае отсутствия наименовая полей в коллекциях fieldsToCleanup,fieldsToOutput выбрасывает исключение.
     */
    public String cleanup(Object object, Set<String> fieldsToCleanup, Set<String> fieldsToOutput) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?> clazz = object.getClass();
        Class<?>[] interfaces = clazz.getInterfaces();
        final boolean isMap = Arrays.asList(interfaces).contains(Map.class);

        handleEx(object, fieldsToCleanup, fieldsToOutput, isMap);
        StringBuilder toConsole = new StringBuilder();
        if (isMap) {
            toConsole.append(processMap(object, fieldsToCleanup, fieldsToOutput));
        }
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if (fieldsToCleanup.contains(field.getName())) {
                cleanUpObject(object, field);
            }
            if (fieldsToOutput.contains(field.getName())) {
                outputObject(object, toConsole, field);
            }
        }
        return toConsole.toString();
    }

    private void handleEx(Object object, Set<String> fieldsToCleanup, Set<String> fieldsToOutput, boolean isMap) {
        Set<String> list = new HashSet<>(fieldsToCleanup);
        list.addAll(fieldsToOutput);
        List<String> listFields = new ArrayList<String>();

        for (Field field : object.getClass().getDeclaredFields()) {
            listFields.add(field.getName());
        }
        if (!list.removeAll(listFields) && !isMap) {
            throw new IllegalArgumentException("There is no such fields!");
        }
    }

    private StringBuilder processMap(Object object, Set<String> fieldsToCleanup, Set<String> fieldsToOutput) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        StringBuilder result = new StringBuilder();
        Class clazz = object.getClass();
        Map map = new HashMap((Map) object);
        for (Object key : map.keySet()) {
            if (fieldsToCleanup.contains(key)) {
                clazz.getMethod("remove", Object.class).invoke(object, key);
            }
            if (fieldsToOutput.contains(key)) {
                result.append(clazz.getMethod("get", Object.class).invoke(object, key));
            }
        }
        return result;
    }

    private void outputObject(Object object, StringBuilder toConsole, Field field) throws IllegalAccessException {
        String typeName = field.getType().getName();
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

    private void cleanUpObject(Object object, Field field) throws IllegalAccessException {
        String typeName = field.getType().getName();
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
}
