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
    
    
    PageEditView(ePortfolioModel ePortfol) {
        ePortfolio=ePortfol;
        titleLabel = new Label();studentNameLabel = new Label(); bannerText=new Label();
        titleLabel.setText(pg.getTitle());
        studentNameLabel.setText(pg.getStudentName());
        bannerText.setText(pg.getBannertext());
        getChildren().addAll(titleLabel,studentNameLabel,bannerText);
        if(ePortfolio.getSelectedPage().getBanner()!=""){
            Image bannerimg = new Image(ePortfolio.getSelectedPage().getBanner());
            bannerView.setImage(bannerimg);
            getChildren().add(bannerView);
        }    
    }

    
    
   

    
    
    
}
