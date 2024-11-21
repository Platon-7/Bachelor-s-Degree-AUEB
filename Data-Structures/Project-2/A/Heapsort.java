package A;

public class Heapsort {

    public City[] sort(City[] item,int count) {
        for (int i=(count/2)-1; i>= 0;i--) { //kataskeyh swrou (anadiataksh pinaka)
            heapify(item, count, i);
        }
        for (int i=count-1;i>0;i--) { 

            City temp = item[0];// vazoume to root sto telos tou swrou
            item[0]=item[i];
            item[i]=temp;
            heapify(item, i, 0);

        }
        return item;
    }
        void heapify(City[] item, int n, int i)
    {
        int s = i; // arxikopoioume to megalitero ws root
        int l = 2 * i + 1; 
        int r = 2 * i + 2;  

        if(l<n && item[l].compareTo(item[s])<0)
            s=l;

        if (r < n && item[r].compareTo(item[s])<0)
            s = r;
 
        // an to megalitero den einai to root kanoume swap 
        if (s != i) {
            City swap = item[i];
            item[i]=item[s];
            item[s]=swap;
            heapify(item, n, s);
        }
    }


}
