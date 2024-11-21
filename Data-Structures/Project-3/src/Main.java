
package src;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    static RandomizedBST bs = new RandomizedBST();
    static Suspect sp = new Suspect(0, null, null, 0, 0);

    public static void main(String args[]) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the filepath: ");
        String filename = scanner.nextLine();
        bs.load(filename);
        System.out.println("Please enter one of the following numbers:\n 1. Insert Suspect\n 2. Delete Suspect\n 3. Search Suspect\n 4. Update Savings\n 5. See Mean Savings\n 6. See Top Suspects\n 7. See All The Suspects");
        int num = scanner.nextInt();
        if(num==1){
            bs.helping_hand_2--;//metablhth poy bohthaei sta prints
            System.out.println("Please enter the suspect's AFM: ");
            int iAFM = scanner.nextInt();
            sp.setAFM(iAFM);
            System.out.println("Please enter the suspect's first name: ");
            String ifirstname = scanner.next();
            sp.setFirstName(ifirstname);
            System.out.println("Please enter the suspect's last name: ");
            String ilasname = scanner.next();
            sp.setLastName(ilasname);
            System.out.println("Please enter the suspect's savings: ");
            double isavings = scanner.nextDouble();
            sp.setSavings(isavings);
            System.out.println("Please enter the suspect's taxed incoming: ");
            double itaxincoming = scanner.nextDouble();
            sp.setTaxedIncome(itaxincoming);
            bs.insert(sp);

        }
        else if(num==2){
            bs.helping_hand++;
            System.out.println("Please enter the suspect's AFM: ");
            int delAFM = scanner.nextInt();
            bs.remove(delAFM);
        }
        else if(num==3){
            bs.helping_hand++;
            System.out.println("Search by 1. AFM or 2. Last name\n Enter the number (1 or 2): ");
            int numb = scanner.nextInt();
            if(numb ==1){
                System.out.println("Please enter the suspect's AFM: ");
                int sAFM = scanner.nextInt();
                bs.searchByAFM(sAFM);

            }
            else if(numb==2){
                System.out.println("Please enter the suspect's last name: ");
                String lname = scanner.next();
                bs.searchByLastName(lname);
            }
        }
        else if(num==4){
            bs.helping_hand++;
            System.out.println("Please enter the suspect's AFM:");
            int sAFM = scanner.nextInt();
            System.out.println("Please enter the suspect's savings: ");
            double sSavings = scanner.nextDouble();
            bs.updateSavings(sAFM, sSavings);
        }
        else if(num==5){
            System.out.println(bs.getMeanSavings());
        }
        else if(num==6){
            System.out.println("How many suspects do you want to see? ");
            int gnum = scanner.nextInt();
            bs.printΤopSuspects(gnum);
        }
        else if(num==7){
            bs.printByAFM();
        }

    }
}