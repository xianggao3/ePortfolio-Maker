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
    
    FlowPane siteToolbar= new FlowPane();;//site editing toolbar below filetoolbar
    Button addPageButton;
    Button removePageButton;
    
    TabPane tabbedPane=new TabPane();
    Tab tab1;
    Tab tab2;
    
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
    Button addComponentButton;/*
    Button addImageCompButton;
    Button addSlideShowCompButton;
    Button addVideoCompButton;
    Button addListCompButton;*/
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
    
    public ePortfolioAppMakerView(ePortfolioJSONFileManager initFileManager){
        fileManager= initFileManager;
        ePortfolio = new ePortfolioModel(this);
        errorHandler=new ErrorHandler(this);
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
        initTabPane();
        workspace.setCenter(tabbedPane);
        tabbedPane.getStyleClass().add("file_toolbar");
    }
    
    private void initSiteViewerWorkspace(){
        siteView=new WebView();
        scrollPane = new ScrollPane(siteView);
    }

    private void initTabPane(){
        tab1=new Tab();
        tab1.setText("First Title Here");
        currentTab = tabbedPane.getSelectionModel();
        
        
        VBox tabcontent = new VBox();
        Image im = new Image("http://www.vapor-rage.com/wp-content/uploads/2014/05/sample.jpg");
        ImageView iv = new ImageView(im);
        tabcontent.getChildren().addAll(iv);
        
        tab1.setContent(tabcontent);
        tab2=new Tab();
        tab2.setText("Second Title Here");
        tabbedPane.getTabs().addAll(tab1,tab2);
        
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
        controller= new ePortfolioController(this);
        
        editListCompButton.setOnAction(e->{
            listD=new listDialog(primaryStage);
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
        selectPageFontButton.setOnAction(e->{
            pgFontD=new pageFontDialog();
        });
        addComponentButton.setOnAction(e->{
           addD= new addCompDialog(); 
        });
        updateHeaderButton.setOnAction(e->{
           headerD=new headerDialog(); 
        });
        updateFooterButton.setOnAction(e->{
            footerD=new footerDialog();
        });
        editImageCompButton.setOnAction(e->{
            imgD=new imgDialog();
        });
        editVideoCompButton.setOnAction(e->{
           vidD=new videoDialog(); 
        });
        editSlideShowCompButton.setOnAction(e->{
            ssm=new ssDialog(this.primaryStage);
        });
        editTextCompButton.setOnAction(e->{
            textD=new textDialog(primaryStage);
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
            tabbedPane.getTabs().add(nTab= new Tab(" New Tab Title"));
            currentTab.select(nTab);
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
	
        updatePageEditToolbarControls();
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
        removeCompButton.setDisable(!pageSelected);	
        
    }
    /**
     * Uses the slide show data to reload all the components for
     * slide editing.
     * 
     * @param slideShowToLoad SLide show being reloaded.
     */
    //hey xiang note to self, change these so that they match current app.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
    public void reloadSlideShowPane() {
	slidesEditorPane.getChildren().clear();
	reloadTitleControls();
	for (Slide slide : slideShow.getSlides()) {
	    SlideEditView slideEditor = new SlideEditView(this, slide);
	    if (slideShow.isSelectedSlide(slide))
		slideEditor.getStyleClass().add(CSS_CLASS_SELECTED_SLIDE_EDIT_VIEW);
	    else
		slideEditor.getStyleClass().add(CSS_CLASS_SLIDE_EDIT_VIEW);
	    slidesEditorPane.getChildren().add(slideEditor);
	    slideEditor.setOnMousePressed(e -> {
		slideShow.setSelectedSlide(slide);
		this.reloadSlideShowPane();
	    });
	}
	updateSlideshowEditToolbarControls();
    }
    
    private void initTitleControls() {
	PropertiesManager props = PropertiesManager.getPropertiesManager();
	String labelPrompt = props.getProperty(LABEL_SLIDESHOW_TITLE);
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
}
