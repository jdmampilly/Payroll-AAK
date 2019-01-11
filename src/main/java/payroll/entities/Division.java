/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payroll.entities;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author JDM
 */
@Entity
@Table(name = "division")
public class Division extends PayrollBase {

    @Column(name = "division_code")
    private int divisionCode;

    @Column(name = "division_name")
    private String divisionName;

    public int getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(int divisionCode) {
        this.divisionCode = divisionCode;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.divisionCode;
        hash = 97 * hash + Objects.hashCode(this.divisionName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Division other = (Division) obj;
        if (this.divisionCode != other.divisionCode) {
            return false;
        }
        return Objects.equals(this.divisionName, other.divisionName);
    }

    @Override
    public String toString() {
        return "Division{" + "divisionCode=" + divisionCode + ", divisionName=" + divisionName + '}';
    }

    @Override
    public Division copy() {
        Division d = new Division();
      
        return d;
    }

}
