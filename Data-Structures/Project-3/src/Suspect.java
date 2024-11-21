
package src;

import java.util.Comparator;


class Suspect {

    private int AFM; 
    private String firstName; 
    private String lastName; 
    private double savings; 
    private double taxedIncome; 

    public Suspect(int afm, String fName, String lName, double save, double taxes) {
        this.AFM = afm;
        this.firstName = fName;
        this.lastName = lName;
        this.savings = save;
        this.taxedIncome = taxes;
    }

    int key() {
        return AFM;
    } 

    String getFirstName() {
        return this.firstName;
    }

    void setSavings(double savings) {
        this.savings = savings;
    }

    double getSavings() {
        return this.savings;
    }

    double getTaxedIncome() {
        return this.taxedIncome;
    }

    void setTaxedIncome(double taxed) {
        this.taxedIncome = taxed;
    }

    void setFirstName(String fName) {
        this.firstName = fName;
    }

    String getLastName() {
        return this.lastName;
    }

    void setLastName(String lName) {
        this.lastName = lName;
    }

    int getAFM() {
        return this.AFM;
    }

    void setAFM(int afm) {
        this.AFM = afm;
    }

    double getSubtraction() {
        return this.savings - this.taxedIncome;

    }
    double getAssisted(){
        if(this.taxedIncome<9000){
            return 1;
        }else{
            return 0;
        }
    }

    public int compareTo(Suspect o, int semaphore) {// comparator poy kanei oles tis sigkriseis me lambda expressions
        if (semaphore == 0) {
            return Comparator.comparing(Suspect::getAFM)
                    .compare(this, o);
        } else {
            return Comparator.comparing(Suspect::getAssisted)
                    .thenComparing(Suspect::getSubtraction)
                    .compare(this, o);
        }
    }
}



