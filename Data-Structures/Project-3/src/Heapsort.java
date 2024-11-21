package src;

public class Heapsort {

    RandomizedBST obj = new RandomizedBST();
    public Suspect[] sort(Suspect[] item,int count, int semaphore) {
        for (int i=(count/2)-1; i>= 0;i--) { //kataskeyh swrou (anadiataksh pinaka)
            heapify(item, count, i,semaphore);
        }
        for (int i=count-1;i>0;i--) { 

            Suspect temp = item[0];// vazoume to root sto telos tou swrou
            item[0]=item[i];
            item[i]=temp;
            heapify(item, i, 0,semaphore);

        }
        return item;
    }
        void heapify(Suspect[] item, int n, int i,int semaphore)
    {
        int s = i; // arxikopoioume to megalitero ws root
        int l = 2 * i + 1; 
        int r = 2 * i + 2;  

        if(l<n && item[l].compareTo(item[s],semaphore)<0)
            s=l;

        if (r < n && item[r].compareTo(item[s],semaphore)<0)
            s = r;
 
        // an to megalitero den einai to root kanoume swap 
        if (s != i) {
            Suspect swap = item[i];
            item[i]=item[s];
            item[s]=swap;
            heapify(item, n, s,semaphore);
        }
    }
}



