package ds;

public class Queue {
	
	private Node first;
	private Node last;
	private int size;
	
	private class Node{
		int vertex;
		Node next;
	}
	
	public void enqueue(int d) {
		Node oldlast = last;
		last = new Node();
		last.vertex = d;
		last.next = null;
		if(isEmpty()) first = last;
		else oldlast.next = last;
		size++;
	}
	
	public int dequeue() {
		int d = first.vertex;
		first = first.next;
		size--;
		if(isEmpty()) last = null;
		return d;
	}
	
	public int size() { return size;}
	public boolean isEmpty() {
		return first == null;
	}

	public static void main(String[] args) {
		
		Queue q = new Queue();
		q.enqueue(3);
		q.enqueue(5);
	
		

	}

}
