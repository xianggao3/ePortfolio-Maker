/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker.controller;

import eportfoliomaker.model.Slide;
import eportfoliomaker.model.SlideShowModelComponent;
import eportfoliomaker.slideshow.LanguagePropertyType;
import eportfoliomaker.slideshow.SlideEditView;
import eportfoliomaker.slideshow.SlideShowEditController;
import static eportfoliomaker.slideshow.LanguagePropertyType.LABEL_SLIDESHOW_TITLE;
import static eportfoliomaker.slideshow.LanguagePropertyType.TOOLTIP_ADD_SLIDE;
import static eportfoliomaker.slideshow.LanguagePropertyType.TOOLTIP_MOVE_DOWN;
import static eportfoliomaker.slideshow.LanguagePropertyType.TOOLTIP_MOVE_UP;
import static eportfoliomaker.slideshow.LanguagePropertyType.TOOLTIP_REMOVE_SLIDE;
import static eportfoliomaker.slideshow.StartupConstants.CSS_CLASS_SELECTED_SLIDE_EDIT_VIEW;
import static eportfoliomaker.slideshow.StartupConstants.CSS_CLASS_SLIDES_EDITOR_PANE;
import static eportfoliomaker.slideshow.StartupConstants.CSS_CLASS_SLIDE_EDIT_VIEW;
import static eportfoliomaker.slideshow.StartupConstants.CSS_CLASS_TITLE_PANE;
import static eportfoliomaker.slideshow.StartupConstants.CSS_CLASS_TITLE_PROMPT;
import static eportfoliomaker.slideshow.StartupConstants.CSS_CLASS_TITLE_TEXT_FIELD;
import static eportfoliomaker.slideshow.StartupConstants.CSS_CLASS_VERTICAL_TOOLBAR_BUTTON;
import static eportfoliomaker.slideshow.StartupConstants.CSS_CLASS_VERTICAL_TOOLBAR_PANE;
import static eportfoliomaker.slideshow.StartupConstants.CSS_CLASS_WORKSPACE;
import static eportfoliomaker.slideshow.StartupConstants.ICON_ADD_SLIDE;
import static eportfoliomaker.slideshow.StartupConstants.ICON_MOVE_DOWN;
import static eportfoliomaker.slideshow.StartupConstants.ICON_MOVE_UP;
import static eportfoliomaker.slideshow.StartupConstants.ICON_REMOVE_SLIDE;
import static eportfoliomaker.slideshow.StartupConstants.PATH_ICONS;
import static eportfoliomaker.slideshow.StartupConstants.STYLE_SHEET_UI;
import eportfoliomaker.view.ePortfolioAppMakerView;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author xgao3
 */
public class SlideshowMakerView extends Stage{

    // THIS PANE ORGANIZES THE BIG PICTURE CONTAINERS FOR THE
    // APPLICATION GUI
    Scene primaryScene;
    BorderPane ssmPane;
    
    // WORKSPACE
    BorderPane workspace;

    // THIS WILL GO IN THE LEFT SIDE OF THE SCREEN
    FlowPane slideEditToolbar;
    Button addSlideButton;
    Button removeSlideButton;
    Button moveSlideUpButton;
    Button moveSlideDownButton;
    
    // FOR THE SLIDE SHOW TITLE
    FlowPane titlePane;
    Label titleLabel;
    TextField titleTextField;
    
    // AND THIS WILL GO IN THE CENTER
    ScrollPane slidesEditorScrollPane;
    VBox slidesEditorPane;

    // THIS IS THE SLIDE SHOW WE'RE WORKING WITH
    SlideShowModelComponent slideShow;
    ePortfolioAppMakerView ui;
    private SlideShowEditController editController;
    
    public SlideshowMakerView(ePortfolioAppMakerView ui,SlideShowModelComponent ss){//scomponent
        initOwner(ui.getPrimaryStage());
        this.ui=ui;
        slideShow=ss;
        
        startUI(this,"Slideshow Maker");
        FlowPane s = new FlowPane();
        
        ssmPane = new BorderPane();
	ssmPane.setCenter(workspace);
        s.getChildren().addAll(ssmPane);
	primaryScene.getStylesheets().add(STYLE_SHEET_UI);
        setScene(primaryScene);
        showAndWait();
    }
    
    
    public SlideShowModelComponent getSlideShow() {
	return slideShow;
    }

    public Stage getWindow() {
	return this;
    
    }
    
    
    
    public void startUI(Stage initPrimaryStage, String windowTitle) {

        // INIT THE CENTER WORKSPACE CONTROLS BUT DON'T ADD THEM
	// TO THE WINDOW YET
	initWorkspace();

	// NOW SETUP THE EVENT HANDLERS
	initEventHandlers();

	// AND FINALLY START UP THE WINDOW (WITHOUT THE WORKSPACE)
	// KEEP THE WINDOW FOR LATER
    }

