package bankteller;

/**
 * The type Profile.
 */
public class Profile {
    private String fname;
    private String lname;
    private Date dob;

    /**
     * Instantiates a new Profile.
     *
     * @param fname the fname
     * @param lname the lname
     * @param dob   the dob
     */
    public Profile(String fname, String lname, String dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = new Date(dob);
    }

    @Override
    public String toString() {
        return this.fname + " " + this.lname + " " + this.dob.toString();
    }

    @Override
    public boolean equals(Object o) {
        Profile compare = (Profile) o;

        return (this.fname.equalsIgnoreCase(compare.getfname()) && this.lname.equalsIgnoreCase(compare.getLname())  &&
                compare.getDob().compareTo(this.dob) == 0);
    }

    /**
     * Gets .
     *
     * @return the
     */
    public String getfname() {
        return this.fname;
    }

    /**
     * Gets lname.
     *
     * @return the lname
     */
    public String getLname() {
        return this.lname;
    }

    /**
     * Gets dob.
     *
     * @return the dob
     */
    public Date getDob() {
        return this.dob;
    }

}
