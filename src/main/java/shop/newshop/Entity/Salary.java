package shop.newshop.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "salary")
public class Salary {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "month")
    private int month;

    @Column(name = "year")
    private int year;

    @Column(name = "nameEmployee")
    private String nameEmployee;

    @Column(name = "count")
    private double count;

    @Column(name = "total")
    private double total;

    @Column(name = "day_off")
    private double dayoff;

    @Column(name = "idEmployee")
    private int idEmployee;

    @Column(name = "createDate")
    private Date createDate;

    public Salary() {
    }

    public Salary(int month, int year, String nameEmployee, double count, double total, double dayoff, int idEmployee,Date createDate) {
        this.month = month;
        this.year = year;
        this.nameEmployee = nameEmployee;
        this.count = count;
        this.total = total;
        this.dayoff = dayoff;
        this.idEmployee = idEmployee;
        this.createDate = createDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDayoff() {
        return dayoff;
    }

    public void setDayoff(double dayoff) {
        this.dayoff = dayoff;
    }
}
