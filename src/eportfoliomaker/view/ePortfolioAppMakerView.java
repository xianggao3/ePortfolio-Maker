/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker.view;

import eportfoliomaker.ErrorHandler;
import eportfoliomaker.controller.addCompDialog;
import eportfoliomaker.controller.bannerDialog;
import eportfoliomaker.controller.colorDialog;
import eportfoliomaker.controller.ePortfolioController;
import eportfoliomaker.controller.fontDialog;
import eportfoliomaker.controller.footerDialog;
import eportfoliomaker.controller.headerDialog;
import eportfoliomaker.controller.imgDialog;
import eportfoliomaker.controller.layoutDialog;
import eportfoliomaker.controller.listDialog;
import eportfoliomaker.controller.pageFontDialog;
import eportfoliomaker.controller.siteViewer;
import eportfoliomaker.controller.studentNameDialog;
import eportfoliomaker.controller.textDialog;
import eportfoliomaker.controller.titleDialog;
import eportfoliomaker.controller.videoDialog;
import eportfoliomaker.ePortfolioJSONFileManager;
import eportfoliomaker.model.Component;
import eportfoliomaker.model.Img;
import eportfoliomaker.model.ListComp;
import eportfoliomaker.model.Page;
import eportfoliomaker.model.Paragraph;
import eportfoliomaker.model.SlideShowModel;
import eportfoliomaker.model.Video;
import eportfoliomaker.model.ePortfolioModel;
import eportfoliomaker.slideshow.ssDialog;
import eportfoliomaker.slideshow.ssDialog;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
    
    FlowPane siteToolbar= new FlowPane();//site editing toolbar below filetoolbar
    Button addPageButton;
    Button removePageButton;
    
    //Tabs in the middle (Pages)
    TabPane tabbedPane=new TabPane();
    Tab tab1;
    Tab tab2;
    PageEditView pagesEditorPane;
    ScrollPane pagesEditorScrollPane;
    
    VBox pageEditToolbar;//
    VBox rightEditToolbar;
    Button updateHeaderButton;
    Button selectLayoutButton;
    Button selectColorButton;
    Button selectBannerImageButton;
    Button selectComponentButton;
    Button chooseComponentFontButton;
    Button updatePageTitleButton;
    Button updateStudentNameButton;
    Button selectPageFontButton;
    Button updateFooterButton;
    Button addComponentButton;
    Button removeCompButton;
    Button editTextCompButton;
    Button editImageCompButton;
    Button editSlideShowCompButton;
    Button editVideoCompButton;
    Button editListCompButton;
    Button addTextHyperlinkButton;
    Button editTextHyperlinkButton;
    Button NavSiteButton;
    
    ePortfolioModel ePortfolio;
    ePortfolioJSONFileManager fileManager;
    private ErrorHandler errorHandler;
    
    //Dialogs
    private ePortfolioController controller;
    listDialog listD;
    titleDialog titleDialog;
    studentNameDialog nameDi;
    layoutDialog layoutD;
    colorDialog colorD;
    pageFontDialog pgFontD;
    fontDialog fontD;
    addCompDialog addD;
    headerDialog headerD;
    footerDialog footerD;
    imgDialog imgD;
    videoDialog vidD;
    ssDialog ssm;
    textDialog textD;
    bannerDialog bannerD;
    siteViewer sv;
    Tab nTab;
    SingleSelectionModel<Tab> currentTab;   
    Page pg;
    
    public ePortfolioAppMakerView(ePortfolioJSONFileManager initFileManager){
        fileManager= initFileManager;
        ePortfolio = new ePortfolioModel(this);
        errorHandler=new ErrorHandler(this);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public ePortfolioModel getePortfolio() {
        return ePortfolio;
    }

    public void setePortfolio(ePortfolioModel ePortfolio) {
        this.ePortfolio = ePortfolio;
    }

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }
    
    
    
    public void startUI(Stage initPrimaryStage, String windowTitle) {
        initFileToolBar();
        initWorkspace();
        initPageEditToolbar();
        initSiteViewerWorkspace();
        initEventHandlers();
        primaryStage=initPrimaryStage;
        initWindow(windowTitle);
    }    

    private void initFileToolBar() {
        fileToolBarPane= new FlowPane();
        fileToolBarPane.getStyleClass().add("file_toolbar");
        
        newPortButton=initChildButton(fileToolBarPane,"NewOrig.png","",false,"New ePortfolio");
        loadPortButton=initChildButton(fileToolBarPane,"LoadOrig.png","",false,"Load an Existing ePortfolio");
        savePortButton=initChildButton(fileToolBarPane,"SaveOrig.png","",true,"Save the Current ePortfolio");
        saveAsPortButton=initChildButton(fileToolBarPane,"saveAs.png","",true,"Save the ePortfolio As");
        exportButton=initChildButton(fileToolBarPane,"ExportOrig.png","",true,"Export the Current ePortfolio");
        exitButton=initChildButton(fileToolBarPane,"ExitOrig.png","",false,"Exit Application");
    }
    private void initPageEditToolbar(){
        pageEditToolbar=new VBox();
        pageEditToolbar.getStyleClass().add("page_edit_toolbar");
        
        updatePageTitleButton=initChildButton(pageEditToolbar,"title.png","page_edit_toolbar_icons",false,"Change the Title of the Current Page");
        updateStudentNameButton=initChildButton(pageEditToolbar,"name.png","page_edit_toolbar_icons",false,"Change Student Name");
        selectBannerImageButton=initChildButton(pageEditToolbar,"banner.png","page_edit_toolbar_icons",false,"Select Banner Image");
        selectLayoutButton=initChildButton(pageEditToolbar,"layout.png","page_edit_toolbar_icons",false,"Select Layout");
        selectColorButton=initChildButton(pageEditToolbar,"color.png","page_edit_toolbar_icons",false,"Select Color");
        //dont need chooseComponentFontButton=initChildButton(pageEditToolbar,"font.png","page_edit_toolbar_icons",false,"Choose Component Font");
        selectPageFontButton=initChildButton(pageEditToolbar,"font.png","page_edit_toolbar_icons",false,"Change the Page's Font");
        updateHeaderButton=initChildButton(pageEditToolbar,"header.png","page_edit_toolbar_icons",false,"Change Header");
        updateFooterButton=initChildButton(pageEditToolbar,"footer.png","page_edit_toolbar_icons",false,"Change Footer");
        //
        rightEditToolbar=new VBox();
        rightEditToolbar.getStyleClass().add("page_edit_toolbar");
        addComponentButton=initChildButton(rightEditToolbar,"addComp.png","page_edit_toolbar_icons",false,"Add Component");
        selectComponentButton=initChildButton(rightEditToolbar,"component.png","page_edit_toolbar_icons",false,"Select Component");
        removeCompButton=initChildButton(rightEditToolbar,"remove.png","page_edit_toolbar_icons",false,"Remove Component");
        editTextCompButton=initChildButton(rightEditToolbar,"text.png","page_edit_toolbar_icons",false,"Text");
        editListCompButton=initChildButton(rightEditToolbar,"list.png","page_edit_toolbar_icons",false,"List");
        editImageCompButton=initChildButton(rightEditToolbar,"img.png","page_edit_toolbar_icons",false,"Image");
        editSlideShowCompButton=initChildButton(rightEditToolbar,"slideshow.png","page_edit_toolbar_icons",false,"Slideshow");
        editVideoCompButton=initChildButton(rightEditToolbar,"video.png","page_edit_toolbar_icons",false,"Video");
        /*
        editTextCompButton=initChildButton(pageEditToolbar,"color.png","page_edit_toolbar_icons",false,"Select Color");
        editImageCompButton=initChildButton(pageEditToolbar,"color.png","page_edit_toolbar_icons",false,"Select Color");
        editSlideShowCompButton=initChildButton(pageEditToolbar,"color.png","page_edit_toolbar_icons",false,"Select Color");
        editVideoCompButton=initChildButton(pageEditToolbar,"color.png","page_edit_toolbar_icons",false,"Select Color");
        editTextHyperlinkButton=initChildButton(pageEditToolbar,"color.png","page_edit_toolbar_icons",false,"Select Color");
        */
        
    }

    
    private void initWorkspace() {
        workspace=new BorderPane();
        workSpaceModeToolbar = new FlowPane();
        workSpaceModeToolbar.getStyleClass().add("mode_toolbar");
        selectPageEditorWorkspaceButton=initChildButton(workSpaceModeToolbar,"editView.png","",true,"Enter Page Edit Mode");
        selectSiteViewerWorkspaceButton=initChildButton(workSpaceModeToolbar,"siteView.png","",false,"Enter Site View Mode");
        workspace.setBottom(workSpaceModeToolbar);
        
        addPageButton=initChildButton(siteToolbar,"addPage.png","",false,"Add a Page");
        removePageButton=initChildButton(siteToolbar,"deletePage.png","",false,"Delete Current Page");
        siteToolbar.getStyleClass().add("site_toolbar");
        /*Label titleLabel = new Label("Title:");
        Label nameLabel = new Label("Student Name:");
        TextField pageTitle = new TextField();
        TextField studentName = new TextField();
        siteToolbar.getChildren().addAll(titleLabel,pageTitle,nameLabel,studentName);*/
        workspace.setTop(siteToolbar);
        
        //initTabPane();
        
        workspace.setCenter(tabbedPane);
        tabbedPane.getStyleClass().add("file_toolbar");
        currentTab = tabbedPane.getSelectionModel();
    }
    
    public void initSiteViewerWorkspace(){
        siteView=new WebView();
        scrollPane = new ScrollPane(siteView);
    }
