// class A implements Runnable{
//     public void run(){
//         for (int i = 0; i < 100; i++) {
//             System.out.println("Say Hi");
//             try {
//                 Thread.sleep(100);
//             } catch (InterruptedException e) {
//                 System.out.println(e.getStackTrace());
//             }
//         }
//     }
// }
// class B implements Runnable{
//     public void run(){
//         for (int i = 0; i < 100; i++) {
//             System.out.println("Say Hello");
//             try {
//                 Thread.sleep(100);
//             } catch (InterruptedException e) {
//                 System.out.println(e.getStackTrace());
//             }
//         }
//     }
// }
public class ThreadPractice3 {
    public static void main(String[] args){
        Runnable obj1 = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("Say Hi");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(e.getStackTrace());
                }
            }
        };
        Runnable obj2 = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("Say Hello");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(e.getStackTrace());
                }
            }
        };
        Thread t1 = new Thread(obj1); 
        Thread t2 = new Thread(obj2);
        t1.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            System.out.println(e.getStackTrace());
        }
        t2.start();
    }
}
