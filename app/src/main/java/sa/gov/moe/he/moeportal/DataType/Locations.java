package sa.gov.moe.he.moeportal.DataType;

/**
 * Created by mazoo_000 on 13/05/2015.
 */
public class Locations {
    private int id;
    private String Name;
    private String Lat;
    private String Leng;
    private String info;
    private String Email;
    private String Phone;

    public Locations() {
        id = 0;
        Name = " ";
        Lat = " ";
        Leng = " ";
        info = " ";
        Email = " ";
        Phone = " ";
    }
    public Locations(int id, String name, String lat, String leng, String info, String email, String phone) {
        this.id = id;
        Name = name;
        Lat = lat;
        Leng = leng;
        this.info = info;
        Email = email;
        Phone = phone;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getLeng() {
        return Leng;
    }

    public void setLeng(String leng) {
        Leng = leng;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return Phone;
    }

    public void setPhoneNumber(String phone) {
        Phone = phone;
    }
}
