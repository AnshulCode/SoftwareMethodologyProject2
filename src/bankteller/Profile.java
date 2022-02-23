package bankteller;

public class Profile {
    private String fname;
    private String lname;
    private Date dob;

    public Profile(String fname,String lname,String dob){
        this.fname = fname;
        this.lname = lname;
        this.dob = new Date(dob);
    }

    @Override
    public String toString() {
        return this.fname +" "+ this.lname + " " + this.dob.toString();
    }

    @Override
    public boolean equals(Object o) {
        Profile compare = (Profile) o;
        if(this.fname != compare.getfname() || compare.getLname() != this.lname ||
                compare.getDob().compareTo(this.dob) != 0) {
            return false;
        }
        return true;
    }

    public String getfname() {
        return this.fname;
    }

    public String getLname() {
        return this.lname;
    }

    public Date getDob() {
        return this.dob;
    }

}
