package sigrun.serialization;

import sigrun.NotSupportedException;
import sigrun.common.Reportable;
import sigrun.converters.ByteANumberConverter;
import sigrun.converters.NumberByteAConverter;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * User: Mikhail Aksenov
 * github.com/mikhail-aksenov
 * mikhail.aksenov@halliburton.com
 * Date: 4/29/12
 * Time: 2:27 PM
 */
public class AnnotationBasedSerializer<T extends SerializableData> extends Serializer<T> {
    private Class<T> clazz;

    public AnnotationBasedSerializer(Class<T> clazz) {
        this.clazz = clazz;
    }

    public byte[] serialize(T object) {
        byte[] result = new byte[object.lengthInBytes()];

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Reportable.class)) {
                field.setAccessible(true);
                Reportable reportable = field.getAnnotation(Reportable.class);
                try {
                    result = serializeField(object, field, reportable, result);
                } catch (NotSupportedException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    private byte[] serializeField(T object, Field field, Reportable reportable, byte[] result)
            throws NotSupportedException, IllegalAccessException {
        Class<?> fieldClass = field.getType();
        if (fieldClass.equals(Integer.class) || fieldClass.equals(int.class)) {
            return NumberByteAConverter.writeIntToByteA((Integer) field.get(object), result, reportable.startPosition());
        } else if (fieldClass.equals(Short.class) || fieldClass.equals(short.class)) {
            return NumberByteAConverter.writeShortToByteA((Short) field.get(object), result, reportable.startPosition());
        } else if (fieldClass.equals(byte[].class)) {
            byte[] value = (byte[]) field.get(object);
            for (int i = 0; i < value.length; i++)
                result[i + reportable.startPosition()] = value[i];

            return result;
        } else
            throw new NotSupportedException("Field has a type which is not supported at this moment");
    }


    @Override
    public T deserialize(byte[] bytes) {
        T object = null;
        try {
            object = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            return object;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return object;
        }

        if (bytes.length != object.lengthInBytes())
            throw new IllegalArgumentException();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Reportable.class)) {
                field.setAccessible(true);
                Reportable reportable = field.getAnnotation(Reportable.class);
                try {
                    field.set(object, getValue(field, bytes, reportable));
                } catch (NotSupportedException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return object;
    }

    @Override
    public int lengthOfSerializableObject() {
        try {
            return clazz.newInstance().lengthInBytes();
        } catch (InstantiationException e) {
            e.printStackTrace();
            return 0;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private Object getValue(Field field, byte[] bytes, Reportable reportable) throws NotSupportedException {
        if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
            return ByteANumberConverter.byteAToInt(bytes, reportable.startPosition());
        } else if (field.getType().equals(Short.class) || field.getType().equals(short.class)) {
            return ByteANumberConverter.byteAToShort(bytes, reportable.startPosition());
        } else if (field.getType().equals(byte[].class)) {
            return Arrays.copyOfRange(bytes, reportable.startPosition(), reportable.endPosition());
        } else
            throw new NotSupportedException("Field has a type which is not supported at this moment " + field.getType().getName());
    }
}