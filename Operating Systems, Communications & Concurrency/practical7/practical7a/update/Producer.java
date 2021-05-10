import java.util.Date;
public class Producer implements Runnable {
 private Buffer buffer;
 private int num;
 public Producer(Buffer buffer,int num) {
 this.buffer = buffer;
 this.num = num;
 }
 public void run() {
 Date message;
 while (true) {
 message = new Date(); // produce an item
 try {
 Thread.sleep(1000); // sleep for 1000 ms
 } catch (InterruptedException e) {}
 buffer.insert(message);
 System.out.println("Producer "+num+": Inserted "+ message);
 }
 } }
