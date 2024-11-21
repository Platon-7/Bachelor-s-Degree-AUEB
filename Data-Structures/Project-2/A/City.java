package A;

import java.util.Comparator;


public class City implements CityInterface{

    int id;
    int thepopulation;
    String thename;
    int covidcases;
    double avgcovidcases;
    
    public City(int id, int thepopulation,String thename, int covidcases,double avgcovidcases){
        this.id=id;
        this.thepopulation=thepopulation;
        this.thename=thename;
        this.covidcases=covidcases;
        this.avgcovidcases=avgcovidcases;
    }
    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getName() {
        return thename;
    }

    @Override
    public int getPopulation() {
        return thepopulation;
    }

    @Override
    public int getCovidCases() {
        return covidcases;
    }

    @Override
    public void setID(int ID) {
        id = ID;
    }

    @Override
    public void setName(String name) {
        thename = name;
    }

    @Override
    public void setPopulation(int population) {
        thepopulation = population;
    }

    @Override
    public void setCovidCases(int CovidCases) {
        covidcases = CovidCases;
    }

    public double getAvgCovidCases() {
        return avgcovidcases;
    }



    public int compareTo(City o) {// comparator poy kanei oles tis sigkriseis me lambda expressions
        return Comparator.comparing(City::getAvgCovidCases)
                .reversed()
                .thenComparing(City::getName)
                .thenComparingInt(City::getID)
                .reversed()
                .compare(this, o);
    }
}