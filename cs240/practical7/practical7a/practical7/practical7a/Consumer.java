import java.util.Date;
public class Consumer implements Runnable {
   private Buffer buffer;
   public Consumer(Buffer buffer) {
      this.buffer = buffer;
}
   public void run() {
      Date message;
      while (true) {
         try {
            Thread.sleep(1000); // sleep for 1000 ms
         } catch (InterruptedException e) {}
         message = (Date) buffer.remove();
         // consume the item
         System.out.println("Removed "+ message);
} }
}