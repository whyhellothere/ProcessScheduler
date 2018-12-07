
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
    if (index != 0 && index * 2 <= size) {
      if (heap[index].compareTo(heap[index * 2]) > 0) {
        CustomProcess oldVar = heap[index * 2];
        heap[index * 2] = heap[index];
        heap[index] = oldVar;
        minHeapPercolateDown(2 * index);
      }
      if ((index * 2) + 1 <= size) {
        if (heap[index].compareTo(heap[(index * 2) + 1]) > 0) {
          CustomProcess oldVar = heap[(index * 2) + 1];
          heap[(index * 2) + 1] = heap[index];
          heap[index] = oldVar;
          minHeapPercolateDown((2 * index) + 1);
        }
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
