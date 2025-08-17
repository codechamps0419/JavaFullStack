________________________________________
Core JPA Concepts (1-30)
1.	What is JPA and what is its purpose?
2.	How does JPA differ from JDBC?
3.	Explain the concept of an ORM framework.
4.	What is an Entity and how do you define one?
5.	Explain the @Id and @GeneratedValue annotations.
6.	What are the different primary key generation strategies?
7.	What is the EntityManager and its role?
8.	Explain the different states of a JPA entity (Transient, Managed, Detached, Removed).
9.	What is a Persistence Context?
10.	What is a Persistence Unit?
11.	Explain JPQL (Java Persistence Query Language) and how it's used.
12.	What is a Named Query?
13.	How do you map a One-to-One relationship?
14.	How do you map a One-to-Many relationship?
15.	What is the difference between @OneToMany and @ManyToOne?
16.	How do you map a Many-to-Many relationship?
17.	What are Fetch Types? Explain EAGER vs. LAZY loading.
18.	What is the N+1 select problem and how do you solve it?
19.	Explain what cascading is and give an example.
20.	What is the purpose of the @Transactional annotation?
21.	What is the difference between EntityManager.persist() and EntityManager.merge()?
22.	How do you handle transactions in JPA?
23.	What are JPA/Hibernate caches?
24.	Explain the difference between first-level and second-level cache.
25.	How do you handle optimistic locking?
26.	What is the purpose of the @Version annotation?
27.	What are the common JPA annotations for column mapping?
28.	Explain the difference between EntityManager.find() and EntityManager.getReference().
29.	How do you perform a native SQL query with JPA?
30.	What is a Data Transfer Object (DTO) and why is it used with JPA?
________________________________________
Spring Data JPA Specifics (31-60)
31.	What is Spring Data JPA and its main purpose?
32.	What is the purpose of the @Repository annotation?
33.	Explain the repository hierarchy (CrudRepository, PagingAndSortingRepository, JpaRepository).
34.	What are derived query methods? Provide an example.
35.	What is the purpose of the @Query annotation?
36.	When would you use @Query instead of a derived query method?
37.	How do you pass parameters to a custom @Query?
38.	Explain the difference between save() and saveAndFlush().
39.	How do you handle pagination and sorting in Spring Data JPA?
40.	What is the Pageable interface?
41.	How do you implement custom repository methods?
42.	What is the role of the @Modifying annotation with @Query?
43.	What is the difference between getOne() and findById()?
44.	How does Spring Data JPA integrate with Spring Boot?
45.	What is JpaSpecificationExecutor and when is it useful?
46.	What is Query by Example?
47.	How do you define a repository for an entity with a composite primary key?
48.	Explain how Spring Data JPA simplifies transaction management.
49.	What is the purpose of @EnableJpaRepositories?
50.	How do you configure a Spring Data JPA application to connect to a database?
51.	What is a JpaRepository and why is it the most commonly used?
52.	How do you use the Sort object for sorting?
53.	How does Spring Data JPA handle exceptions?
54.	What are Entity Lifecycle Events and how are they used?
55.	Explain the purpose of @PrePersist and @PostUpdate.
56.	How do you perform a delete operation in Spring Data JPA?
57.	What are @ManyToOne(optional = false) and what does it mean?
58.	Explain the use of @Column(nullable = false).
59.	How do you map a field to a specific column name?
60.	What is the purpose of @JoinColumn?
________________________________________
Advanced & Scenario-Based Questions (61-100)
61.	You have a User and Role entity with a many-to-many relationship. A developer adds a new User but the roles are not saved. What could be the problem?
62.	Explain the difference between JOIN and LEFT JOIN in a JPQL query.
63.	How would you debug an issue where your JPA queries are slow?
64.	Describe a scenario where you would choose to use native SQL queries.
65.	How would you handle a scenario where you need to query across multiple repositories?
66.	Explain how to manage a large number of entities efficiently in memory.
67.	What are some best practices for designing JPA entities?
68.	How do you perform batch inserts or updates efficiently?
69.	You're getting a LazyInitializationException. What does it mean and how would you fix it?
70.	How do you use @ManyToOne with optional = false to enforce a foreign key constraint?
71.	What is a dirty check in Hibernate/JPA?
72.	How do you prevent duplicate inserts for the same entity?
73.	What is the purpose of flush() and how does it relate to transactions?
74.	How would you implement a soft delete with JPA?
75.	What's the difference between a Projection and a DTO in Spring Data JPA?
76.	Explain what a @NamedEntityGraph is.
77.	How does a NamedEntityGraph help solve the N+1 problem?
78.	What are the different types of inheritance mapping in JPA?
79.	When would you use the @MappedSuperclass annotation?
80.	How would you handle auditing (e.g., created and updated dates) in your entities?
81.	Explain the use of @CreatedDate and @LastModifiedDate.
82.	What is a Criteria API and when would you use it?
83.	What is the difference between @Column and @Basic?
84.	How do you handle Enums in your JPA entities?
85.	What are Optimistic and Pessimistic locking strategies?
86.	How would you test a Spring Data JPA repository?
87.	What is the purpose of the @Transactional(readOnly = true) annotation?
88.	When would you use a Streamable return type from a repository method?
89.	How do you handle a Composite Primary Key?
90.	Explain the role of the @MapsId annotation.
91.	What is a QuerydslPredicateExecutor?
92.	How do you perform a dynamic query without writing a custom repository?
93.	What are the different types of ID generation strategies?
94.	How does Spring Data JPA manage the EntityManager and PersistenceContext?
95.	What are some performance-tuning strategies for a Spring Data JPA application?
96.	How would you handle a situation where you need to save multiple related entities in a single transaction?
97.	Explain the @TransactionalEventListener annotation.
98.	What is the spring.jpa.hibernate.ddl-auto property and its values?
99.	How do you handle schema updates with tools like Flyway or Liquibase?
100.	How would you handle large file storage with JPA? (e.g., storing images or documents).


