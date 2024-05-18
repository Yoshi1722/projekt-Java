package pl.projekt.projekt;

public class Reader {

    private int id;
    private String name;
    private String lastname;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String toString(){
        int var100 = this.getId();
        return "Reader(id=" + var100 + ", name="+this.getName() +" " + this.getLastname();
    }

    public Reader(){}

    public Reader(final int id, final String name, final String last_name, final String email){
       this.id = id;
       this.name = name;
       this.lastname = last_name;
       this.email = email;
    }
}
