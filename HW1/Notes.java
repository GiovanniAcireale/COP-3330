public class Notes {
    /*
    public static void main(String args[]) {
        MyClass myObject = new MyClass(0);
        myObject.setMyInt(2);
        System.out.println(myObject);
    }
    public static void main(String args[]) {
        Test test = new Test();
        Test.data = 0;
        test.data = 2;
        System.out.println(test.data + Test.data);
    }
    public static void main(String args[]) {
        int score = 65;
        switch ( (score%10) % 6 ){
            case 0: System.out.print("0");
            case 1: 
            case 6: System.out.print("1");
            default: System.out.print("2");
        }
        if (score/10 == 6) System.out.print("3");
    }
    public static void main(String args[]) {
        int x = 1, y = 2, z = 3;
        while (x >= 1){
            System.out.print("A");
            if (y != 2)
                System.out.print("B");
            else if (z != 3){
                System.out.print("C");
                x = 1;
            } else
                System.out.print("D");
            --x;
        }
    }
*/
    private static int someMethod(int data){
        return 2 * data;
    }

    public static void main(String args[]) {
        int x = 1;
        int y = someMethod(x);
        System.out.println(x+y);
    }
}
/* 
class Test {
    private static int data;

    public Test() {
        data = 1;
    }
}
class MyClass {
    private int myInt;

    public MyClass(int myInt) {
        this.myInt = myInt;
    }

    public int getMyInt() {
        return myInt;
    }

    public void setMyInt(int myInt) {
        this.myInt = myInt;
    }

}
*/