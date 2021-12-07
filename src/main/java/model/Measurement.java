package model;

import java.util.List;

public class Measurement {
    private List<Integer> lengthList;
    private List<Integer> widthList;
    private List<Integer> shedLengthList;
    private List<Integer> shedWidthList;

    public Measurement(List<Integer> lengthList, List<Integer> widthList, List<Integer> shedLengthList, List<Integer> shedWidthList) {
        this.lengthList = lengthList;
        this.widthList = widthList;
        this.shedLengthList = shedLengthList;
        this.shedWidthList = shedWidthList;
    }

    public List<Integer> getLengthList() {
        return lengthList;
    }

    public void setLengthList(List<Integer> lengthList) {
        this.lengthList = lengthList;
    }

    public List<Integer> getWidthList() {
        return widthList;
    }

    public void setWidthList(List<Integer> widthList) {
        this.widthList = widthList;
    }

    public List<Integer> getShedLengthList() {
        return shedLengthList;
    }

    public void setShedLengthList(List<Integer> shedLengthList) {
        this.shedLengthList = shedLengthList;
    }

    public List<Integer> getShedWidthList() {
        return shedWidthList;
    }

    public void setShedWidthList(List<Integer> shedWidthList) {
        this.shedWidthList = shedWidthList;
    }
}

