
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Greedy {

    //Function that reads the file from the given txt name
    public static SinglyLinkedList<Integer> readFile(Scanner scan) throws FileNotFoundException{
        SinglyLinkedList<Integer> mblist = new SinglyLinkedList<>();
        while(scan.hasNextLine()){
            mblist.add(Integer.parseInt(scan.nextLine()));
            //if data is falsy given, we quit
            if(mblist.getData()>1000000){
                System.out.println("Error:File is more than 1 TB...");
                System.exit(0);
            }
        }
        return mblist;
    }

    //Function that takes as parameter a Linked list and converts it to a priority queue
    public static MaxPQ insertMaxPq( SinglyLinkedList<Disk> diskList){
        MaxPQ mpq = new MaxPQ(3);
        for (int i = 0; i < diskList.length(); i++) {
            mpq.insert(diskList.get(i).getFreeSpace());
        }
        return mpq;
    }

}