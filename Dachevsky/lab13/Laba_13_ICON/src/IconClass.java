public class IconClass {

    static {
        System.loadLibrary("TestSystray");
    }

    static native boolean OnButton1();
    static native boolean OnButton2();

    public static void main(String[] args) {
        try {
            IconClass icon = new IconClass();
            icon.OnButton1();
            Thread.sleep(10000);
            icon.OnButton2();
        } catch( Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
