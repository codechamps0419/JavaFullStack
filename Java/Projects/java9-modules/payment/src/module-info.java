// Module 3: payment — depends on order only; gets common for free via transitive
module com.example.payment {
    requires com.example.order;  // pulling in order module
    // com.example.common is available transitively via com.example.order — no need to require it again
    exports com.example.payment; // makes PaymentService visible to other modules
}
