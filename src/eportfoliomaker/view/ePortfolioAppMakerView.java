/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker.view;

import eportfoliomaker.ErrorHandler;
import eportfoliomaker.controller.ePortfolioController;
import eportfoliomaker.ePortfolioJSONFileManager;
import eportfoliomaker.model.ePortfolioModel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 *
 * @author xgao3
 */
public class ePortfolioAppMakerView {
    
    protected Stage primaryStage;
    protected Scene primaryScene;
    protected BorderPane ePortMakerPane;
    
    BorderPane pageEditWorkspace;
    BorderPane siteViewWorkspace;
    FlowPane workSpaceModeToolbar;//workspace switch toolbar
    Button selectPageEditorWorkspaceButton;
    Button selectSiteViewerWorkspaceButton;
    
    FlowPane fileToolBarPane;//top toolbar
    Button newPortButton;
    Button loadPortButton;
    Button savePortButton;
    Button saveAsPortButton;
    Button exportButton;
    Button exitButton;
    
    FlowPane siteToolbar;//site editing toolbar below filetoolbar
    Button addPageButton;
    Button removePageButton;
    Button selectPageButton;
    
    FlowPane pageEditToolbar;//
    Button selectLayoutTemplateButton;
    
    ePortfolioModel ePortfolio;
    ePortfolioJSONFileManager fileManager;
    private ErrorHandler errorHandler;
    private ePortfolioController controller;
    
    public ePortfolioAppMakerView(ePortfolioController initEPortfolioController){
        controller=initEPortfolioController;
        ePortfolio = new ePortfolioModel(this);
        errorHandler=new ErrorHandler(this);
    }
    
    
    
    
}
