import java.lang.reflect.Field;

public class Quirks {

    public static int parameter() [] {
        int[] numbers = new int[] {1, 2, 3};

        return numbers;
    }

    public void callme() {

    }

    public static final void replaceWith(String target, String replacement) {
        try {
            Field value = String.class.getDeclaredField("value");
            value.setAccessible(true);
            value.set(target, replacement.getBytes());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void quirk_9_non_final_string() {
        String jFall = "JFall";

        System.out.println(jFall);
        try {
            replaceWith(jFall, "JavaOne");
        } catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println(jFall);

        String s = "JFall";
        System.out.println(s);
    }

    static class Receiver {
        public int method(Receiver this, int number) {
            return number;
        }
    }

    public static int give_me_five() {
        return 5;
    }

    public enum MyMutableEnum {
        ONE(1), TWO(2), THREE(3), FOUR(4);

        int value;

        MyMutableEnum(int value) {
            this.value = value;
        }

        public void createAMess() {
            ONE.value = 11;
            TWO.value = 22;
            THREE.value = 33;
            FOUR.value = 44;
        }

        @Override
        public String toString() {
            return value + "";
        }
    }

}
