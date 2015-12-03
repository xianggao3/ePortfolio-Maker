/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker.model;

import eportfoliomaker.view.PageEditView;
import eportfoliomaker.view.ePortfolioAppMakerView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author xgao3
 */
public class ePortfolioModel {
    ePortfolioAppMakerView ui;
    ObservableList<String> navbar;
    ObservableList<Page> pages;
    Page selectedPage;
    String studentName;
    PageEditView pv;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public ObservableList<String> getNavbar() {
        return navbar;
    }

    public void setNavbar(ObservableList<String> navbar) {
        this.navbar = navbar;
    }

    public ObservableList<Page> getPages() {
        return pages;
    }

    public Page getSelectedPage() {
        return selectedPage;
    }

    public void setSelectedPage(Page selectedPage) {
        this.selectedPage = selectedPage;
    }
    
    public boolean isPageSelected() {
	return selectedPage != null;
    }
    
    public boolean isSelectedPage(Page testpage) {
	return selectedPage == testpage;
    }

    public ePortfolioAppMakerView getUi() {
        return ui;
    }

    public void setUi(ePortfolioAppMakerView ui) {
        this.ui = ui;
    }

    public PageEditView getPv() {
        return pv;
    }

    public void setPv(PageEditView pv) {
        this.pv = pv;
    }
    
    
    public ePortfolioModel(ePortfolioAppMakerView initUI) {
        initUI=ui;
        pages=FXCollections.observableArrayList();
        navbar = FXCollections.observableArrayList();
        reset();
    }

    public void reset() {
	pages.clear();
        navbar.clear();
	selectedPage = null;
        studentName="";
    }
    
    
    
}
