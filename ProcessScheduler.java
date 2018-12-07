//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Access Control
// Files: User.Java, AccessControlTest.java, AccessControl.java
// Course: CS300, Fall, 2018
//
// Author: Daniel Chu
// Email: dchu22@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Tolga Beser
// Partner Email: tbeser@wisc.edu
// Partner Lecturer's Name: Gary Dahl
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _x_ Write-up states that pair programming is allowed for this assignment.
// _x_ We have both read and understand the course Pair Programming Policy.
// _x_ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: N/A
// Online Sources: N/A
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Scanner;

/**
 * Driver class for Process Scheduler. Receives inputs from user and processes commands.
 *
 */
public class ProcessScheduler {

  private int currentTime; // stores the current time after the last run
  private int numProcessesRun; // stores the number of processes run so far
  private CustomProcessQueue queue; // this processing unit's custom process queue

  /**
   * Main method that holds scanner object for reading user inputs. Displays warnings if inputs
   * are illegal 
   * @param args the array of Strings that runs the code
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in); // scanner object
    ProcessScheduler schedule = new ProcessScheduler();
    boolean run = true; // boolean determines if this runs
    startSystem(); // calls user start prompt
    while(run) { // runs as long as user does not call quit
      promptUser(); // calls regular user prompt
      String input = scanner.nextLine().toLowerCase(); // casts next line to lower-case
      String[] inputParsed = input.split(" "); // splits command into array of strings
      // if user schedules a process
      if(inputParsed[0].equals("schedule") || inputParsed[0].equals("s")) { 
        if(Integer.parseInt(inputParsed[1])<=0) { // checks if value is greater than 0
          System.out.println("WARNING: burst time MUST be greater than 0!\n");
          continue;
        }
        if(inputParsed.length!=2) { // if the command is incorrect
          System.out.println("WARNING: Please enter a valid command!\n");
        }
        // schedules new process
        schedule.scheduleProcess(new CustomProcess(Integer.parseInt(inputParsed[1])));
      } else if(inputParsed[0].equals("run") || inputParsed[0].equals("r")) { // if user calls run
        System.out.println(schedule.run());
      } else if(inputParsed[0].equals("quit") || inputParsed[0].equals("q")) {// if user calls quit
        run = false; // sets while loop to false
        exitDialogue(schedule); // displays exit dialogue
      } else { // if commands are none of the above then it is invalid
        System.out.println("WARNING: Please enter a valid command!\n");
      }
    }
  }

  /**
   * Constructor method. Sets currentTime to zero and numProcessesRun to zero. Initializes queue.
   */
  public ProcessScheduler() {
    currentTime = 0;
    numProcessesRun = 0;
    queue = new CustomProcessQueue();
  }

  /**
   * Schedules a process and alerts user
   * @param process
   */
  public void scheduleProcess(CustomProcess process) {
    queue.enqueue(process);
    System.out.println("Process ID " + process.getProcessId() + " scheduled. Burst time = " 
        + process.getBurstTime() + "\n");
  }

  /**
   * Runs the processes queued up and alerts user
   * @return String of alerts describing processes run
   */
  public String run() {
    String result = ""; // string to append all dialogue to
    if(queue.size()<=1) {
      result += "Starting " + queue.size() + " process\n\n";
    } else {
      result += "Starting " + queue.size() + " processes\n\n";
    }
    while(queue.size()!=0) { // as long as the queue has processes
      CustomProcess temp = queue.dequeue();
      result += "Time " + currentTime + " : Process ID " + temp.getProcessId() + " Starting.\n";
      currentTime += temp.getBurstTime();
      numProcessesRun++;
      result += "Time " + currentTime + " : Process ID " + temp.getProcessId() + " Completed.\n";
    }
    result += "\nTime " + currentTime + " : All scheduled processes completed.\n";
    return result;
  }

  /**
   * Displays intro prompt
   */
  private static void startSystem() {
    System.out.println("==========   Welcome to the SJF Process Scheduler App   ========");
    System.out.println();
  }

  /**
   * Displays user command prompt
   */
  private static void promptUser() {
    System.out.println("Enter command:");
    System.out.println("[schedule <burstTime>] or [s <burstTime>]");
    System.out.println("[run] or [r]");
    System.out.println("[quit] or [q]");
    System.out.println();
  }

  /**
   * Displays exit dialogue
   * @param p
   */
  private static void exitDialogue(ProcessScheduler p) {
    System.out.println(p.numProcessesRun + " processes run in " + p.currentTime + " units of time!");
    System.out.println("Thank you for using our scheduler!");
    System.out.println("Goodbye!");
  }

}
