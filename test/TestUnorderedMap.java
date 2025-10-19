/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2025.
 */

import uq.comp3506.a2.structures.UnorderedMap;

public class TestUnorderedMap {

    public static void main(String[] args) {
        System.out.println("Testing the UnorderedMap Class...");

        UnorderedMap<Integer, String> map = new UnorderedMap<>();

        // Test isEmpty() and size()
        assert map.isEmpty() : "Map should be empty initially";
        assert map.size() == 0 : "Initial size should be 0";

        // Test put()
        System.out.println("\n-- Testing put() --");
        map.put(50, "A");
        map.put(20, "B");
        map.put(70, "C");
        map.put(50, "D"); // update existing key
        System.out.println("Map after insertions: " + map);
        assert map.size() == 3 : "Size should be 3 after adding 3 unique keys";

        // Test get()
        System.out.println("\n-- Testing get() --");
        String val50 = map.get(50);
        String val20 = map.get(20);
        String val70 = map.get(70);
        String val99 = map.get(99); // key not present
        System.out.println("get(50): " + val50); // should be "D"
        System.out.println("get(20): " + val20); // should be "B"
        System.out.println("get(70): " + val70); // should be "C"
        System.out.println("get(99): " + val99); // should be null

        // Test remove()
        System.out.println("\n-- Testing remove() --");
        String removed = map.remove(20);
        System.out.println("Removed 20: " + removed);
        System.out.println("Map after removal: " + map);
        assert map.size() == 2 : "Size should be 2 after removal";

        // Test clear()
        System.out.println("\n-- Testing clear() --");
        map.clear();
        System.out.println("Map after clear(): " + map);
        assert map.isEmpty() : "Map should be empty after clear";

        System.out.println("\nAll tests completed successfully.");
    }
}
