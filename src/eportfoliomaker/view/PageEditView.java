/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker.view;

import eportfoliomaker.model.Component;
import eportfoliomaker.model.Page;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author xgao3
 */
public class PageEditView extends VBox{
    ePortfolioAppMakerView ui;
    Label titleLabel;
    Label studentNameLabel;
    ObservableList<ComponentEditView> components;
    
    PageEditView(ePortfolioAppMakerView aThis, Page page) {

    }
    
}
