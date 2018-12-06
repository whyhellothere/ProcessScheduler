
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
    System.out.println("index:" + index);
    boolean loop = true;
    if(index>1) {
      System.out.println("test");
      int next = (int) (index)/2;
      while(loop) {
        System.out.println("heap:");
        printHeap();
        if(heap[index].compareTo(heap[next]) < 0) {
          CustomProcess temp = heap[index];
          heap[index] = heap[next];
          heap[next] = temp;
        } else {
          loop = false;
        }
      }
    }

    //    if (index != 1) {
    //      int test = (int) Math.floor((index - 1)/ 2) + 1;
    //      if (heap[(int) Math.floor((index - 1)/ 2) + 1].getBurstTime() > heap[index].getBurstTime()) {
    //        // Bring the heap[index] up and call recursively again
    //        CustomProcess oldNum = heap[Math.floorDiv(index - 1, 2) + 1];
    //        heap[(int) Math.floor((index - 1)/ 2) + 1] = heap[index];
    //        heap[index] = oldNum;
    //        minHeapPercolateUp((int) Math.floor((index - 1)/ 2) + 1);
    //      }
    //    }

  }

  private void minHeapPercolateDown(int index) { 

    if (2 * index < (size)) {
      if (heap[(2 * index)].getBurstTime() < heap[index].getBurstTime()) {
        CustomProcess oldNum = heap[index];
        heap[index] = heap[(2 * index)];
        heap[(2 * index)] = oldNum;
        minHeapPercolateDown((2 * index));
      }
      if ((2 * index) + 1 < size) {
        if (heap[(2 * index) + 1].getBurstTime() < heap[index].getBurstTime()) {
          CustomProcess oldNum = heap[index];
          heap[index] = heap[(2 * index) + 1];
          heap[(2 * index) + 1] = oldNum;
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

}
