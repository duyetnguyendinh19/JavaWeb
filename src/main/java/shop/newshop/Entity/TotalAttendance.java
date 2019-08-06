package shop.newshop.Entity;

import org.springframework.stereotype.Service;

import java.util.Date;

public class TotalAttendance {

    private int idEmployee;

    private String nameEmployee;

    private float salary;

    private int month;

    private double count;

    private double day_off;

    private double totalSalary;

    public TotalAttendance() {
    }

    public TotalAttendance(int idEmployee, String nameEmployee, float salary, int month, double count, double day_off, double totalSalary) {
        this.idEmployee = idEmployee;
        this.nameEmployee = nameEmployee;
        this.salary = salary;
        this.month = month;
        this.count = count;
        this.day_off = day_off;
        this.totalSalary = totalSalary;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public double getDay_off() {
        return day_off;
    }

    public void setDay_off(double day_off) {
        this.day_off = day_off;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }
}
