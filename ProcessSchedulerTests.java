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
 * Testing class for ProcessScheduler
 *
 */
public class ProcessSchedulerTests {

  /**
   * Main method for running all the tests
   * @param args is the array of string commands that run the code
   */
  public static void main(String[] args) {
    boolean passed = true; // boolean describing if all tests are passed
    if(!testEnqueueCustomProcessQueue()) {
      System.out.println("Failed enqueue tests");
      passed = false;
    }
    if(!testDequeueCustomProcessQueue()) {
      System.out.println("Failed dequeue tests");
      passed = false;
    }
    if(!testDouble()) {
      System.out.println("Failed double test");
      passed = false;
    }
    if(!testCompareTo()) {
      System.out.println("Failed compare test");
      passed = false;
    }
    if(passed) {
      System.out.println("ALL TESTS PASSED!!!!");
    }
  }
  
  /**
   * Tests the enqueue method in CustomProcessQueue
   * @return
   */
  public static boolean testEnqueueCustomProcessQueue(){
    boolean passed = true;
    CustomProcessQueue q = new CustomProcessQueue();
    q.enqueue(new CustomProcess(3)); // creates new process
    if(q.peek().getBurstTime()!=3) { // checks value of top
      passed = false;
    }
    q.enqueue(new CustomProcess(10)); // creates new process
    if(q.peek().getBurstTime()!=3) { // checks value of top
      passed = false;
    }
    q.enqueue(new CustomProcess(1)); // creates new process
    if(q.peek().getBurstTime()!=1) { // checks value of top
      passed = false;
    }
    q.enqueue(new CustomProcess(0)); // creates new process
    q.enqueue(new CustomProcess(4)); // creates new process
    if(q.peek().getBurstTime()!=1) { // checks value of top
      passed = false;
    }
    return passed;
  } 

  /**
   * Checks for the correct implementation of dequeue() 
   * @return boolean describing test passed
   */
  public static boolean testDequeueCustomProcessQueue(){
    boolean passed = true;
    CustomProcessQueue q = new CustomProcessQueue();
    q.enqueue(new CustomProcess(8)); // creates new process
    q.enqueue(new CustomProcess(4)); // creates new process
    q.enqueue(new CustomProcess(5)); // creates new process
    if(q.dequeue().getBurstTime()!=4) { // checks dequeue value
      passed = false;
    }
    if(q.dequeue().getBurstTime()!=5) { // checks dequeue value
      passed = false;
    }
    if(q.peek().getBurstTime()!=8) { // checks remaining value
      passed = false;
    }
    return passed;
  } 
  
  /**
   * Checks for the correct implementation of doubleSize() 
   * @return boolean describing test passed
   */
  public static boolean testDouble() {
    CustomProcessQueue q = new CustomProcessQueue();
    for(int i=1; i<45; i++) {
      q.enqueue(new CustomProcess(i)); // adds 45 new processes
    }
    System.out.println(q.getHeap().length);
    if(q.getHeap().length==80) { // checks to see if heap size is doubled
      return true;
    }
    return false;
  }
  
  /**
   * Checks for the correct implementation of compareTo() 
   * @return boolean describing test passed
   */
  public static boolean testCompareTo() {
    CustomProcess a = new CustomProcess(10); // adds a new process
    CustomProcess b = new CustomProcess(10); // adds a new process
    CustomProcess c = new CustomProcess(8); // adds a new process
    CustomProcess d = new CustomProcess(20); // adds a new process
    if(a.compareTo(b) > 0) { // checks return value of compareTo()
      return false;
    }
    if(a.compareTo(c)<0) { // checks return value of compareTo()
      return false;
    }
    if(a.compareTo(d) > 0) { // checks return value of compareTo()
      return false;
    }
    return true;
  }

}
