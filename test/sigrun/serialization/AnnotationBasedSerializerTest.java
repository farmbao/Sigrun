package sigrun.serialization;

import org.junit.Assert;
import org.junit.Test;

/**
 * User: Mikhail Aksenov
 * github.com/mikhail-aksenov
 * mikhail.aksenov@halliburton.com
 * Date: 4/30/12
 * Time: 7:44 PM
 */
public class AnnotationBasedSerializerTest {
    protected static final byte[] SERIALIZED_CLASS = new byte[]{
            0x00, 0x00, 0x00, 0x01, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A
    };

    protected static final TestAnnotatedClass DESERIALIZED_CLASS;

    static {
        DESERIALIZED_CLASS = new TestAnnotatedClass();
        DESERIALIZED_CLASS.setIntField(1);
        DESERIALIZED_CLASS.setShortField((short) 0x0100);
        byte[] field = new byte[10];
        for (int i = 0; i < field.length; i++) {
            field[i] = (byte) (i + 1);
        }
        DESERIALIZED_CLASS.setArray(field);
    }

    @Test
    public void testSerialize() {
        Serializer<TestAnnotatedClass> serializer =
                new AnnotationBasedSerializer<TestAnnotatedClass>(TestAnnotatedClass.class);

        byte[] serialized = serializer.serialize(DESERIALIZED_CLASS);
        Assert.assertArrayEquals(SERIALIZED_CLASS, serialized);
    }

    @Test
    public void testDeserialize() {
        Serializer<TestAnnotatedClass> serializer =
                new AnnotationBasedSerializer<TestAnnotatedClass>(TestAnnotatedClass.class);

        TestAnnotatedClass deserialized = serializer.deserialize(SERIALIZED_CLASS);
        Assert.assertTrue(DESERIALIZED_CLASS.equals(deserialized));
    }
}
