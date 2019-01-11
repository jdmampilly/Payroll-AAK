/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payroll.beans;

import payroll.entities.Department;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author JDM
 */
@Named
@ViewScoped
public class DepartmentBean extends PayrollBean{
    @PostConstruct
    public void init() {
        selectedDocument = new Department();
        list = repo.getAll(Department.class);
    }
}
