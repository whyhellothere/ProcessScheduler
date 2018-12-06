
public class ProcessSchedulerTests {

  public static void main(String[] args) {
//    testEnqueueCustomProcessQueue(); 
    testDequeueCustomProcessQueue();
  }
  public static boolean testEnqueueCustomProcessQueue(){
    CustomProcessQueue q = new CustomProcessQueue();
    q.enqueue(new CustomProcess(3));
    q.enqueue(new CustomProcess(10));
    q.enqueue(new CustomProcess(8));
    q.enqueue(new CustomProcess(0));
    q.enqueue(new CustomProcess(4));
    System.out.println("final heap:");
    q.printHeap();
    return false;
  } // checks the correctness of the enqueue 
  // operation implemented in the CustomProcessQueue class
  public static boolean testDequeueCustomProcessQueue(){
    CustomProcessQueue q = new CustomProcessQueue();
    q.enqueue(new CustomProcess(8));
    q.enqueue(new CustomProcess(4));
    q.enqueue(new CustomProcess(5));
    q.printHeap();
    System.out.println(q.dequeue().getBurstTime());
    q.printHeap();
    return false;
  } // checks the correctness of the dequeue 
  // operation implemented in the CustomProcessQueue class


}
