
public class Disk implements Comparable<Disk>{

    private int id;
    private int initialspace;
    private SinglyLinkedList<Integer> folders=new SinglyLinkedList<Integer>(); // A list of folders ,basicly a list with the sizes of the folders added in it.


    //Constructor of Dsik class
    public Disk() { 
        this.id = 0;             
        this.initialspace=1000000; //Initializing then space of its disk to 1000000 MB
        this.folders=new SinglyLinkedList<Integer>();
    }

    //Method to get the initialspace
    public int getFreeSpace() { 
        return this.initialspace;
    }
    
    //Method to set the initialspace
    public void setFreeSpace(int data){
        this.initialspace -=data;
    }

    //Method to get the id
    public int getId(){ 
        return this.id;
    }

    //Method to set the id
    public void setId(int id) {
        this.id = id;
    }

    //Method to get the folders
    public SinglyLinkedList<Integer> getFolders(){ 
        return this.folders;
    }

    // The compare method of the Comparable interface which compares the size of two Disk objects
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
