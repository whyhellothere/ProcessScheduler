
public class ProcessSchedulerTests {

  public static void main(String[] args) {
    boolean passed = true;
    if(!testEnqueueCustomProcessQueue()) {
      System.out.println("Failed enqueue tests");
      passed = false;
    }
//    if(!testDequeueCustomProcessQueue()) {
//      System.out.println("Failed dequeue tests");
//      passed = false;
//    }
//    if(!testDouble()) {
//      System.out.println("Failed double test");
//      passed = false;
//    }
    if(!testCompareTo()) {
      System.out.println("Failed compare test");
      passed = false;
    }
    if(passed) {
      System.out.println("ALL TESTS PASSED!!!!");
    }
  }
  public static boolean testEnqueueCustomProcessQueue(){
    boolean passed = true;
    CustomProcessQueue q = new CustomProcessQueue();
    q.enqueue(new CustomProcess(3));
    if(q.peek().getBurstTime()!=3) {
      passed = false;
    }
    q.enqueue(new CustomProcess(10));
    if(q.peek().getBurstTime()!=3) {
      passed = false;
    }
    q.enqueue(new CustomProcess(1));
    if(q.peek().getBurstTime()!=1) {
      passed = false;
    }
    q.enqueue(new CustomProcess(0));
    q.enqueue(new CustomProcess(4));
    if(q.peek().getBurstTime()!=1) {
      passed = false;
    }
    q.printHeap();
    return passed;
  } // checks the correctness of the enqueue 
  // operation implemented in the CustomProcessQueue class
  public static boolean testDequeueCustomProcessQueue(){
    boolean passed = true;
    CustomProcessQueue q = new CustomProcessQueue();
    q.enqueue(new CustomProcess(8));
    q.enqueue(new CustomProcess(4));
    q.enqueue(new CustomProcess(5));
    if(q.dequeue().getBurstTime()!=4) {
      passed = false;
    }
    if(q.dequeue().getBurstTime()!=5) {
      passed = false;
    }
    if(q.peek().getBurstTime()!=8) {
      passed = false;
    }
    q.printHeap();
    return passed;
  } // checks the correctness of the dequeue 
  // operation implemented in the CustomProcessQueue class
  
  public static boolean testDouble() {
    CustomProcessQueue q = new CustomProcessQueue();
    for(int i=1; i<45; i++) {
      q.enqueue(new CustomProcess(i));
    }
    System.out.println(q.getHeap().length);
    if(q.getHeap().length==80) {
      return true;
    }
    return false;
  }
  
  public static boolean testCompareTo() {
    CustomProcess a = new CustomProcess(10);
    CustomProcess b = new CustomProcess(10);
    CustomProcess c = new CustomProcess(8);
    CustomProcess d = new CustomProcess(20);
    if(a.compareTo(b) > 0) {
      System.out.println(a.getProcessId());
      System.out.println(b.getProcessId());

      System.out.println(1);
      return false;
    }
    if(a.compareTo(c)<0) {
      System.out.println(2);

      return false;
    }
    if(a.compareTo(d) > 0) {
      System.out.println(3);

      return false;
    }
    return true;
  }

}
