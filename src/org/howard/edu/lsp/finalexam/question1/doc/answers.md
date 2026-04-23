# Question 1

## Part 1:

Shared Resource #1:
`nextId` is a shared resource because multiple threads can access and modify the same counter.

Shared Resource #2:
`requests` is a shared resource because multiple threads can add elements to the same `ArrayList`.

Concurrency Problem:
A race condition may occur. Two or more threads may read or update shared data at the same time, causing duplicate request IDs, skipped IDs, or list corruption.

Why addRequest() is unsafe:
`addRequest()` is unsafe because it calls `getNextId()` and then modifies the shared `requests` list without synchronization. Since `ArrayList` is not thread-safe, concurrent calls to `requests.add()` can cause inconsistent behavior.

## Part 2:

Fix A: Explanation
`public synchronized int getNextId() { ... }` is not a complete fix. It protects the ID generation logic, so duplicate IDs are prevented. However, `requests.add(request)` is still not protected, so multiple threads can still modify the `ArrayList` at the same time.

Fix B: Explanation
`public synchronized void addRequest(String studentName) { ... }` correctly solves the concurrency problem. Synchronizing the entire `addRequest()` method ensures that only one thread at a time can generate an ID and add the request to the list. This protects both shared resources: `nextId` and `requests`.

Fix C: Explanation
`public synchronized List<String> getRequests() { ... }` does not solve the problem. It only synchronizes access when returning the list. It does not protect ID generation or the process of adding new requests, so the race condition still exists.

## Part 3:

Answer + Explanation
No, `getNextId()` should not be public. Based on Arthur Riel's object-oriented design heuristics, implementation details should be hidden. The method is only needed internally by `RequestManager`, so exposing it publicly allows outside code to misuse the ID-generation process. It should be private so the class controls how request IDs are created.

## Part 4:

Description:
An alternative to using the `synchronized` keyword is to use a `Lock`, such as `ReentrantLock`. A lock can be used to protect the critical section where the request ID is generated and the request is added to the shared list. The lock should be acquired before the shared resources are accessed and released in a `finally` block to ensure it is always unlocked.

Code Snippet:
```java
private final Lock lock = new ReentrantLock();

public void addRequest(String studentName) {
    lock.lock();
    try {
        int id = nextId++;
        String request = "Request-" + id + " from " + studentName;
        requests.add(request);
    } finally {
        lock.unlock();
    }
}
```
