/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payroll.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author JDM
 */
@Entity
@Table(name = "dept")
public class Department extends PayrollBase {

    @Column(name = "dept_code")
    private int departmentCode;

    @Column(name = "dept_name")
    private String departmentName;

    @Column(name = "division_code")
    private String divisionCode;

    public int getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(int departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
    }

    
    
    
    @Override
    public Department copy() {
        Department d = new Department();
        return d;
    }

}
