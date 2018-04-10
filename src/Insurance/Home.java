package Insurance;

public class Home {
    private int homeID;
    private double value;
    private int yearBuilt;
    private int homeType;
    private int heatingType;
    private String postalCode;
    private String address;
    private String city;
    private String province;

    public Home(int homeID, double value, int yearBuilt, int homeType, int heatingType, String postalCode, String address, String city, String province) {
        this.homeID = homeID;
        this.value = value;
        this.yearBuilt = yearBuilt;
        this.homeType = homeType;
        this.heatingType = heatingType;
        this.postalCode = postalCode;
        this.address = address;
        this.city = city;
        this.province = province;
    }

    public int getHomeID() {
        return homeID;
    }

    public double getValue() {
        return value;
    }

    public int getYearBuilt() {
        return yearBuilt;
    }

    public int getHomeType() {
        return homeType;
    }

    public int getHeatingType() {
        return heatingType;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public void setHomeID(int homeID) {
        this.homeID = homeID;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setYearBuilt(int yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public void setHomeType(int homeType) {
        this.homeType = homeType;
    }

    public void setHeatingType(int heatingType) {
        this.heatingType = heatingType;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
