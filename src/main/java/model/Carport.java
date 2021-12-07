package model;

public class Carport {
    private int id;
    private int length;
    private int width;
    private int idRoof;
    private boolean hasShed;
    private int shedLength;
    private int shedWidth;

    public Carport(int length, int width, int idRoof, boolean hasShed) {
        this.length = length;
        this.width = width;
        this.idRoof = idRoof;
        this.hasShed = hasShed;
    }

    public Carport(int length, int width, int idRoof, boolean hasShed, int shedLength, int shedWidth) {
        this.length = length;
        this.width = width;
        this.idRoof = idRoof;
        this.hasShed = hasShed;
        this.shedLength = shedLength;
        this.shedWidth = shedWidth;
    }

    public Carport(int id, int length, int width, int idRoof, boolean hasShed) {
        this.id = id;
        this.length = length;
        this.width = width;
        this.idRoof = idRoof;
        this.hasShed = hasShed;
    }

    public Carport(int id, int length, int width, int idRoof, boolean hasShed, int shedLength, int shedWidth) {
        this.id = id;
        this.length = length;
        this.width = width;
        this.idRoof = idRoof;
        this.hasShed = hasShed;
        this.shedLength = shedLength;
        this.shedWidth = shedWidth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getIdRoof() {
        return idRoof;
    }

    public void setIdRoof(int idRoof) {
        this.idRoof = idRoof;
    }

    public boolean hasShed() {
        return hasShed;
    }

    public void setHasShed(boolean hasShed) {
        this.hasShed = hasShed;
    }

    public String hasShedString() {
        return this.hasShed ? "Ja" : "Nej";
    }

    public int getShedLength() {
        return shedLength;
    }

    public void setShedLength(int shedLength) {
        this.shedLength = shedLength;
    }

    public int getShedWidth() {
        return shedWidth;
    }

    public void setShedWidth(int shedWidth) {
        this.shedWidth = shedWidth;
    }
}
