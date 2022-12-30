import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class FunctionalityTesting {
    public static void main(String[] args) {
        fileGenerator();
    }
    public static int generateNums(){
        double num=0;
        num=Math.random()*(1000000)+1;
        return (int)num;
    }

    public static void fileGenerator(){
        
        String fileName= "testinput";

        SinglyLinkedList<Integer> N=numbersOfFolders();

        SinglyLinkedList<Integer> Greedy_disks=new SinglyLinkedList<Integer>();
        SinglyLinkedList<Integer> Sorted_disks=new SinglyLinkedList<Integer>();

        for (int i = 0; i < N.length(); i++) {

            SinglyLinkedList<Integer> Greedy_disks_perN=new SinglyLinkedList<Integer>();
            SinglyLinkedList<Integer> Sorted_disks_perN=new SinglyLinkedList<Integer>();
            int sum1=0;
            int sum2=0;

             for (int j = 1; j <= 10; j++) {
                try {

                    File myObj = new File(fileName+"N"+N.get(i)+"_"+j+".txt");
                    FileWriter myWriter = new FileWriter(fileName+"N"+N.get(i)+"_"+j+".txt");

                    for (int index = 0; index < N.get(i); index++) {
                        myWriter.write(generateNums()+"");
                        if(index<N.get(i)-1){
                            myWriter.write("\n");
                        }
                    }
                    myWriter.close();
                    
                    Scanner reader=new Scanner(myObj);
                    Scanner reader2=new Scanner(myObj);

                    System.out.println("...");

                    Greedy_disks_perN.add(Greedy.greedyMethod(Greedy.readFile(reader)).length());
                    Sorted_disks_perN.add(Sort.sortExecute(Greedy.readFile(reader2)).length());
                    
                    
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
            for (int j = 0; j < Greedy_disks_perN.length(); j++) {
                sum1+=Greedy_disks_perN.get(i);
                sum2+=Sorted_disks_perN.get(i);
            }
            if (Greedy_disks_perN.length()!=0){
                Greedy_disks.add(sum1/Greedy_disks_perN.length());
                Sorted_disks.add(sum2/Sorted_disks_perN.length());
            }
        }
        comparisonOutput(Greedy_disks,Sorted_disks);
    }


    public static SinglyLinkedList<Integer> numbersOfFolders(){

        SinglyLinkedList<Integer> N=new SinglyLinkedList<Integer>();


        Scanner num=new Scanner(System.in);

        System.out.println("Enter at least 3 values for the folders to be in each txt file: ");

        for (int i = 0; i < 3; i++) {
            N.add(num.nextInt());
        }

        int answer=0;
        Scanner scan=new Scanner(System.in);

        while(answer!=2){
            System.out.println("Would you like to add another value(1 for yes or 2 for no)?");
            answer=scan.nextInt();
            while(answer!=1 && answer!=2){
                System.out.println("That's not one of the acceptable answer!(1 for yes or 2 for no)");
                System.out.println("Would you like to add another value(1 for yes or 2 for no)?");
                answer=scan.nextInt();
            }
            if(answer==1){
                System.out.println("Enter the value: ");
                N.add(num.nextInt());
            }
        }
        scan.close();
        num.close();
        return N;
        
    }

    public static void comparisonOutput(SinglyLinkedList<Integer> Greedy_disks,SinglyLinkedList<Integer> Sorted_disks){

        int sum1=0;
        int sum2=0;

        for (int i = 0; i < Greedy_disks.length(); i++) {
            if(Greedy_disks.get(i)<Sorted_disks.get(i)){
                sum1++;
                System.out.println("One average the Greedy algoritm generated "+Greedy_disks.get(i)+" disks per N");
                System.out.println("One average the Sort algoritm generated "+Sorted_disks.get(i)+" disks per N");
            }else if(Greedy_disks.get(i)>Sorted_disks.get(i)){
                sum2++;
                System.out.println("One average the Greedy algoritm generated "+Greedy_disks.get(i)+" disks per N");
                System.out.println("One average the Sort algoritm generated "+Sorted_disks.get(i)+" disks per N");
            }else{
                System.out.println("One average both Greedy and Sorted algorithms generated "+Greedy_disks.get(i)+" disks per N");
            }
        }
        if (sum1>sum2){
            System.out.println("The Greedy algorithm was more efficient in average than the Sort one for the Bin Packing problem");
        }else if(sum1<sum2){
            System.out.println("The Sort algorithm was more efficient in average than the Greedy one for the Bin Packing problem");
        }else{
            System.out.println("Both Greedy and Sorted algorithms were equally efficient for the Bin Packing problem");
        }
    }
}