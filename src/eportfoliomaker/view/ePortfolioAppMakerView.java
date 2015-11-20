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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebView;
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
    
    BorderPane workspace;
    
    WebView siteView;
    ScrollPane scrollPane;
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
    
    FlowPane siteToolbar= new FlowPane();;//site editing toolbar below filetoolbar
    Button addPageButton;
    Button removePageButton;
    
    TabPane tabbedPane=new TabPane();
    Tab tab1;
    
    VBox pageEditToolbar;//
    Button selectLayoutButton;
    Button selectColorButton;
    Button selectBannerImageButton;
    Button selectComponentButton;
    Button chooseComponentFontButton;
    Button updatePageTitleButton;
    Button updateStudentNameButton;
    Button updateFooterButton;
    Button addTextCompButton;
    Button addImageCompButton;
    Button addSlideShowCompButton;
    Button addVideoCompButton;
    Button removeCompButton;
    Button editTextCompButton;
    Button editImageCompButton;
    Button editSlideShowCompButton;
    Button editVideoCompButton;
    Button addTextHyperlinkButton;
    Button editTextHyperlinkButton;
    Button NavSiteButton;
    
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
        initPageEditToolbar();
        //@todo initEventHandlers();
        primaryStage=initPrimaryStage;
        initWindow(windowTitle);
    }    

    private void initFileToolBar() {
        fileToolBarPane= new FlowPane();
        //get css
        
        newPortButton=initChildButton(fileToolBarPane,"NewOrig.png","",false,"New ePortfolio");
        loadPortButton=initChildButton(fileToolBarPane,"LoadOrig.png","",false,"Load an Existing ePortfolio");
        savePortButton=initChildButton(fileToolBarPane,"SaveOrig.png","",true,"Save the Current ePortfolio");
        saveAsPortButton=initChildButton(fileToolBarPane,"saveAs.png","",true,"Save the ePortfolio As");
        exportButton=initChildButton(fileToolBarPane,"ExportOrig.png","",true,"Export the Current ePortfolio");
    }
    private void initPageEditToolbar(){
        pageEditToolbar=new VBox();
        
        selectLayoutButton=initChildButton(pageEditToolbar,"layout.png","",false,"Select Layout");
        selectColorButton=initChildButton(pageEditToolbar,"color.png","",false,"Select Color");
        selectBannerImageButton=initChildButton(pageEditToolbar,"color.png","",false,"Select Banner Image");
        selectComponentButton=initChildButton(pageEditToolbar,"color.png","",false,"Select Component");
        chooseComponentFontButton=initChildButton(pageEditToolbar,"color.png","",false,"Choose Component");
        updatePageTitleButton=initChildButton(pageEditToolbar,"color.png","",false,"Select Color");
        updateStudentNameButton=initChildButton(pageEditToolbar,"color.png","",false,"Select Color");
        updateFooterButton=initChildButton(pageEditToolbar,"color.png","",false,"Select Color");
        addTextCompButton=initChildButton(pageEditToolbar,"color.png","",false,"Select Color");
        addImageCompButton=initChildButton(pageEditToolbar,"color.png","",false,"Select Color");
        addSlideShowCompButton=initChildButton(pageEditToolbar,"color.png","",false,"Select Color");
        addVideoCompButton=initChildButton(pageEditToolbar,"color.png","",false,"Select Color");
        removeCompButton=initChildButton(pageEditToolbar,"color.png","",false,"Select Color");
        editTextCompButton=initChildButton(pageEditToolbar,"color.png","",false,"Select Color");
        editImageCompButton=initChildButton(pageEditToolbar,"color.png","",false,"Select Color");
        editSlideShowCompButton=initChildButton(pageEditToolbar,"color.png","",false,"Select Color");
        editVideoCompButton=initChildButton(pageEditToolbar,"color.png","",false,"Select Color");
        addTextHyperlinkButton=initChildButton(pageEditToolbar,"color.png","",false,"Select Color");
        editTextHyperlinkButton=initChildButton(pageEditToolbar,"color.png","",false,"Select Color");
        
        pageEditToolbar.getStyleClass().add("page_edit_toolbar");
    }

    
    private void initWorkspace() {
        workspace=new BorderPane();
        workSpaceModeToolbar = new FlowPane();
        selectPageEditorWorkspaceButton=initChildButton(workSpaceModeToolbar,"editView.png","",true,"Enter Page Edit Mode");
        selectSiteViewerWorkspaceButton=initChildButton(workSpaceModeToolbar,"siteView.png","",false,"Enter Site View Mode");
        workspace.setBottom(workSpaceModeToolbar);
        
        addPageButton=initChildButton(siteToolbar,"addPage.png","",false,"Add a Page");
        removePageButton=initChildButton(siteToolbar,"deletePage.png","",false,"Delete Current Page");
        workspace.setTop(siteToolbar);
        initTabPane();
        workspace.setCenter(tabbedPane);
    }
    
    private void initSiteViewerWorkspace(){
        siteView=new WebView();
        scrollPane = new ScrollPane(siteView);
        workspace.setCenter(scrollPane);
    }

    private void initTabPane(){
        tab1=new Tab();
        tab1.setText("Tab 1");
        tab1.setContent(new Rectangle(200,200, Color.LIGHTSTEELBLUE));
        tabbedPane.getTabs().add(tab1);
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
        
        ePortMakerPane.setLeft(pageEditToolbar);

        ePortMakerPane.setCenter(workspace);
        
        primaryScene=new Scene(ePortMakerPane);
        
        //add css todo
	primaryStage.setScene(primaryScene);
	primaryStage.show();
    }

    private Button initChildButton(Pane toolbar,String iconFileName,String cssClass,boolean disabled,String toolTip) {
        
	String imagePath = "file:" + "./images/icons/" + iconFileName;
	Image buttonImage = new Image(imagePath);
	Button button = new Button();
	button.getStyleClass().add(cssClass);
	button.setDisable(disabled);
	button.setGraphic(new ImageView(buttonImage));
	Tooltip buttonTooltip = new Tooltip(toolTip);
	button.setTooltip(buttonTooltip);
	toolbar.getChildren().add(button);
	return button;    }
    
    private void initEventHandlers(){
        controller= new ePortfolioController();
    }
}
