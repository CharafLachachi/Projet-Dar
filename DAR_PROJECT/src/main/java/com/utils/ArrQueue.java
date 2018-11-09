package com.utils;


/**
 * Implementation of the queue ADT using a fixed-length array with floating front and rear.
 **/

public class ArrQueue<D> {
  int capacity; // the capacity of the queue
  public static final int CAPACITY = 100; // default capacity
  D Queue[]; // Generic array used to implement the queue
  int front = 0;
  int rear = 0;

  public ArrQueue() {
    this(CAPACITY);
  }

  public ArrQueue(int cap) {
    capacity = cap;
    Queue = (D[]) new Object[capacity]; // compiler may give warning, but this is ok
  }

  // returns whether the queue is full
  public boolean isFull() {
    return (Queue[front] != null && front == rear);
  }

  // returns whether the queue is empty
  public boolean isEmpty() {
    return (Queue[front] == null);
  }

  // returns the number of elements in the queue
  public int size() {
    if (isFull()) return capacity;
    else return ((rear - front + capacity) % capacity);
  }

  // Inspects and returns the element at the front of the queue
  public D front(){
    if (isEmpty()) {
        System.out.println("Queue is empty.");
        return null;
    }
    return Queue[front];
  }

  // Inserts and element at the rear of the queue
  public synchronized void enqueue(D element) {
    if (size() == capacity) {
       System.out.println("Queue is full.");
       return;
    }
    Queue[rear] = element;
    rear = (rear + 1) % capacity;
  }
  
  // Removes the element at the front of the queue
  public synchronized D dequeue() {
    if (isEmpty()) {
      System.out.println("Queue is empty.");
      return null;
    }
    D element;
    element = Queue[front];
    Queue[front] = null;
    front = (front + 1) % capacity;
    return element;
  }
  
  public String toString() {
    String s;
    int i, size = size();
    s = "["; 
    if (size > 0) s += Queue[front];
    if (size > 1) {
      i = (front + 1) % capacity;
      while ( i != rear) {
        s += ", " + Queue[i];
        i = (i + 1) % capacity;
      }
    }
    return s + "]";
  }

  public void status(String op, Object element) {
    System.out.print("----> " + op);
    if (element != null)
      System.out.print(", returns " + element);
    System.out.println(" size: " + size() + " front: " + front + " rear: " + rear);
  }
  
//  public static void main(String[] args) {
//
//	    ArrQueue<String> B = new ArrQueue<String>();
//	    B.enqueue("Tom");
//	    B.enqueue("Jerry");
//	    B.enqueue("Jack");
//	    B.enqueue("Rabbit");
//	    System.out.println(B.toString());
//
//	    ArrQueue<Integer> A = new ArrQueue<Integer>(4);
//
//	    A.enqueue(7);
//	    A.status("A.enqueue(7)", null);
//	    System.out.println(A.toString());
//
//	    A.enqueue(9);
//	    A.status("A.enqueue(9)", null);
//	    System.out.println(A.toString());
//
//	    A.enqueue(11);
//	    A.status("A.enqueue(11)", null);
//	    System.out.println(A.toString()); 
//
//	    Object o; 
//	    o = A.dequeue();
//	    A.status("A.dequeue()", o);
//	    System.out.println(A.toString());
//
//	    A.enqueue(5);
//	    A.status("A.enqueue(5)", null);
//	    System.out.println(A.toString());
//
//	    o = A.dequeue();
//	    A.status("A.dequeue()", o);
//	    System.out.println(A.toString());
//
//	    A.enqueue(8);
//	    A.status("A.enqueue(8)", null);
//	    System.out.println(A.toString());
//
//	    A.enqueue(9);
//	    A.status("A.enqueue(9)", null);
//	    System.out.println(A.toString());
//
//	    A.enqueue(2);
//	    A.status("A.enqueue(2)", null);
//	    System.out.println(A.toString());
//	  }
}


