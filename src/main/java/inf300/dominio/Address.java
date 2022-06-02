package inf300.dominio;


import java.io.Serializable;

/**
 * *<img src="./doc-files/Address.png" alt="Address">
 */
public class Address implements Serializable {

    private static final long serialVersionUID = 3980790290181121772L;

    private final int id;
    private final String street1;
    private final String street2;
    private final String city;
    private final String state;
    private final String zip;
    private final Country country;
    private final String latitude;
    private final String longitude;
    private final String buildingNumber;

    public Address(int id, String street1, String street2, String city, String state, String zip, Country country, String latitude, String longitude, String buildingNumber) {
        this.id = id;
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.buildingNumber = buildingNumber;
    }



    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public String getStreet1() {
        return street1;
    }

    /**
     *
     * @return
     */
    public String getStreet2() {
        return street2;
    }

    /**
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @return
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @return
     */
    public String getZip() {
        return zip;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }
    
    

    /**
     *
     * @return
     */
    public Country getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Address) {
            Address address = (Address) o;
            return street1.equals(address.street1)
                    && street2.equals(address.street2)
                    && city.equals(address.city)
                    && state.equals(address.state)
                    && zip.equals(address.zip)
                    && country.equals(address.country);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return street1.hashCode() + street2.hashCode() + city.hashCode()
                + state.hashCode() + zip.hashCode() + country.hashCode();
    }
}
