import java.util.Scanner;

public class ProcessScheduler {

  private int currentTime; // stores the current time after the last run
  private int numProcessesRun; // stores the number of processes run so far
  private CustomProcessQueue queue; // this processing unit's custom process queue

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    ProcessScheduler schedule = new ProcessScheduler();
    boolean run = true;
    startSystem();
    while(run) {
      promptUser();
      String input = scanner.nextLine().toLowerCase();
      String[] inputParsed = input.split(" ");
      if(inputParsed[0].equals("schedule") || inputParsed[0].equals("s")) {
        if(inputParsed.length!=2 && Integer.parseInt(inputParsed[1])<=0) {
          System.out.println("WARNING: burst time MUST be greater than 0!\n");
          continue;
        }
        schedule.scheduleProcess(new CustomProcess(Integer.parseInt(inputParsed[1])));
      } else if(inputParsed[0].equals("run") || inputParsed[0].equals("r")) {
        System.out.println(schedule.run());
      } else if(inputParsed[0].equals("quit") || inputParsed[0].equals("q")) {
        run = false;
        exitDialogue(schedule);
      } else {
        System.out.println("WARNING: Please enter a valid command!\n");
      }
    }
  }
  
  public ProcessScheduler() {
    currentTime = 0;
    numProcessesRun = 0;
    queue = new CustomProcessQueue();
  }

  public void scheduleProcess(CustomProcess process) {
    queue.enqueue(process);
    System.out.println("Process ID " + process.getProcessId() + " scheduled. Burst time = " + process.getBurstTime() + "\n");
  }

  public String run() {
    String result = "";
    if(queue.size()>=1) {
      result += "Starting " + queue.size() + " process\n\n";
    } else {
      result += "Starting " + queue.size() + " processes\n\n";
    }
    while(queue.size()!=0) {
      CustomProcess temp = queue.dequeue();
      result += "Time " + currentTime + " : Process ID " + temp.getProcessId() + " Starting.\n";
      currentTime += temp.getBurstTime();
      numProcessesRun++;
      result += "Time " + currentTime + " : Process ID " + temp.getProcessId() + " Completed.\n";
    }
    result += "\nTime " + currentTime + " : All scheduled processes completed.\n";
    return result;
  }
  
  private static void startSystem() {
    System.out.println("==========   Welcome to the SJF Process Scheduler App   ========");
    System.out.println();
  }

  private static void promptUser() {
    System.out.println("Enter command:");
    System.out.println("[schedule <burstTime>] or [s <burstTime>]");
    System.out.println("[run] or [r]");
    System.out.println("[quit] or [q]");
    System.out.println();
  }
  
  private static void exitDialogue(ProcessScheduler p) {
    System.out.println(p.numProcessesRun + " processes run in " + p.currentTime + " units of time!");
    System.out.println("Thank you for using our scheduler!");
    System.out.println("Goodbye!");
  }
  
}
