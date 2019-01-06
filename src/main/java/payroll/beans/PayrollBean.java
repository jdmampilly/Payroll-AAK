/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payroll.beans;

import java.io.Serializable;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import payroll.entities.PayrollFile;
import payroll.repositories.PayrollRepository;

/**
 *
 * @author JalalAM
 */
public abstract class PayrollBean implements Serializable {

    @Inject
    protected PayrollRepository repo;
    protected List<PayrollFile> list;
    protected PayrollFile selectedDocument;
    protected boolean showForm;
    protected boolean hasWrite;

    public void onCopyClick(ActionEvent event, PayrollFile file) {
        selectedDocument = file.copy();
        showForm = true;
    }

    public void onEditClick(ActionEvent event, PayrollFile item) {
        selectedDocument = item;
        showForm = true;
    }

    public void onDeleteClick(ActionEvent event, PayrollFile item) {
        repo.delete(item);
        //list.remove(item);
    }

    protected void resetState() {
        showForm = false;
        try {
            selectedDocument = selectedDocument.getClass().newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void onSaveClick(ActionEvent event) {
        if (selectedDocument.getId() > 0) {
            updateDocument();
        } else {
            persistDocument();
        }
    }

    private void persistDocument() {
        selectedDocument = repo.persist(selectedDocument);
    }

    private void updateDocument() {
        selectedDocument = repo.update(selectedDocument);
        resetState();
    }

    public void onCancelClick(ActionEvent event) {
        resetState();
    }

    public boolean getShowForm() {
        return this.showForm;
    }

    public void setShowForm(boolean showForm) {
        this.showForm = showForm;
    }

    public PayrollFile getSelectedDocument() {
        return selectedDocument;
    }

    public void setSelectedDocument(PayrollFile selectedDocument) {
        this.selectedDocument = selectedDocument;
    }

    public List<PayrollFile> getList() {
        return list;
    }

    public void setList(List<PayrollFile> list) {
        this.list = list;
    }
}
