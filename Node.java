import java.io.Serializable;

public class Node <T> implements Serializable{
    // make a linkedList with unkown data type that goes one way
    private T data;
    private Node<T> next;
    public Node(T data){
        this.data = data;
        this.next = null;
    } // end constructor
    public Node(T data, Node<T> next){
        this.data = data;
        this.next = next;
    } // end constructor
    public T getData(){
        return this.data;
    } // end getData
    public void setData(T data){
        this.data = data;
    } // end setData
    public Node<T> getNext(){
        return this.next;
    } // end getNext
    public void setNext(Node<T> next){
        if(next == null){
            this.next = null;
            return;
        }
        this.next = next;
    } // end setNext
} // end class def


