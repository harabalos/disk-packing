import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sort {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan=new Scanner(new File(args[0]));
        SinglyLinkedList<Integer> mblist = Greedy.readFile(scan);
        SinglyLinkedList<Disk> diskList = sortExecute(mblist);
        MaxPQ folderList = Greedy.insertMaxPq(diskList);
        Greedy.printResult(diskList, folderList);

    }

    public static SinglyLinkedList<Disk> sortExecute(SinglyLinkedList<Integer> inputfolderlist){

        Integer[] aux= new Integer[inputfolderlist.length()];
        for (int k = 0; k < aux.length; k++) {
            aux[k]=inputfolderlist.get(k);
        }

        mergesort(aux, 0, aux.length-1);

        SinglyLinkedList<Integer> inputfolderlist1=new SinglyLinkedList<Integer>();
        for (int k =0; k < aux.length; k++) {
            inputfolderlist1.add(aux[k]);
        }
        return Greedy.greedyMethod(inputfolderlist1);
    }

    static void mergesort(Integer[] aux,int first,int last){
        if (last <= first) return;
        for (int m = 1; m <= last-first; m = m+m){
            for (int i = first; i <= last-m; i += m+m){
                merge(aux, i, i+m-1, min(i+m+m-1, last)); 
            }
        }   
    } 
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

    static int min(int A, int B) {
        return (A < B)? A: B; 
    }

}
