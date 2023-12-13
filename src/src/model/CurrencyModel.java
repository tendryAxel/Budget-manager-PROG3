package model;

public class CurrencyModel {
  private   int id;
   private String name;
   private String code;

    public static final String TABLE_NAME = "currency";
    public static final  String ID = "id";
    public static final  String NAME = "name";
    public static final  String CODE = "code";

    public CurrencyModel(int id, String name , String code) {
        this.id = id;
        this.name = name;
        this.code = name;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "CurrencyModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
