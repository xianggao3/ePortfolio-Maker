/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker.model;

import eportfoliomaker.view.ePortfolioAppMakerView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author xgao3
 */
public class ePortfolioModel {
    ePortfolioAppMakerView ui;
    String navbar;
    ObservableList<Page> pages;
    Page selectedPage;

    public String getNavbar() {
        return navbar;
    }

    public void setNavbar(String navbar) {
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
    
    public ePortfolioModel(ePortfolioAppMakerView initUI) {
        initUI=ui;
        pages=FXCollections.observableArrayList();
        reset();
    }

    private void reset() {
	pages.clear();
	selectedPage = null;
    }
    
    
}
