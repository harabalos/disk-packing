
public class Disk implements Comparable<Disk>{

    private int id;
    private int initialspace;
    private SinglyLinkedList<Integer> folders=new SinglyLinkedList<Integer>(); 

    public Disk() { 
        this.id = 0;             
        this.initialspace=1000000; 
        this.folders=new SinglyLinkedList<Integer>();
    }

    public int getFreeSpace() { 
        return this.initialspace;
    }
    

    public int getId(){ 
        return this.id;
    }


    public SinglyLinkedList<Integer> getFolders(){ 
        return this.folders;
    }

    public int compareTo(Disk o) {
        if(getFreeSpace()>o.getFreeSpace()){
            return 1;
        }else if(getFreeSpace()<o.getFreeSpace()){
            return -1;
        }else{
            return 0;
        }
    }


    }
