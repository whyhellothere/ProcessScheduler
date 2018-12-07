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

/**
 * Class for Process objects that can be run in Process Scheduler
 *
 */
public class CustomProcess implements Comparable<CustomProcess>{

  private static int nextProcessId = 1; // stores the id to be assigned to the next process 
  // to be created
  private final int PROCESS_ID; // unique identifier for this process
  private int burstTime; // time required by this process for CPU execution
  
  /**
   * Constructor for CustomProcess. Initializes all variables and updates the next ProcessId
   * @param burstTime is the time this process takes to run
   */
  public CustomProcess(int burstTime) { 
    this.burstTime = burstTime;
    this.PROCESS_ID = nextProcessId;
    nextProcessId++;
  }
  
  /**
   * Getter method for ProcessId
   * @return the Process Id
   */
  public int getProcessId() {
    return PROCESS_ID;
  }
  
  /**
   * Getter method for how long process takes to run
   * @return how long the process takes to run
   */
  public int getBurstTime() {
    return burstTime;
  }

  /**
   * Compares one CustomProcess to another CustomProcess. Returns 1 if the other has higher
   * priority. Returns -1 if the other has lower priority. Returns -1 if burst times for each are
   * the same and other has lower process id. Returns 1 if burst times are the same and other has
   * higher process id
   * @param other is the other CustomProcess being compared to
   */
  public int compareTo(CustomProcess other) {
    int compare = (burstTime - other.burstTime); // finds difference in times
    if (compare == 0) { // if times are equal
      if (PROCESS_ID < other.PROCESS_ID) {
        return -1; // return -1 if this has smaller process id
      }
      return 1; // return 1 if this has bigger process id
    }
    if (compare < 0) { // if difference is negative
      return -1; // return -1
    }
    return 1; // else if difference is positive, return 1
  }
  
  
}
