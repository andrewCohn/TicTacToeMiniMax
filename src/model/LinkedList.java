package model;
// Author: Andrew Cohn
public class LinkedList<T> {
    private Node head;
    private int size = 0;
    private class Node{
        private T val;
        private Node next;
        private Node(T val){
            this.val = val;
            this.next = null;
            size++;
        }
        private T getVal(){
            return this.val;
        }
        private void setVal(T val){
            this.val = val;
        }
        private void setNext(Node n){
            this.next = n;
        }
    }
    public void add(T val){
        Node temp = this.head;
        this.head = new Node(val);
        this.head.setNext(temp);
    }
    public void addToEnd(T val){
        Node cur = this.head;
        while (cur.next!=null){
            cur = cur.next;
        }
    }
    public T get(int i){
        if (i >= size){
            throw new IndexOutOfBoundsException("Tried to get value "+String.valueOf(i)+" from linked list of size"+String.valueOf(this.size));
        }
        Node cur = this.head;
        int j = 0;
        while (j != i){
            cur = cur.next;
            j++;
        }
        return cur.val;
    }
}
