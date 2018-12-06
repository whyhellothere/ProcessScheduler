
public class CustomProcessQueue implements WaitingQueueADT<CustomProcess>{

  private static final int INITIAL_CAPACITY = 40; // the initial capacity of the heap
  private CustomProcess[] heap; // array-based min heap storing the data. This is an oversize array
  private int size; // number of CustomProcesses present in this CustomProcessQueue

  public CustomProcessQueue() {
    heap = new CustomProcess[INITIAL_CAPACITY];
  }

  public CustomProcess dequeue() {
    if (isEmpty() != true) {
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
    size++;
    heap[size] = p;
    minHeapPercolateUp(size);
  }

  private void minHeapPercolateUp(int index) { 
    boolean loop = true;
    int current = index;
    while(loop) { // continuously
      if(current<=1) {
        loop = false;
      } else if(heap[current].compareTo(heap[current-1])<0) {
        CustomProcess temp = heap[current-1];
        heap[current-1] = heap[current];
        heap[current] = temp;
        current--;
      } else {
        loop = false;
      }
    }
  }

  private void minHeapPercolateDown(int index) { 
//    if (2 * index < (size)) {
//      if (heap[(2 * index)].getBurstTime() < heap[index].getBurstTime()) {
//        CustomProcess oldNum = heap[index];
//        heap[index] = heap[(2 * index)];
//        heap[(2 * index)] = oldNum;
//        minHeapPercolateDown((2 * index));
//      }
//      if ((2 * index) + 1 < size) {
//        if (heap[(2 * index) + 1].getBurstTime() < heap[index].getBurstTime()) {
//          CustomProcess oldNum = heap[index];
//          heap[index] = heap[(2 * index) + 1];
//          heap[(2 * index) + 1] = oldNum;
//          minHeapPercolateDown((2 * index) + 1);
//        }
//      }
//    }
    boolean loop = true;
    int current = index;
    while(loop) { // continuously
      if(current>=size) {
        loop = false;
      } else if(heap[current].compareTo(heap[current+1])>0) {
        CustomProcess temp = heap[current+1];
        heap[current+1] = heap[current];
        heap[current] = temp;
        current++;
      } else {
        loop = false;
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

}
