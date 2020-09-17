package ds; // 인접리스트

import java.util.Iterator;

public class AdjList implements Iterable{
	
	private class Node{
		int vertex; //자료 형 바꿀때는 여기를 수정한다.
		Node next;
	}
	
	private Node first;
	
	public void add(int d) { //여기도 수정
		Node oldfirst = first;
		first = new Node();
		first.vertex = d; //여기도 수정
		first.next = oldfirst;
	}
	
	private class ListTIerator implements Iterator{ //hasNext, next() 필수
		private Node current = first;
		
		public boolean hasNext() {
			return current != null;
		}
		
		public Object next() {
			int v = current.vertex; //여기도 수정
			current = current.next;
			return v;
		}
	}
	
	public Iterator iterator() {
		return new ListTIerator();
	}

	public static void main(String[] args) {
		
		AdjList lst = new AdjList();
		lst.add(3);
		lst.add(5);
		lst.add(1);
		
		for(Object x:lst)
			System.out.println(x);
		

	}

}
