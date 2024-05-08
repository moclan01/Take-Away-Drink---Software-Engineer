package org.example.take_away_drink.model;

public class Type {

    private String idtype;
    private String nametype;

    public Type(String idtype, String nametype) {
        this.idtype = idtype;
        this.nametype = nametype;
    }
    public Type(){

    }

    public String getNametype() {
        return nametype;
    }

    public void setNametype(String nametype) {
        this.nametype = nametype;
    }

    public String getIdtype() {
        return idtype;
    }

    public void setIdtype(String idtype) {
        this.idtype = idtype;
    }

    @Override
    public String toString() {
        return "Type{" +
                "idtype='" + idtype + '\'' +
                ", nametype='" + nametype + '\'' +
                '}';
    }
}

