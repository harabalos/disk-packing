public class MaxPQ {
    // the heap to store data in
    private Integer [] heap;
    // current size of the queue
    private int n;

    public MaxPQ(int capacity ){
        //+1 because the first element is always null
        heap = new Integer[capacity +1];
        n=0;
    }

    //checks if binary heap is empty
    public boolean isEmpty(){
        return n==0;
    }

    //returns the size of the inserted elements in the binary heap
    public int size(){
        return n;
    }


    //Inserts the specified element into this priority queue.
    public void insert(int x){
        // Check available space
        if(n==heap.length - 1){
            resize(2*heap.length);
        }
        // Place item at the next available position
        n++;
        heap[n] = x;
        // Let the newly added item swim
        swim(n);
    }

    //Helper function to put correct items to the top
    private void swim(int k){
        //loop thats checks if its value is greater than its parent, if yes we swipe them, otherwise we do nothing
        while(k>1 && heap[k/2]<heap[k]){
            int temp = heap[k];
            heap[k]=heap[k/2];
            heap[k/2] = temp;
            k/=2;
        }

    }


    //Helper function to resize the size of the heap to a size double of its current length
    private void resize(int capacity) {
        Integer[] temp = new Integer[capacity];
        //iterate through heap and copy it to a new array with the capacity size
        if(capacity >= heap.length){
            for (int i = 0; i < heap.length; i++) {
                temp[i]=heap[i];
            }
        }else{
            for (int i = 0; i < temp.length; i++) {
                temp[i]=heap[i];
            }
        }
        heap=temp;
    }

    //prints all elements of the priority queue
    public void printMaxHeap(){
        for (int i = 1; i <= n; i++) {
            System.out.print(heap[i]+" ");
        }
    }

    //Helper function to put correct items to the bottom
    private void sink(int k){
        //compare the parent with its children
        while(2*k <= n){
            int j = 2*k;
            //go to the next one
            if(j<n && heap[j]<heap[j+1]){
                j++;
            }
            //if parent is more or equal with the child break
            if(heap[k]>=heap[j]){
                break;
            }
            swap(k,j);
            k=j;
        }
    }

    //Helper function to swap two elements in the heap
    public void swap(int a,int b){
        int temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    public int get(int index){
        return heap[index];
    }


    //Retrieves and removes the head of this queue, or returns null if this queue is empty.
    public int getMax(){
        //max element is the first 1
        int max = heap[1];
        //swipe max with the last element
        swap(1,n);
        n--;
        //call sink to put the first item to the correct order again (bottom)
        sink(1);
        //last item disappeared ;)
        heap[n+1]=null;
        //checks if there are many null values in array
        if(n>0 && (n == (heap.length - 1)/4)){
            //cut the array in half
            resize(heap.length/2);
        }
        return max;

    }

}