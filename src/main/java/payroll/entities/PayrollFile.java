/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payroll.entities;

import java.util.Date;

/**
 *
 * @author JDM
 */
public interface PayrollFile {

    public int getId();

    public Date getCreatedDate();

    public void setId(int id);

    public void setCreatedDate(Date createdDate);

    public PayrollFile copy();
}
