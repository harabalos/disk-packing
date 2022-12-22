public class SinglyLinkedList <T>{

    //Represent a node of the singly linked list  
    private Node<T> head;

    public static class Node<T>{
        private T data;
        private Node<T> next;

        //Constructor Node
        public Node(T data){
            this.data = data;
            this.next = null;
        }
    }

    //Method to get the data
    public T getData() {
        Node<T> current = head;
        while(true){
            if(current.next==null){
                return current.data;
            }
            current = current.next;
        }
        }

    //Method to get the length of the List
    public int length(){
        if(head==null){
            return 0;
        }
        int count = 0;
        Node<T> current = head;
        while(current!=null){
            count++;
            current = current.next;
        }
        return count;
    }


    //Method to display the list
    public void display(){
        Node<T> current = head;
        while(current!=null){
            System.out.print(current.data);
            current= current.next;
            if (current!=null){
                System.out.print(" ");
            }
        }
        System.out.println(" ");
    }


    //Method to add elements to the list
    public void add(T data){
        Node<T> newNode = new Node<T>(data);
        if(head == null){
            head = newNode;
            return;
        }
        Node<T> current = head;
        while(null!=current.next){
            current = current.next;
        }
        current.next = newNode;
    }


    //Method to delete elements from the list
    public void delete(T data){
        Node<T> current = head;
        Node<T> temp = null;
        if(current!=null&&current.data==data){
            head = current.next;
            return;
        }
        while(current!=null&&current.data!=data){
            temp = current;
            current = current.next;
        }
        if(current == null){
            return;
        }
        temp.next = current.next;
    }


    //Method to get the element specified by the index
    public T get(int index)
    {
        Node<T> current = head;
        // index of Node we are currently looking at 
        int count = 0; 
        while (current != null){
            if (count == index){
                return current.data;
            }
            count++;
            current = current.next;
        }
        assert (false);
        return null;
    }


}