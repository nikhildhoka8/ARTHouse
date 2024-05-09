import java.io.Serializable;

public class GenericLL <T> implements Serializable{
    // make a generic linked list using the nodes
    Node <T> head;
    int size;
    public static void main(String[] args) {
        GenericLL<Integer> list = new GenericLL();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list.count(list.head));
        System.out.println("\n");
        list.traverse();
        list.remove(1);
        list.traverse();
        System.out.println(list.size());
    }
    public GenericLL(){
        this.head = null;
    } // end constructor
    public void add(Object a){
        if (this.head == null){
            this.head = new Node(a);
        } // end if
        else{
            Node <T> temp = this.head;
            while(temp.getNext() != null){
                temp = temp.getNext();
            } // end while
            temp.setNext(new Node(a));
        } // end else
        this.size++;
    } // end add
    public void remove(int index){
        if (index == 0){
            this.head = this.head.getNext();
        } // end if
        else{
            Node <T> temp = this.head;
            for (int i = 0; i < index - 1; i++){
                temp = temp.getNext();
            } // end for
            temp.setNext(temp.getNext().getNext());
        } // end else
        this.size--;
    } // end removes
    public void traverse(){
        if (this.head == null){
            System.out.println("The list is empty!");
        } // end if
        else{
            Node <T> temp = this.head;
            while(temp != null){
                System.out.println(temp.getData());
                temp = temp.getNext();
            } // end while
        } // end else
    } // end traverse
    public int size(){
        return this.size;
    } // end getSize

    public Node<T> get (int index){
        if (index == 0){
            return this.head;
        } // end if
        else{
            Node <T> temp = this.head;
            for (int i = 0; i < index; i++){
                temp = temp.getNext();
            } // end for
            return temp;
        } // end else
    } // end get
    // count the size of the of the linked list after a particular node recursively
    public int count (Node <T> currentNode){
        if (currentNode == null){
            return 0;
        } // end if
        else{
            return 1 + count(currentNode.getNext());
        } // end else
    } // end count
    public Node <T> getHead(){
        return this.head;
    } // end getHead
    public void swap(Node<T> a, Node<T> b) {
        Node<T> temp = new Node<>(a.getData());
        a.setData(b.getData());
        b.setData(temp.getData());
    } // end swap
} // end class def
