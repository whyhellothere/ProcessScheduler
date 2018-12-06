
public class CustomProcessQueue implements WaitingQueueADT<CustomProcess>{

  private static final int INITIAL_CAPACITY = 40; // the initial capacity of the heap
  private CustomProcess[] heap; // array-based min heap storing the data. This is an oversize array
  private int size; // number of CustomProcesses present in this CustomProcessQueue

  public CustomProcessQueue() {
    heap = new CustomProcess[INITIAL_CAPACITY];
  }

  public CustomProcess dequeue() {
    if (!isEmpty()) {
      CustomProcess top = heap[1];
      heap[1] = heap[size];
      heap[size] = null;
      size--;
      minHeapPercolateDown(1);
      return top;
    }
    return null;
  }

  public void enqueue(CustomProcess p) {
    if(p.getBurstTime()<=0) {
      return;
    }
    if(size >= INITIAL_CAPACITY-1) {
      doubleSize();
    }
    size++;
    heap[size] = p;
    minHeapPercolateUp(size);
  }

  private void minHeapPercolateUp(int index) { 
    boolean loop = true;
    if(index>1) {
      int next = index/2;
      while(loop) {
        if(heap[index].compareTo(heap[next]) < 0) {
          CustomProcess temp = heap[index];
          heap[index] = heap[next];
          heap[next] = temp;
          index = next;
        } else {
          loop = false;
        }
      }
    }
  }

  private void minHeapPercolateDown(int index) { 
    boolean loop = true;

    while(loop) { // loops continuously
      if(index!=0 && index<size) { // makes sure the indexes are in valid range
        int next = index*2; // finds next index at leftmost branch
        System.out.println("hi");
        if(heap[next+1]==null) { // if there is no right branch
          System.out.println("hiiii");
          if(heap[index].compareTo(heap[next]) > 0) { // if the next is smaller
            System.out.println("hey");
            CustomProcess temp = heap[index]; // temporarily saves value at index
            heap[index] = heap[next]; // swaps with next
            heap[next] = temp;
            index = next;
          } else {
            loop = false; // if the next is not smaller then end loop
          }
        } 
//        else { // if there is a right branch
//          // if one of the branches has a smaller value
//          if(heap[index].compareTo(heap[next]) > 0 || heap[index].compareTo(heap[next]) > 0 ) {
//            // if the smaller value is the right branch then change next
//            if(heap[index].compareTo(heap[next+1]) > 0 && heap[next].compareTo(heap[next+1]) > 0 ) {
//              next += 1;
//            }
//            CustomProcess temp = heap[index];
//            heap[index] = heap[next];
//            heap[next] = temp;
//            index *= 2; // changes to index * 2 because next can become next+1
//          } else {
//            loop = false;
//          }
//        }
        System.out.println(loop);
      }
    }
  }


  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return heap[1]==null;
  }

  @Override
  public CustomProcess peek() {
    return heap[1];
  }

  public CustomProcess[] getHeap() {
    return heap;
  }

  public void printHeap() {
    for(int i=1; i<size+1; i++) {
      System.out.println(heap[i].getBurstTime() + " " + heap[i].getProcessId());
    }
  }

  private void doubleSize() {
    CustomProcess[] temp = new CustomProcess[heap.length*2];
    for(int i=1; i<heap.length; i++) {
      temp[i] = heap[i];
    }
    heap = temp;
  }

}
