## Threads and Synchronization

This lab illustrates the problem of synchronization when many threads are operating on a shared object.  The general issue is called "thread safety", and it is a major cause of errors in computer software.

## Assignment

To the problems on the lab sheet and record your answers here.

1. Record average run times.
2. Write your explanation of the results.  Use good English and proper grammar.  Also use good Markdown formatting.

## ThreadCount run times

These are the average runtime using 3 or more runs of the application.
The Counter class is the object being shared by the threads.
The threads use the counter to add and subtract values.

| Counter class           | Limit              | Runtime (sec)   |
|:------------------------|:-------------------|-----------------|
| Unsynchronized counter  | 10,000,000         | 0.022713        |
| Using ReentrantLock     | 10,000,000         | 2.744698        |
| Synchronized method     | 10,000,000         | 1.127276        |
| AtomicLong for total    | 10,000,000         | 0.468867        |

## 1. Using unsynchronized counter object

1.1) Yes, the total should be zero. No, the total isn't always the same.

1.2) **The average run-times** is 0.022713.

1.3) The counter total sometimes not zero because each thread is parallel working to each others, so, they may not start and finish at the same time. The total is not the same each time because each time we run the threads, it always count the total again.

## 2. Implications for Multi-threaded Applications

How might this affect real applications?

- This behavior can affect real applications by make the bank account not safe and provide some problems like your deposit amount didn't add to your bank account or able to get over withdraw, for example, if two people enter the same account which balance is 40 Baht at different ATM and withdraw 40 Baht at the same time, this behavior will let the process success, so, the amount of money that you withdrew will be 80 Baht while your account balance will decrease only 40 Baht.

## 3. Counter with ReentrantLock

3.1) Yes, the total always zero. **The average run-times** is 2.744698.

3.2) Because we used ReentrantLock solution which is one solution of thread safe instead of unsynchronized counter.

3.3) The *ReentrantLock* will do the last successfully locking first and unlocking the method when finished a thread then call next last successfully locking and do the thread until it finish then unlock the method and call next last successfully locking in queue until all threads done. We use it when we want to do only one thread each time and also want to sequentially do the threads and the thread can be re-enter into lock again before unlocking.

3.4) We write the *finally block* because we want to make sure that it will done the code in try block before do the code in finally block.

## 4. Counter with synchronized method

4.1) The total is zero. **The average run-times** is 1.127276.

4.2) Because we used Synchronized solution which is one solution of thread safe instead of unsynchronized counter.

4.3) The *"synchronized"* means the method can't do two threads at the same time. We use it when we want to do only one thread each time without order.

## 5. Counter with AtomicLong

5.1) Because we used AtomicCounter which is a thread safe instead of unsynchronized counter, so, it's already locking.

5.2) We use *AtomicLong* when we want to use the same resource because it will updated the value automatically. The AtomicLong will gets and adds the given value to the current value. This solution better use with the data that isn't complex or less complex.

## 6. Analysis of Results

6.1) From all thread safe solutions, the **fastest** solution is using *AtomicLong* and the **slowest** solution is using *ReentrantLock*.

6.2) The *ReentrantLock* solution can be applied to the broadest range of problems because we can choose which part in the method that shouldn't let multiple threads do it at the same time and also doing the thread in queue to avoid some problems. For example, in bank application if we applied the ReentrantLock solution in the withdraw method, so, it can do only one thread at a time and the problem that can withdraw with the same account in different place at the same time won't happen. Moreover, the ReentrantLock is unstructured, so, it can even hold a lock across methods and it also performing much better under higher race condition.

## 7. Using Many Threads (optional)

| Counter class           | Limit              | Runtime (sec)   |
|:------------------------|:-------------------|-----------------|
| Unsynchronized counter  | 10,000,000         | 0.093212        |
| Using ReentrantLock     | 10,000,000         | 4.337997        |
| Synchronized method     | 10,000,000         | 5.219846        |
| AtomicLong for total    | 10,000,000         | 3.627217        |

- From all thread safe solutions, the **fastest** solution is using *AtomicLong* and the **slowest** solution is using *Synchronized*.