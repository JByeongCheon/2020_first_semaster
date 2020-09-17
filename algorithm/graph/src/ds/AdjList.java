package ds; // ��������Ʈ

import java.util.Iterator;

public class AdjList implements Iterable{
	
	private class Node{
		int vertex; //�ڷ� �� �ٲܶ��� ���⸦ �����Ѵ�.
		Node next;
	}
	
	private Node first;
	
	public void add(int d) { //���⵵ ����
		Node oldfirst = first;
		first = new Node();
		first.vertex = d; //���⵵ ����
		first.next = oldfirst;
	}
	
	private class ListTIerator implements Iterator{ //hasNext, next() �ʼ�
		private Node current = first;
		
		public boolean hasNext() {
			return current != null;
		}
		
		public Object next() {
			int v = current.vertex; //���⵵ ����
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
