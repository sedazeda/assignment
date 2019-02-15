package model;

public class Computer {
    private String name;
    private String date;
    private String discontinuedDate;
    private String company;

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    private String dataStatus;

    public Computer(String name, String date, String discontinuedDate, String company, String dataStatus) {
        this.name = name;
        this.date = date;
        this.discontinuedDate = discontinuedDate;
        this.company = company;
        this.dataStatus = dataStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDiscontinuedDate() {
        return discontinuedDate;
    }

    public void setDiscontinuedDate(String discontinuedDate) {
        this.discontinuedDate = discontinuedDate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


}
