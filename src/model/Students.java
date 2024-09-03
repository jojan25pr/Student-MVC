
package model;

public class Students {
    
    private int id;
    private String names;
    private String lastnames;
    private String document;
    private double averageGrade;
    
    public Students(){
        
    }

    public Students(int id, String names, String lastnames, String document, double averageGrade) {
        this.id = id;
        this.names = names;
        this.lastnames = lastnames;
        this.document = document;
        this.averageGrade = averageGrade;
    }
    
    
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastnames() {
        return lastnames;
    }

    public void setLastnames(String lastnames) {
        this.lastnames = lastnames;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }
    
    
}
