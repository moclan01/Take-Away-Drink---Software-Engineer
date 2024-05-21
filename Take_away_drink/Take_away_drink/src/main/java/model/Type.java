package model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

import java.io.Serializable;

public class Type implements Serializable {

   private String idtype;
    private String nametype;

    public Type(String idtype, String nametype) {
        this.idtype = idtype;
        this.nametype = nametype;
    }

    public Type(){

    }
    @ColumnName("idtype")
    public String getidtype() {
        return idtype;
    }

    public String getNametype() {
        return nametype;
    }

    public void setIdtype(String idtype) {
        this.idtype = idtype;
    }

    public void setNametype(String nametype) {
        this.nametype = nametype;
    }

    @Override
    public String toString() {
        return "Type{" + "idtype=" + idtype + ", nametype=" + nametype + '}';
    }
}

