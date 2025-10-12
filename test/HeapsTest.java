import main.java.uq.comp3506.a2.structures.Entry;
import main.java.uq.comp3506.a2.structures.Heap;
public class HeapsTest {
    public static void main(String[] args) {

        Heap<Integer, String> heap = new Heap<>();

        // Insert multiple entries (min-heap property will be maintained)
        heap.insert(new Entry<>(10, "A"));
        heap.insert(new Entry<>(5, "B"));
        heap.insert(new Entry<>(2, "C"));
        heap.insert(new Entry<>(3, "D"));
        heap.insert(new Entry<>(8, "E"));

        System.out.println("Heap before manual change:");
        System.out.println(heap);

        // Break the heap manually: make index 1's key large
//        heap.data.get(1).setValue("Z");          // optional: change value to see
//        heap.data.get(1).swapValues(heap.data.get(0)); // fake a disorder
//        heap.data.get(1).setValue("B");          // restore value if needed
//        heap.data.get(1).setKey(20);             // <-- if you make key public or add setter

        System.out.println("Heap after manual change (invalid):");
        System.out.println(heap);

        // Now call downHeap to restore min-heap property
//        heap.downHeap(1);

        System.out.println("Heap after downHeap:");
        System.out.println(heap);

        System.out.println("Heap after removeMin:");
        heap.removeMin();
        System.out.println(heap);

        System.out.println("peek min: " + heap.peekMin());

        System.out.println("clear heap");
        heap.clear();
        System.out.println("heap cleared"+ heap);
    }
}