/*
    public void initTabPane(){
        
        currentTab = tabbedPane.getSelectionModel();
        
        pagesEditorScrollPane = new ScrollPane(pv);
        pagesEditorScrollPane.setFitToWidth(true);
	pagesEditorScrollPane.setFitToHeight(true);
	//initTitleControls();

        
        tab1.setContent(pagesEditorScrollPane);
        tabbedPane.getTabs().addAll(tab1);
        
    }
  */  
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
        ePortMakerPane.setRight(rightEditToolbar);
        
        ePortMakerPane.setCenter(workspace);
        
        primaryScene=new Scene(ePortMakerPane);
        
        primaryScene.getStylesheets().add("eportfoliomaker/style/Style.css");
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
	return button;   
    }
        
    private void initEventHandlers(){
        controller = new ePortfolioController(this,fileManager);
	newPortButton.setOnAction(e -> {
	    controller.handleNewPortRequest();
	});
	loadPortButton.setOnAction(e -> {
	    controller.handleLoadPortRequest();
	});
	savePortButton.setOnAction(e -> {
	    controller.handleSavePortRequest();
	});
	saveAsPortButton.setOnAction(e -> {
	    controller.handleSaveAsPortRequest();
	});
        exportButton.setOnAction(e->{
            controller.handleExportRequest();
        });
	exitButton.setOnAction(e -> {
	    controller.handleExitRequest();
	});
        
        
        //components
        controller= new ePortfolioController(this,fileManager);
        
        editListCompButton.setOnAction(e->{
            
            Component a = ePortfolio.getSelectedPage().getSelectedComp();
            if(a instanceof ListComp){
            listD=new listDialog(primaryStage,(ListComp)a);
            }
        });
        updatePageTitleButton.setOnAction(e->{
           titleDialog= new titleDialog();      
        });
        updateStudentNameButton.setOnAction(e->{
            nameDi=new studentNameDialog();
        });
        selectLayoutButton.setOnAction(e->{
           layoutD=new layoutDialog(); 
        });
        selectColorButton.setOnAction(e->{
            colorD= new colorDialog();
        });
        removeCompButton.setOnAction(e->{
           ePortfolio.getSelectedPage().getComponents().remove(ePortfolio.getSelectedPage().getSelectedComp());
        });
        selectComponentButton.setOnAction(e->{
            
        });
        selectPageFontButton.setOnAction(e->{
            pgFontD=new pageFontDialog();
        });
        addComponentButton.setOnAction(e->{
           addD= new addCompDialog(ePortfolio); 
        });
        updateHeaderButton.setOnAction(e->{
           headerD=new headerDialog(); 
        });
        updateFooterButton.setOnAction(e->{
            footerD=new footerDialog();
        });
        editImageCompButton.setOnAction(e->{
            
            Component a = ePortfolio.getSelectedPage().getSelectedComp();
            if(a instanceof Img){
            imgD=new imgDialog(primaryStage,(Img)a);
            }
        });
        editVideoCompButton.setOnAction(e->{
            
            Component a = ePortfolio.getSelectedPage().getSelectedComp();
            if(a instanceof Video){
            vidD=new videoDialog(primaryStage,(Video)a);
            }
        });
        editSlideShowCompButton.setOnAction(e->{
            
            Component a = ePortfolio.getSelectedPage().getSelectedComp();
            if(a instanceof SlideShowModel){
            ssm=new ssDialog(primaryStage,(SlideShowModel)a);
            }
        });
        editTextCompButton.setOnAction(e->{
            
            Component a = ePortfolio.getSelectedPage().getSelectedComp();
            if(a instanceof Paragraph){
            textD=new textDialog(primaryStage,(Paragraph)a);
            }
        });
        selectBannerImageButton.setOnAction(e->{
           bannerD=new bannerDialog(); 
        });
        selectSiteViewerWorkspaceButton.setOnAction(e->{
            try {
                sv = new siteViewer();
            } catch (MalformedURLException ex) {
                Logger.getLogger(ePortfolioAppMakerView.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        addPageButton.setOnAction(e->{
            tab1=new Tab("New Tab");
            pagesEditorScrollPane = new ScrollPane(pagesEditorPane);
            pagesEditorScrollPane.setFitToWidth(true);
            pagesEditorScrollPane.setFitToHeight(true);
            //initTitleControls();
            tab1.setContent(pagesEditorScrollPane);
            tabbedPane.getTabs().add(tab1);
            
            Page pg = new Page(this);
            PageEditView pv = new PageEditView(ePortfolio);
            pv.setPg(pg);
            pg.setPageEditView(pv);
            ePortfolio.getPages().add(pg);
            ePortfolio.setSelectedPage(pg);
            reloadPagePane(pv);
        });

        removePageButton.setOnAction(e->{
           tabbedPane.getTabs().remove(currentTab.getSelectedItem());
        });
    }
    
    public void updateFileToolbarControls(boolean saved) {
	// FIRST MAKE SURE THE WORKSPACE IS THERE
	ePortMakerPane.setCenter(workspace);
	
	// NEXT ENABLE/DISABLE BUTTONS AS NEEDED IN THE FILE TOOLBAR
	savePortButton.setDisable(saved);
	selectSiteViewerWorkspaceButton.setDisable(false);
	
        //updatePageEditToolbarControls();
    }
    
    public void updatePageEditToolbarControls(){
        boolean pageSelected=false;
        editTextCompButton.setDisable(true);
        editListCompButton.setDisable(true);
        editImageCompButton.setDisable(true);
        editSlideShowCompButton.setDisable(true);
        editVideoCompButton.setDisable(true);
	if(ePortfolio.isPageSelected()==true){
            if(ePortfolio.getSelectedPage().isCompSelected()==true){
                pageSelected =true;
                if(ePortfolio.getSelectedPage().getSelectedComp().getType()=="p"){
                    editTextCompButton.setDisable(false);
                }else if(ePortfolio.getSelectedPage().getSelectedComp().getType()=="list"){
                    editListCompButton.setDisable(false);
                }else if(ePortfolio.getSelectedPage().getSelectedComp().getType()=="img"){
                    editImageCompButton.setDisable(false);
                }else if(ePortfolio.getSelectedPage().getSelectedComp().getType()=="slideshow"){
                    editSlideShowCompButton.setDisable(false);
                }else if(ePortfolio.getSelectedPage().getSelectedComp().getType()=="video"){
                    editVideoCompButton.setDisable(false);
                }
            }
        }
	removeCompButton.setDisable(!pageSelected);
        updatePageTitleButton.setDisable(!pageSelected);	
        updateStudentNameButton.setDisable(!pageSelected);	
        selectBannerImageButton.setDisable(!pageSelected);	
        selectLayoutButton.setDisable(!pageSelected);	
        selectColorButton.setDisable(!pageSelected);	
        selectPageFontButton.setDisable(!pageSelected);	
        updateHeaderButton.setDisable(!pageSelected);	
        updateFooterButton.setDisable(!pageSelected);	
        addComponentButton.setDisable(!pageSelected);	
        selectComponentButton.setDisable(!pageSelected);	
    }
    
        
    public void reloadPagePane(PageEditView pv) {
        currentTab.getSelectedItem().setContent(null);
        for(Component comp:ePortfolio.getSelectedPage().getComponents()){
            ComponentEditView compEditor = new ComponentEditView(comp);
            //initTitleControls();
            pagesEditorScrollPane = new ScrollPane(pv);
            pv.getChildren().add(compEditor);
            
            pagesEditorScrollPane.setFitToWidth(true);
            pagesEditorScrollPane.setFitToHeight(true);
            currentTab.getSelectedItem().setContent(pagesEditorScrollPane);
        }
    }
    /*
    private void initTitleControls() {
	String labelPrompt = "New Title";
	titlePane = new FlowPane();
	titleLabel = new Label(labelPrompt);
	titleTextField = new TextField();
	
	titlePane.getChildren().add(titleLabel);
	titlePane.getChildren().add(titleTextField);
	
	String titlePrompt = props.getProperty(LanguagePropertyType.LABEL_SLIDESHOW_TITLE);
	titleTextField.setText(titlePrompt);
	
	titleTextField.textProperty().addListener(e -> {
	    slideShow.setTitle(titleTextField.getText());
	    updateFileToolbarControls(false);
	});
	
	titlePane.getStyleClass().add(CSS_CLASS_TITLE_PANE);
	titleLabel.getStyleClass().add(CSS_CLASS_TITLE_PROMPT);
	titleTextField.getStyleClass().add(CSS_CLASS_TITLE_TEXT_FIELD);
    }
    
    public void reloadTitleControls() {
	if (slidesEditorPane.getChildren().size() == 0)
	    slidesEditorPane.getChildren().add(titlePane);
	titleTextField.setText(slideShow.getTitle());
    }
    */
}
