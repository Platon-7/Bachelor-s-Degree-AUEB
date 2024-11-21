package A;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Covid_k {

    static int count = 0;
    static Heapsort swros = new Heapsort();
    static City array[] = new City[999];//o aplos pinakas gia to remove
    static int input;// static metablith poy exei to k input tou xrhsth

    public static void Covid() {
        if (input > count) {// oso einai to count toses fores mphka sthn calculatedensity ara toses poleis
            System.out.println("Wrong input");
            System.exit(0);
        }
		System.out.println("The top "+input+" cities are: ");
        for (int m = 0; m < input; m++) {
            System.out.println(array[m].getName());//to print 
        }
    }

    public static void calculateDensity(int population, int covidcases, String city, int id) { //anagwgh twn krousmatwn ana 50000 katoikous
        double avgcases;
        avgcases = Math.round(500000d * covidcases / population) / 100d;//math round poy stroggylopoiei akribws kai meta dia 100 gia na exei 2 dekadika psifia
        City covid = new City(id, population, city, covidcases, avgcases);
        array[count] = covid;//add to item toy constructor ston pinaka
        count++;//count gia na kserw poses poleis diabasa synolika
    }

    public static void main(String args[]) throws IOException {
        Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the filepath for the input.txt: ");
		String filepath = scanner.nextLine();
        System.out.println("Enter the k variable: ");
        int k = scanner.nextInt();
        input = k;
        try ( BufferedReader reader = new BufferedReader(new FileReader(filepath))){
            for (int i = 0; i < 999; i++) {
                String merge = "";// edo mesa exei to New York ftiagmeno
                String txt = reader.readLine();// diabazo th nea seira
                if (txt == null) {
                    break;
                } else {
                    String[] splitted = txt.split(" ");// splitted = [10] [New] [York] [1000000] [8000]
                    if (splitted.length > 4) { //splitted = [10] [New] [York] [1000000] [8000]
                        int p = 0;
                        for (int l = 0; l < (splitted.length - 3 + p); l++) {
                            String[] anotherArray = new String[splitted.length - 1];
                            if (!merge.equals("")) {//an den einai to proto string poy bazo sto merge
                                merge = merge + " " + splitted[1];// px new + york
                            } else {
                                merge = splitted[1];// an einai to proto den xreiazetai keno prin
                            }
                            System.arraycopy(splitted, 0, anotherArray, 0, 1);//another = [10]
                            System.arraycopy(splitted, 2, anotherArray, 1, splitted.length - 2);
                            splitted = anotherArray;//[10] [York] [1000000] [8000]
                            p++;
                        }
                        String copy[] = new String[splitted.length + 1]; //vazoume ta xwrismena stoixeia se enan pinaka
                        System.arraycopy(splitted, 0, copy, 0, 1);//bazw to prwto stoixeio toy splitted sto copy
                        copy[1] = merge;// bazw sth thesh 1 toy copy to ftiagmeno onoma ths polhs
                        System.arraycopy(splitted, 1, copy, 2, splitted.length - (1));// bazw kai ta ypoloipa sto copy
                        splitted = copy;// to splitted pleon einai idio me to copy

                    }
                    if (Integer.parseInt(splitted[0]) < 1 ^ Integer.parseInt(splitted[0]) > 9999) {
                        System.out.println("Wrong input");//elegxoume an o k pou edwse o xrhsths einai entos twn epitreptwn oriwn
                        System.exit(0);
                    }
                    if ((Integer.parseInt(splitted[2]) < 0) ^ (Integer.parseInt(splitted[2]) > 10000000)) { //elegxoume to population na einai mesa sta kathorismena plaisia
                        System.out.println("Wrong input");// an ena apo ta dyo einai lathos mpes edo, den mporoyn na isxyoyn kai ta dyo opote kano XOR
                        System.exit(0);
                    }
                    if (Integer.parseInt(splitted[3]) > Integer.parseInt(splitted[2])) {
                        System.out.println("Wrong input");//an ta covid cases einai perissotera apo ton plithismo bgale minima lathous
                        System.exit(0);
                    }
                    if (splitted[1].length() > 50) { //elegxoume to name ths polhs na mhn ksepernaei tous 50 xarakthres
                        System.out.println("Wrong input");
                        System.exit(0);
                    }
                    calculateDensity(Integer.parseInt(splitted[2]), Integer.parseInt(splitted[3]), splitted[1], Integer.parseInt(splitted[0]));

                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Wrong filename");
        }
        City b[];
        b = swros.sort(array,count); //to b periexei to sortarismeno arraylist
        Covid();// kalo thn methodo poy tha printarei to apotelesma

    }

}
