import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sort {
    public static void main(String[] args) throws FileNotFoundException {

        //Imports the fixed SinglyLinkedList from Greedy class
        Scanner scan=new Scanner(new File(args[0]));
        //Executes Greedy algorithm with the decreasingly sorted inputfolderlist

        SinglyLinkedList<Integer> mblist = Greedy.readFile(scan);
        SinglyLinkedList<Disk> diskList = sortExecute(mblist);
        MaxPQ folderList = Greedy.insertMaxPq(diskList);
        
        Greedy.printResult(diskList, folderList);

    }

    public static SinglyLinkedList<Disk> sortExecute(SinglyLinkedList<Integer> inputfolderlist){

        //Transfers the inputfolderlist's data to an array so the sort can begin
        Integer[] aux= new Integer[inputfolderlist.length()];
        for (int k = 0; k < aux.length; k++) {
            aux[k]=inputfolderlist.get(k);
        }

        //Merge Sort     
        mergesort(aux, 0, aux.length-1);

        //Re trasnfer the sorted elements to a new SinglyLinkedList
        SinglyLinkedList<Integer> inputfolderlist1=new SinglyLinkedList<Integer>();
        for (int k =0; k < aux.length; k++) {
            inputfolderlist1.add(aux[k]);
        }

        //Executes Greedy algorithm with the decreasingly sorted inputfolderlist

        return Greedy.greedyMethod(inputfolderlist1);
    }


    //Merge Sort
    static void mergesort(Integer[] aux,int first,int last){
        if (last <= first) return;
        for (int m = 1; m <= last-first; m = m+m){
            for (int i = first; i <= last-m; i += m+m){
                merge(aux, i, i+m-1, min(i+m+m-1, last)); 
            }
        }   
    } 


    //Array merging
    static void merge(Integer[] aux,int first,int middle,int last){
        Integer [] leftArray=new Integer[middle-first+1];
        Integer [] rightArray=new Integer[last-middle];
        for (int i = 0; i < leftArray.length; i++){
            leftArray[i] = aux[first+ i];
        }
        for (int i = 0; i < rightArray.length; i++){
            rightArray[i] = aux[middle+ i + 1];
        }
        int leftIndex = 0;
        int rightIndex = 0;
        for (int i = first; i <last + 1; i++)
        {
            if (leftIndex < leftArray.length && rightIndex < rightArray.length) 
            {
                if (leftArray[leftIndex] > rightArray[rightIndex]) 
                {
                    aux[i] = leftArray[leftIndex];
                    leftIndex++;
                }
                else
                {
                    aux[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            }
            else if (leftIndex < leftArray.length) 
            {
                aux[i] = leftArray[leftIndex];
                leftIndex++;
            } 
            else if (rightIndex < rightArray.length) 
            {
                aux[i] = rightArray[rightIndex];
                rightIndex++;
            }
        }
    }
    
    //Min finding
    static int min(int A, int B) {
        return (A < B)? A: B; 
    }

}
