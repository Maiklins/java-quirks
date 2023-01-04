import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class QuirksTest {

    @Test
    public void null_reference() {
        Quirks jFall = new Quirks();
        Quirks nothing = null;

        assertDoesNotThrow(() -> jFall.give_me_five());
        assertThrows(NullPointerException.class, () -> nothing.callme());
        assertDoesNotThrow(() -> nothing.give_me_five());
    }

    @Test
    public void double_minimum_value() {
        double zero = 0.0;
        double negative = -1.0;
        assertFalse(zero < Integer.MIN_VALUE);
        assertFalse(zero < Double.NEGATIVE_INFINITY);
        assertFalse(negative < Double.NEGATIVE_INFINITY);
    }

    @Test
    public void big_decimal() {

        BigDecimal bigDecimal1 = new BigDecimal("0.25");
        BigDecimal bigDecimal2 = new BigDecimal(0.25);

        assertEquals(bigDecimal1, bigDecimal2);

        bigDecimal1 = new BigDecimal("0.1");
        bigDecimal2 = new BigDecimal(0.1);

        assertEquals(bigDecimal1, bigDecimal2);
    }

    @Test
    public void non_final_string() {
        Quirks.replaceWith("JFall", "JavaOne");

        System.out.println("We are attending: " + "JFall");

        System.out.println("JFall");

        assertEquals("JFall", "JavaOne");
    }

    @Test
    public void stream_match_all() {
        List<java.lang.String> cities = asList("Amsterdam", "Utrecht", "Alkmaar", "Eindhoven");

        boolean longNameStartingWithA = cities.stream()
            .filter(city -> city.startsWith("A"))
            .allMatch(city -> city.length() >= 7);

        assertTrue(longNameStartingWithA);

        boolean longNameStartingWithZ = cities.stream()
            .filter(city -> city.startsWith("Z"))
            .allMatch(city -> city.length() >= 7);

        assertFalse(longNameStartingWithZ);
    }

    @Test
    public void number_literal_with_leading_0() {
        assertEquals(Integer.valueOf(0123).intValue(), 0123);
        assertEquals(Integer.valueOf(0123).intValue(), 83);
        assertEquals(Integer.valueOf("0123").intValue(), 83);
    }

    @Test
    public void wrapper_class_equality() {
        for(int i = -10; i < 127; i++) {
            Integer a = i, b = i;
            assertSame(a, b);
        }
    }

    @Test
    public void receiving_parameter() {
        Quirks.Receiver r = new Quirks.Receiver();
        assertEquals(5, r.method(5));
    }

    @Test
    public void url_syntax() {

        int i = 2;
        int max = 0;

        https://www.jfall.nl
        {
            if(i < 5) {
                max = 5; break https;
            }
            if(i < 10) {
                max = 10;
            }
        }

        assertEquals(5, max);
    }

    @Test
    public void c_style_array_declaration_of_method() {
        int a[] = Quirks.parameter();
        int[] b = a;

        assertEquals(a, b);
    }

    @Test
    public void mutable_enum() {

        assertEquals("1", Quirks.MyMutableEnum.ONE + "");
        assertEquals("2", Quirks.MyMutableEnum.TWO + "");

        Quirks.MyMutableEnum.THREE.createAMess();

        assertEquals("11", Quirks.MyMutableEnum.ONE + "");
        assertEquals("22", Quirks.MyMutableEnum.TWO + "");
    }

    @Test
    public void var_with_generics() {

        var obj1 = new ArrayList<>();
        obj1 = new ArrayList<>();

//        var obj2 = new ArrayList<>();
//        obj2 = new LinkedList<>();
    }

    @Test
    public void different_string() {
        String event = new String("JFall");
        assertEquals("JFall", event + "");
        assertEquals(8, event.length());
    }

    private static class String {
        java.lang.String value;
        public String(java.lang.String value) {
            this.value = value;
        }

        @Override
        public java.lang.String toString() {
            return "<" + value + ">";
        }

        public int length() {
            return value.length() + 2;
        }
    }

}
