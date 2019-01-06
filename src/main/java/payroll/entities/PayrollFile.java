/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payroll.entities;

/**
 *
 * @author JDM
 */
public interface PayrollFile {

    public int getId();

    public void setId(int id);

    public PayrollFile copy();
}
