package inf300.dominio;


import java.io.Serializable;
import java.util.Date;

/**
 * *<img src="./doc-files/Author.png" alt="Author">
 */
public class Author implements Serializable {

    private static final long serialVersionUID = 8882043540800200706L;

    private final String fname;
    private final String mname;
    private final String lname;
    private final Date birthdate;
    private final String bio;

    /**
     *
     * @param fname
     * @param mname
     * @param lname
     * @param birthdate
     * @param bio
     */
    public Author(String fname, String mname, String lname, Date birthdate,
            String bio) {
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.birthdate = birthdate;
        this.bio = bio;
    }

    /**
     *
     * @return
     */
    public String getFname() {
        return fname;
    }

    /**
     *
     * @return
     */
    public String getLname() {
        return lname;
    }

    /**
     *
     * @return
     */
    public String getMname() {
        return mname;
    }

    /**
     *
     * @return
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     *
     * @return
     */
    public String getBio() {
        return bio;
    }

}
