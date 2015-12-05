/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker.view;

import eportfoliomaker.model.Component;
import eportfoliomaker.model.Page;
import eportfoliomaker.model.ePortfolioModel;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 *
 * @author xgao3
 */
public class PageEditView extends VBox{
    ePortfolioModel ePortfolio;
    Page pg;
    Label titleLabel;
    Label studentNameLabel;
    ObservableList<ComponentEditView> components;
    ImageView bannerView;
    Label bannerText;
    
    

        public PageEditView(ePortfolioModel ePortfolio) {
            this.ePortfolio = ePortfolio;
            this.titleLabel = new Label();
            this.studentNameLabel = new Label();
            this.bannerView = new ImageView();
            this.bannerText = new Label();
            
        }

    public Page getPg() {
        return pg;
    }

    public void setPg(Page pg) {
        this.pg = pg;
    }
    
    public void addComp(ComponentEditView comp)
    {
        components.add(comp);
        //refresh(comp);
    }
    public void removeComp(ComponentEditView comp){
        components.remove(comp);
    }

    
}

