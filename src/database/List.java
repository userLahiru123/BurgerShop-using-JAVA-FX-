package database;

import model.Burger;

public class List {

    // ..........inner class..............
    class Node {
        Node next;
        private Burger data;

        Node(Burger data) {
            this.data = data;
        }

        public Burger getData() {
            return data;
        }
    }

    Node first;

    public int listSize() {
        if (isEmpty()) {
            return 0;
        } else {
            Node temp = first;
            int count = 1;
            while (temp.next != null) {
                temp = temp.next;
                count++;
            }
            return count;
        }
    }

    private boolean isEmpty() {
        return first == null;
    }

    public void add(int index, Burger data) {
        if (index >= 0 && index <= listSize()) {
            Node n1 = new Node(data);
            if (isEmpty()) {
                first = n1;
            } else {
                Node temp = first;
                int count = 1;
                while (count < index) {
                    temp = temp.next;
                    count++;
                }
                n1.next = temp.next;
                temp.next = n1;
            }
        }

    }

    public Burger get(int index) {
        if (index >= 0 && index < listSize()) {
            Node temp = first;
            int count = 0;
            while (count < index) {
                temp = temp.next;
                count++;
            }
            return temp.getData();
        }
        return null;
    }

}
