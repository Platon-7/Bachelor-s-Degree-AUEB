package src;

public class List {

    //Represent a node of the singly linked list    
    class Node {

        Suspect data;
        Node next;

        public Node(Suspect data) {
            this.data = data;
            this.next = null;
        }
    }

    //Represent the head and tail of the singly linked list    
    public Node head = null;
    public Node tail = null;
    public int size;

    //addNode() will add a new node to the list    
    public void addNode(Suspect data) {
        //Create a new node    
        Node newNode = new Node(data);

        //Checks if the list is empty    
        if (head == null) {
            //If list is empty, both head and tail will point to new node    
            head = newNode;
            tail = newNode;
            size++;
        } else {
            //newNode will be added after tail such that tail's next will point to newNode    
            tail.next = newNode;
            //newNode will become new tail of the list    
            tail = newNode;
            size++;
        }
    }
    public int size(){
        return size;
    }

    public void display() {
        //Node current will point to head    
        Node current = head;

        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        System.out.println("Our list contains the following criminals with that last name: ");
        while (current != null) {
            //Prints each node by incrementing pointer  
            System.out.print(current.data.getAFM() + " " + current.data.getFirstName() + " " + current.data.getLastName() + " " + current.data.getSavings() + " " + current.data.getTaxedIncome());
            current = current.next;

            System.out.println();

        }
    }
}
