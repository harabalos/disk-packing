public class SinglyLinkedList <T>{

    private Node<T> head;

    public static class Node<T>{
        private T data;
        private Node<T> next;

        public Node(T data){
            this.data = data;
            this.next = null;
        }
    }

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



}