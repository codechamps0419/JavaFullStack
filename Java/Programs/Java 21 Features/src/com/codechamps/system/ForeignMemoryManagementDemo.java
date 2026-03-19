/*
@author CodeChamps0419

Demonstrates Java 21 Foreign Function & Memory (FFM) API - Memory Access (JEP 442 - Preview):
- Developer Usage & Enterprise Relevance Note: 
  * Enterprise CRUD / Full-Stack Devs: Rarely touch it. Standard business applications rely on heap-based 
    objects managed automatically by the JVM Garbage Collector.
  * System / Performance-Critical Devs (Game engines, high-frequency trading platforms, custom DB drivers): 
    Actively used to achieve C-like performance, manage off-heap native memory directly, and avoid GC pause overhead.
1. Allocating and managing safe off-heap memory using `Arena` (automatic resource cleanup).
2. Writing and reading primitive values directly into off-heap `MemorySegment` buffers.
3. Bypassing standard JVM heap garbage collection for high-performance data buffers.
*/

package com.codechamps.system;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public class ForeignMemoryManagementDemo {

    public static void main(String[] args) {
        System.out.println("--- Java 21 FFM API Memory Management Demo (JEP 442) ---\n");

        // Use an automatic Arena to safely manage off-heap memory lifecycle
        try (Arena arena = Arena.ofConfined()) {
            
            // 1. Allocate off-heap memory space for 64 bytes
            long byteSize = 64;
            MemorySegment segment = arena.allocate(byteSize);
            System.out.println("1. Allocated " + byteSize + " bytes of safe off-heap memory successfully.");
            System.out.println("   - Segment Byte Size: " + segment.byteSize());
            System.out.println("   - Is segment read-only? " + segment.isReadOnly());

            // 2. Write values into specific memory offsets using ValueLayout specifications
            segment.set(ValueLayout.JAVA_INT, 0, 1337);       // Store an integer at offset 0
            segment.set(ValueLayout.JAVA_LONG, 8, 987654321L); // Store a long integer at offset 8
            
            System.out.println("\n2. Stored primitive data off-heap at designated memory offsets.");

            // 3. Read the data back directly from off-heap memory
            int retrievedInt = segment.get(ValueLayout.JAVA_INT, 0);
            long retrievedLong = segment.get(ValueLayout.JAVA_LONG, 8);

            System.out.println("\n3. Retrieved data from off-heap memory:");
            System.out.println("   - Integer at offset 0: " + retrievedInt);
            System.out.println("   - Long at offset 8: " + retrievedLong);

        } catch (Exception e) {
            System.err.println("FFM execution error: " + e.getMessage());
        }

        // The Arena automatically deallocates all off-heap memory when exiting the try-with-resources block!
        System.out.println("\n4. Arena closed automatically. Off-heap memory reclaimed safely with zero GC overhead.");
    }
}

/*
Output:

--- Java 21 FFM API Memory Management Demo (JEP 442) ---

1. Allocated 64 bytes of safe off-heap memory successfully.
   - Segment Byte Size: 64
   - Is segment read-only? false

2. Stored primitive data off-heap at designated memory offsets.

3. Retrieved data from off-heap memory:
   - Integer at offset 0: 1337
   - Long at offset 8: 987654321

4. Arena closed automatically. Off-heap memory reclaimed safely with zero GC overhead.

*/