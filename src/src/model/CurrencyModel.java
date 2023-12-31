package model;

public class CurrencyModel {
  private   int id_currency;
   private String name;
   private String code;

    public static final String TABLE_NAME = "currency";
    public static final  String ID = "id_currency";
    public static final  String NAME = "name";
    public static final  String CODE = "code";

    public CurrencyModel(int id_currency, String name , String code) {
        this.id_currency = id_currency;
        this.name = name;
        this.code = name;

    }

    public int getId() {
        return id_currency;
    }

    public void setId(int id) {
        this.id_currency = id;
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
                "id=" + id_currency +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
