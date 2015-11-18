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
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author xgao3
 */
public class ePortfolioAppMakerView {
    
    Stage primaryStage;
    Scene primaryScene;
    BorderPane ePortMakerPane;
    
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
    
    public ePortfolioAppMakerView(ePortfolioJSONFileManager initFileManager){
        fileManager= initFileManager;
        ePortfolio = new ePortfolioModel(this);
        errorHandler=new ErrorHandler(this);
    }
    
    public void startUI(Stage initPrimaryStage, String windowTitle) {
        initFileToolBar();
        initWorkspace();
        //@todo initEventHandlers();
        primaryStage=initPrimaryStage;
        initWindow(windowTitle);
    }    

    private void initFileToolBar() {
    }

    private void initWorkspace() {
    }

    private void initWindow(String windowTitle) {
        primaryStage.setTitle(windowTitle);

	// GET THE SIZE OF THE SCREEN
	Screen screen = Screen.getPrimary();
	Rectangle2D bounds = screen.getVisualBounds();

	// AND USE IT TO SIZE THE WINDOW
	primaryStage.setX(bounds.getMinX());
	primaryStage.setY(bounds.getMinY());
	primaryStage.setWidth(bounds.getWidth());
	primaryStage.setHeight(bounds.getHeight());
        ePortMakerPane=new BorderPane();//add css todo
        ePortMakerPane.setTop(fileToolBarPane);
        primaryScene=new Scene(ePortMakerPane);
        
        //add css todo
	primaryStage.setScene(primaryScene);
	primaryStage.show();
        
        
    }
}
