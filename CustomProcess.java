
public class CustomProcess implements Comparable<CustomProcess>{

  private static int nextProcessId = 1; // stores the id to be assigned to the next process 
  // to be created
  private final int PROCESS_ID; // unique identifier for this process
  private int burstTime; // time required by this process for CPU execution
  
  public CustomProcess(int burstTime) { 
    this.burstTime = burstTime;
    this.PROCESS_ID = nextProcessId;
    nextProcessId++;
  }
  
  public int getProcessId() {
    return PROCESS_ID;
  }
  
  public int getBurstTime() {
    return burstTime;
  }

  /*
  p1.compareTo(p2) < 0 means that the p1 has higher priority than p2. So, p1 should be run first.
  p1.compareTo(p2) == 0 means that p1 and p2 have exactly the same priority.
  p1.compareTo(p2) > 0 means that p1 has lower priority than p2.
  */
  public int compareTo(CustomProcess other) {
    int compare = (burstTime - other.burstTime);
    if (compare == 0) {
      if (PROCESS_ID < other.PROCESS_ID) {
        return -1;
      }
      return 1;
    }
    if (compare < 0) {
      return -1;
    }
    return 1;
  }
  
  
}
