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
 * Queue for Custom Processes. Contains methods to modify them
 *
 */
public class CustomProcessQueue implements WaitingQueueADT<CustomProcess>{

  private static final int INITIAL_CAPACITY = 40; // the initial capacity of the heap
  private CustomProcess[] heap; // array-based min heap storing the data. This is an oversize array
  private int size; // number of CustomProcesses present in this CustomProcessQueue

  /**
   * Constructor method for CustomProcessQueue. Initializes heap array
   */
  public CustomProcessQueue() {
    heap = new CustomProcess[INITIAL_CAPACITY];
  }

  /**
   * Removes the highest priority entry in the queue
   */
  public CustomProcess dequeue() {
    if (!isEmpty()) {
      CustomProcess top = heap[1]; // stores highest priority process locally
      heap[1] = heap[size]; // sets the bottom process to the top
      heap[size] = null; // removes the bottom process
      size--; // lowers the size accordingly
      minHeapPercolateDown(1); // sorts by percolating down
      return top;
    }
    return null; // return null if empty
  }

  /**
   * Adds an entry into the queue and sorts it
   */
  public void enqueue(CustomProcess p) {
    if(size == (heap.length-1)) { // if size exceeds array length
      doubleSize(); // double it
    }
    size++; // increments size
    heap[size] = p; // puts new process at bottom of heap
    minHeapPercolateUp(size); // sorts by percolating up
  }

  /**
   * Sorts an entry in the queue by moving it up along the array.
   * @param index is the location of the process being sorted
   */
  private void minHeapPercolateUp(int index) { 
    boolean loop = true; // boolean determines if while loop runs
    if(index>1) { // as long as array has more than 1 process
      int next = index/2; // sets next process for next branch
      while(loop) {
        if(heap[index].compareTo(heap[next]) < 0) { // compares processes, true if parent is larger
          CustomProcess temp = heap[index]; // if stores process locally
          heap[index] = heap[next]; // switches next and index
          heap[next] = temp;
          index = next; // sets index to next
        } else {
          loop = false; // quits loop if parent is not larger
        }
      }
    }
  }

  /**
   * Sorts an entry in the queue by moving it down along the array
   * 
   * @param index is the location of the process being sorted
   */
  private void minHeapPercolateDown(int index) {
    if (index != 0 && index * 2 <= size) { // checks to make sure index is in range
      if (heap[index].compareTo(heap[index * 2]) > 0) { // check if the left child node is smaller
        CustomProcess oldVar = heap[index * 2]; // switch the two and call it recursively
        heap[index * 2] = heap[index];
        heap[index] = oldVar;
        minHeapPercolateDown(2 * index);
      }
      if ((index * 2) + 1 <= size) {
        if (heap[index].compareTo(heap[(index * 2) + 1]) > 0) { // check if the right child node is
                                                                // smaller
          CustomProcess oldVar = heap[(index * 2) + 1]; // switch the two and call it recursively
          heap[(index * 2) + 1] = heap[index];
          heap[index] = oldVar;
          minHeapPercolateDown((2 * index) + 1);
        }
      }
    }
  }

  /**
   * getter method for size of the queue
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Checks if the queue is empty
   */
  @Override
  public boolean isEmpty() {
    return heap[1]==null;
  }

  /**
   * Returns the top process in the queue
   */
  @Override
  public CustomProcess peek() {
    return heap[1];
  }

  public CustomProcess[] getHeap() {
    return heap;
  }

  private void doubleSize() {
    CustomProcess[] temp = new CustomProcess[heap.length*2]; // creates new array of twice size
    for(int i=1; i<heap.length; i++) {
      temp[i] = heap[i]; // sets all values in old array to new
    }
    heap = temp; // replaces old array with new
  }

}
