/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payroll.beans;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import payroll.entities.Division;

/**
 *
 * @author JDM
 */
@Named
@ViewScoped
public class DivisionBean extends PayrollBean {

    @PostConstruct
    public void init() {
        selectedDocument = new Division();
        list = repo.getAll(Division.class);
    }
   
}
