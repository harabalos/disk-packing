import java.io.File;
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


    //The greedy Algorithm that takes as parameter a list and its elements get contributed to disks
    public static SinglyLinkedList<Disk> greedyMethod(SinglyLinkedList<Integer> mblist){
        SinglyLinkedList<Disk> diskList = new SinglyLinkedList<>();
        int counter = 0;
        // Place items one by one
        for (int i = 0; i < mblist.length(); i++) {
            int j;
            // Find the first bin that can accommodate
            for (j = 0; j < counter; j++) {
                if(mblist.get(i)<=diskList.get(j).getFreeSpace()){
                    diskList.get(j).setFreeSpace(mblist.get(i));
                    diskList.get(j).getFolders().add(mblist.get(i));
                    break;
                }
            }
            // If no bin could accommodate 
            if(j==counter){
                //new disk
                Disk disk = new Disk();
                diskList.add(disk);
                disk.setId(j);
                diskList.get(counter).setFreeSpace(mblist.get(i));
                diskList.get(counter).getFolders().add(mblist.get(i));
                counter++;
            }
        }
        return diskList;
    }

    //Function that prints the results we want
    public static void printResult(SinglyLinkedList<Disk> diskList,MaxPQ folderList){

        //the sum of all Mbs
        double sum=0;
        for (int index = 0; index < diskList.length(); index++) {
            sum+=(1000000 - (diskList.get(index)).getFreeSpace());
        }
        //convert to TB
        System.out.println("\nSum of all folders: " + sum/1000000  + " TB");
        System.out.println("Total number of disks used = " + diskList.length());

        //Print the results based in the priority queue that stores the remaining space.
        for (int j = 0; j < diskList.length(); j++) {
            for (int i = 0; i < diskList.length(); i++) {
                if(folderList.get(1) == diskList.get(i).getFreeSpace()){
                    System.out.print(("id "+ diskList.get(i).getId() +" "+ folderList.getMax() + ": "));
                    diskList.get(i).getFolders().display();
                    //End
                    if(folderList.size()==0){return;}
                }
            }       
    }
    }





    public static void main(String[] args) {

        try {
            //read the given txt
            Scanner scan = new Scanner(new File(args[0]));
            SinglyLinkedList<Integer> mblist = readFile(scan);
            //executes Greedy algorithm
            SinglyLinkedList<Disk> diskList = greedyMethod(mblist);
            MaxPQ folderList =  insertMaxPq(greedyMethod(mblist));
            printResult(diskList, folderList);


            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



}