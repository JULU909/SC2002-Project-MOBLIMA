class Person{
    private String firstName;
    private String lastName;
    
    public Person(String firstName, String lastName){
        this.firstName=firstName; this.lastName=lastName;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getLastName(){
        return lastName;
    }
}
/* stuff im leaving here in case need reference later
    public boolean equals(SalePerson other){
        return (this.firstName==other.firstName && this.lastName ==other.lastName);
    }

    public String toString(){
        return String.format("%s, %s : %d", getLastName(),getFirstName(),getTotalSales());
    }

    public int compareTo(SalePerson other){
        if (other.getTotalSales() == this.getTotalSales()){
            return this.getLastName().compareTo(other.getLastName());
        }
        return this.getTotalSales() - other.getTotalSales();

    }
*/