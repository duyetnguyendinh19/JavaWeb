package shop.newshop.Entity;

import javax.persistence.*;

@Entity
@Table(name = "salary")
public class Salary {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "month")
    private String month;

    @Column(name = "nameEmployee")
    private String nameEmployee;

    @Column(name = "count")
    private int count;

    @Column(name = "total")
    private double total;

    @Column(name = "day_off")
    private int dayoff;

    public Salary() {
    }

    public Salary(String month, String nameEmployee, int count, double total, int dayoff) {
        this.month = month;
        this.nameEmployee = nameEmployee;
        this.count = count;
        this.total = total;
        this.dayoff = dayoff;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getDayoff() {
        return dayoff;
    }

    public void setDayoff(int dayoff) {
        this.dayoff = dayoff;
    }
}
