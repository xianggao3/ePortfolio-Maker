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
public class Page {
    ePortfolioAppMakerView ui;
    String Title;
    String bannerText;
    String banner;
    ObservableList<Component> components;
    Component selectedComp;
    String colorTheme;
    String layoutTheme;
    String pageFont;
    String footer;

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
    }
    
    public void reset() {
	components.clear();
	selectedComp = null;
    }
    
    public void addComp(Component comp){
        components.add(comp);
        ui.reloadSlideShowPane();

    }
}