    // UI SETUP HELPER METHODS
    private void initWorkspace() {
	// FIRST THE WORKSPACE ITSELF, WHICH WILL CONTAIN TWO REGIONS
	workspace = new BorderPane();
	// THIS WILL GO IN THE LEFT SIDE OF THE SCREEN
	slideEditToolbar = new FlowPane();
	addSlideButton = this.initChildButton(slideEditToolbar,		ICON_ADD_SLIDE,	    TOOLTIP_ADD_SLIDE,	    CSS_CLASS_VERTICAL_TOOLBAR_BUTTON,  false);
	removeSlideButton = this.initChildButton(slideEditToolbar,	"Del.png",  TOOLTIP_REMOVE_SLIDE,   CSS_CLASS_VERTICAL_TOOLBAR_BUTTON,  true);
	moveSlideUpButton = this.initChildButton(slideEditToolbar,	ICON_MOVE_UP,	    TOOLTIP_MOVE_UP,	    CSS_CLASS_VERTICAL_TOOLBAR_BUTTON,  true);
	moveSlideDownButton = this.initChildButton(slideEditToolbar,	ICON_MOVE_DOWN,	    TOOLTIP_MOVE_DOWN,	    CSS_CLASS_VERTICAL_TOOLBAR_BUTTON,  true);
	
	
	// AND THIS WILL GO IN THE CENTER
	slidesEditorPane = new VBox();
	slidesEditorScrollPane = new ScrollPane(slidesEditorPane);
	slidesEditorScrollPane.setFitToWidth(true);
	slidesEditorScrollPane.setFitToHeight(true);
	
	// NOW PUT THESE TWO IN THE WORKSPACE
	workspace.setLeft(slideEditToolbar);
	workspace.setCenter(slidesEditorScrollPane);

	// SETUP ALL THE STYLE CLASSES
	workspace.getStyleClass().add(CSS_CLASS_WORKSPACE);
        System.out.println("op");
	slideEditToolbar.getStyleClass().add(CSS_CLASS_VERTICAL_TOOLBAR_PANE);
	slidesEditorPane.getStyleClass().add(CSS_CLASS_SLIDES_EDITOR_PANE);
	slidesEditorScrollPane.getStyleClass().add(CSS_CLASS_SLIDES_EDITOR_PANE);
    }

    private void initEventHandlers() {
	
	
	// THEN THE SLIDE SHOW EDIT CONTROLS
	editController = new SlideShowEditController(this);
	addSlideButton.setOnAction(e -> {
	    editController.processAddSlideRequest();
	});
	removeSlideButton.setOnAction(e -> {
	    editController.processRemoveSlideRequest();
	});
	moveSlideUpButton.setOnAction(e -> {
	    editController.processMoveSlideUpRequest();
	});
	moveSlideDownButton.setOnAction(e -> {
	    editController.processMoveSlideDownRequest();
	});
    }

    /**
     * This function initializes all the buttons in the toolbar at the top of
     * the application window. These are related to file management.
     */
    

    private void initWindow(String windowTitle) {
	// SET THE WINDOW TITLE
	setTitle(windowTitle);
       
	// GET THE SIZE OF THE SCREEN
	Screen screen = Screen.getPrimary();
	Rectangle2D bounds = screen.getVisualBounds();

	// AND USE IT TO SIZE THE WINDOW
	setX(bounds.getMinX()/1.5);
	setY(bounds.getMinY()/2);
	setWidth(bounds.getWidth()/1.5);
	setHeight(bounds.getHeight()/2);
        
        // SETUP THE UI, NOTE WE'LL ADD THE WORKSPACE LATER
	ssmPane = new BorderPane();
	ssmPane.getStyleClass().add(CSS_CLASS_WORKSPACE);
	primaryScene = new Scene(ssmPane);
	
        // NOW TIE THE SCENE TO THE WINDOW, SELECT THE STYLESHEET
	// WE'LL USE TO STYLIZE OUR GUI CONTROLS, AND OPEN THE WINDOW
    }
    public Button initChildButton(
	    Pane toolbar, 
	    String iconFileName, 
	    LanguagePropertyType tooltip, 
	    String cssClass,
	    boolean disabled) {
	PropertiesManager props = PropertiesManager.getPropertiesManager();
	String imagePath = "file:" + PATH_ICONS + iconFileName;
	Image buttonImage = new Image(imagePath);
	Button button = new Button();
	button.getStyleClass().add(cssClass);
	button.setDisable(disabled);
	button.setGraphic(new ImageView(buttonImage));
	Tooltip buttonTooltip = new Tooltip(props.getProperty(tooltip.toString()));
	button.setTooltip(buttonTooltip);
	toolbar.getChildren().add(button);
	return button;
    }
    
    /**
     * Updates the enabled/disabled status of all toolbar
     * buttons.
     * 
     * @param saved 
     */
    public void updateFileToolbarControls(boolean saved) {
	// FIRST MAKE SURE THE WORKSPACE IS THERE
	ssmPane.setCenter(workspace);
	updateSlideshowEditToolbarControls();
    }
    
    public void updateSlideshowEditToolbarControls() {
	// AND THE SLIDESHOW EDIT TOOLBAR
	addSlideButton.setDisable(false);
	boolean slideSelected = slideShow.isSlideSelected();
	removeSlideButton.setDisable(!slideSelected);
	moveSlideUpButton.setDisable(!slideSelected);
	moveSlideDownButton.setDisable(!slideSelected);	
    }

    /**
     * Uses the slide show data to reload all the components for
     * slide editing.
     * 
     * @param slideShowToLoad SLide show being reloaded.
     */
    public void reloadSlideShowPane() {
	slidesEditorPane.getChildren().clear();
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
    
    
    
}
