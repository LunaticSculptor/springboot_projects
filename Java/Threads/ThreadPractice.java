class A extends Thread{
    public void run(){
        for (int i = 0; i < 100; i++) {
            System.out.println("Say Hi");
        }
    }
}
class B extends Thread{
    public void run(){
        for (int i = 0; i < 100; i++) {
            System.out.println("Say Hello");
        }
    }
}
public class ThreadPractice{
    public static void main(String[] args){
        // System.out.println("Hello World");
        A obj1 = new A();
        B obj2 = new B();
        obj1.start();
        obj2.start();
    }
}