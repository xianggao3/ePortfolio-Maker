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
import javafx.scene.control.Tab;

/**
 *
 * @author xgao3
 */
public class Page {
    ePortfolioAppMakerView ui;
    
    PageEditView pv;
    String Title;
    String bannerText;
    String banner;
    ObservableList<Component> components;
    Component selectedComp;
    String colorTheme;
    String layoutTheme;
    String pageFont;
    String footer;
    String header;
    String index;
    String studentName;
    Tab tab;

    public Tab getTab() {
        return tab;
    }

    public void setTab(Tab tab) {
        this.tab = tab;
    }

    public String getBannerText() {
        return bannerText;
    }

    public void setBannerText(String bannerText) {
        this.bannerText = bannerText;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public PageEditView getPv() {
        return pv;
    }

    public void setPv(PageEditView pv) {
        this.pv = pv;
    }
    
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public String getPageFont() {
        return pageFont;
    }

    public void setPageFont(String pageFont) {
        this.pageFont = pageFont;
    }

    

    public String getColorTheme() {
        return colorTheme;
    }

    public void setColorTheme(String colorTheme) {
        this.colorTheme = colorTheme;
    }

    public String getLayoutTheme() {
        return layoutTheme;
    }

    public void setLayoutTheme(String layoutTheme) {
        this.layoutTheme = layoutTheme;
    }
    
    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }
    
    public String getBannertext() {
        return bannerText;
    }

    public void setBannertext(String bannertext) {
        this.bannerText = bannertext;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public ObservableList<Component> getComponents() {
        return components;
    }

    public Component getSelectedComp() {
        return selectedComp;
    }

    public void setSelectedComp(Component selectedComp) {
        this.selectedComp = selectedComp;
    }
    public boolean isCompSelected() {
	return selectedComp != null;
    }
    
    public boolean isSelectedComp(Component testcomp) {
	return selectedComp == testcomp;
    }

    
    
    public Page(ePortfolioAppMakerView initUI) {
	ui = initUI;
	components = FXCollections.observableArrayList();
	reset();	
        studentName=initUI.getePortfolio().getStudentName();
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public void reset() {
	components.clear();
	selectedComp = null;
        Title= "New ePortfolio Page";
        bannerText= "None Selected";
        banner= "None Selected";
        colorTheme= "None Selected";
        layoutTheme= "None Selected";
        pageFont= "None Selected";
        footer= "";
        header= "";
    }
    
    public void addComp(Component comp){
        components.add(comp);
        ui.reloadPagePane(pv);

    }

    public void setHeader() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public void setPageEditView(PageEditView pv) {
        this.pv=pv;
    }
}
