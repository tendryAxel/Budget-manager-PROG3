package model;

public class accountModel {
    int id;
    String name;
    int id_currency;

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

    public int getId_currency() {
        return id_currency;
    }

    public void setId_currency(int id_currency) {
        this.id_currency = id_currency;
    }

    public accountModel() {
    }

    public accountModel(int id, String name, int id_currency) {
        this.id = id;
        this.name = name;
        this.id_currency = id_currency;
    }
}
