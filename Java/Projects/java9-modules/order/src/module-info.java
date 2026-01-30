// Module 2: order — depends on common, re-exports it transitively so payment doesn't need to require common directly
module com.example.order {
    requires transitive com.example.common; // transitive = any module requiring order also gets common for free
    exports com.example.order;             // makes OrderService visible to other modules
}
