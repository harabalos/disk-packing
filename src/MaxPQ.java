public class MaxPQ {
    private Integer [] heap;
    private int n;

    public MaxPQ(int capacity ){
        heap = new Integer[capacity +1];
        n=0;
    }

    public void insert(int x){
        if(n==heap.length - 1){
            resize(2*heap.length);
        }
        n++;
        heap[n] = x;
        swim(n);
    }

    private void swim(int k){
        while(k>1 && heap[k/2]<heap[k]){
            int temp = heap[k];
            heap[k]=heap[k/2];
            heap[k/2] = temp;
            k/=2;
        }

    }


    private void resize(int capacity) {
        Integer[] temp = new Integer[capacity];
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

    public void printMaxHeap(){
        for (int i = 1; i <= n; i++) {
            System.out.print(heap[i]+" ");
        }
    }

    private void sink(int k){
        while(2*k <= n){
            int j = 2*k;
            if(j<n && heap[j]<heap[j+1]){
                j++;
            }
            if(heap[k]>=heap[j]){
                break;
            }
            swap(k,j);
            k=j;
        }
    }

    public void swap(int a,int b){
        int temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }


    public int getMax(){
        int max = heap[1];
        swap(1,n);
        n--;
        sink(1);
        heap[n+1]=null;
        if(n>0 && (n == (heap.length - 1)/4)){
            resize(heap.length/2);
        }
        return max;

    }

}