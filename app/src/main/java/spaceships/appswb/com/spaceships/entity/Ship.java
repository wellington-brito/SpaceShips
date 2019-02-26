package spaceships.appswb.com.spaceships.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

@DatabaseTable(tableName = "ship_table")
public class Ship  implements Serializable, Parcelable {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    @DatabaseField
    private String model;

    @DatabaseField
    private String cost_in_credits;

    @DatabaseField
    private String length;

    @DatabaseField
    private String crew;

    @DatabaseField
    private String passengers;

    @DatabaseField
    private String cargo_capacity;

    @DatabaseField
    private String consumables;

    @DatabaseField
    private String starship_class;

    @DatabaseField
    private String dataAlteracao;

    @DatabaseField
    private String horaAlteracao;



    public Ship(Parcel in) {
        id = in.readInt();
        name = in.readString();
        model = in.readString();
        cost_in_credits = in.readString();
        length = in.readString();
        crew = in.readString();
        passengers = in.readString();
        cargo_capacity = in.readString();
        consumables = in.readString();
        starship_class = in.readString();
        dataAlteracao = in.readString();
        horaAlteracao = in.readString();
    }

    public static final Creator<Ship> CREATOR = new Creator<Ship>() {
        @Override
        public Ship createFromParcel(Parcel in) {
            return new Ship(in);
        }

        @Override
        public Ship[] newArray(int size) {
            return new Ship[size];
        }
    };

    public Ship() {

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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCost_in_credits() {
        return cost_in_credits;
    }

    public void setCost_in_credits(String cost_in_credits) {
        this.cost_in_credits = cost_in_credits;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String lenght) {
        this.length = lenght;
    }

    public String getCrew() {
        return crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }

    public String getPassengers() {
        return passengers;
    }

    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    public String getCargo_capacity() {
        return cargo_capacity;
    }

    public void setCargo_capacity(String cargo_capacity) {
        this.cargo_capacity = cargo_capacity;
    }

    public String getConsumables() {
        return consumables;
    }

    public void setConsumables(String consumables) {
        this.consumables = consumables;
    }

    public String getStarship_class() {
        return starship_class;
    }

    public void setStarship_class(String starship_class) {
        this.starship_class = starship_class;
    }

    public String getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(String dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public String getHoraAlteracao() {
        return horaAlteracao;
    }

    public void setHoraAlteracao(String horaAlteracao) {
        this.horaAlteracao = horaAlteracao;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(model);
        dest.writeString(cost_in_credits);
        dest.writeString(length);
        dest.writeString(crew);
        dest.writeString(passengers);
        dest.writeString(cargo_capacity);
        dest.writeString(consumables);
        dest.writeString(starship_class);
        dest.writeString(dataAlteracao);
        dest.writeString(horaAlteracao);
    }
}
