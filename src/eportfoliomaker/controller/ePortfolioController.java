/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker.controller;

import eportfoliomaker.ErrorHandler;
import eportfoliomaker.ePortfolioJSONFileManager;
import eportfoliomaker.model.Component;
import eportfoliomaker.model.Img;
import eportfoliomaker.model.Page;
import eportfoliomaker.model.Video;
import eportfoliomaker.model.ePortfolioModel;
import eportfoliomaker.slideshow.LanguagePropertyType;
import static eportfoliomaker.slideshow.LanguagePropertyType.LABEL_SAVE_UNSAVED_WORK;
import eportfoliomaker.view.PageEditView;
import eportfoliomaker.view.ePortfolioAppMakerView;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import ssm.view.YesNoCancelDialog;

import eportfoliomaker.model.Component;
import eportfoliomaker.model.Img;
import eportfoliomaker.model.Page;
import eportfoliomaker.model.Video;
import eportfoliomaker.model.ePortfolioModel;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

    
/**
 *
 * @author xgao3
 */
public class ePortfolioController {
    // WE'LL USE THIS TO BUILD PATHS
    public static String SLASH = "/";
    public static String JSON_EXT = ".json";

    // HERE ARE THE DIRECTORIES WE CARE ABOUT
    public static String BASE_DIR = "./base/";
    public static String SITES_DIR = "./sites/";
    public static String CSS_DIR = "css/";
    public static String DATA_DIR = "data/";
    public static String SLIDE_SHOWS_DIR = DATA_DIR + "ePortfolios/";
    public static String ICONS_DIR = "icons/";
    public static String IMG_DIR = "img/";
    public static String JS_DIR = "js/";

    // AND HERE ARE THE FILES WE CARE ABOUT
    public static String INDEX_FILE = "index.html";
    public static String STYLESHEET_FILE = "ePortfolio.css";
    public static String JS_FILE = "ePortfolioMaker.js";
    public static String DATA_FILE = "ePortfolioData.json";
    private ePortfolioAppMakerView ui;
    private boolean saved;
    private ePortfolioJSONFileManager ePortfolioIO;
    
    
    public ePortfolioController(ePortfolioAppMakerView ui,ePortfolioJSONFileManager ePortIO) {
        this.ui = ui;
        saved = true;
        ePortfolioIO=ePortIO;
    }
    public void markAsEdited() {
        saved = false;
        ui.updateFileToolbarControls(saved);
    }
    

    public void handleNewPortRequest() {
        try {
            // WE MAY HAVE TO SAVE CURRENT WORK
            boolean continueToMakeNew = true;
            if (!saved){
                // THE USER CAN OPT OUT HERE WITH A CANCEL
                continueToMakeNew = promptToSave();
            }

            // IF THE USER REALLY WANTS TO MAKE A NEW COURSE
            if (continueToMakeNew) {
                // RESET THE DATA, WHICH SHOULD TRIGGER A RESET OF THE UI
                ePortfolioModel slideShow = ui.getePortfolio();
		slideShow.reset();
                saved = false;

                // REFRESH THE GUI, WHICH WILL ENABLE AND DISABLE
                // THE APPROPRIATE CONTROLS
                ui.updateFileToolbarControls(saved);

		// MAKE SURE THE TITLE CONTROLS ARE ENABLED
		//ui.reloadTitleControls();	not needed i think
                
		ui.reloadPagePane();
                
            }
        } catch (IOException ioe) {
            ErrorHandler eH = ui.getErrorHandler();
            eH.processError(LanguagePropertyType.ERROR_UNEXPECTED);
        }
    }
     public boolean handleSavePortRequest() throws IOException {
         ePortfolioModel slideShowToSave = ui.getePortfolio();
         ePortfolioIO.saveePortfolioPage(slideShowToSave);
         saved = true;
         ui.updateFileToolbarControls(saved);
         return true;
    }
     
     
      
      private boolean promptToSave() throws IOException {
        // PROMPT THE USER TO SAVE UNSAVED WORK
	YesNoCancelDialog yesNoCancelDialog = new YesNoCancelDialog(ui.getPrimaryStage());
	PropertiesManager props = PropertiesManager.getPropertiesManager();
        yesNoCancelDialog.show(props.getProperty(LABEL_SAVE_UNSAVED_WORK));
        
        // AND NOW GET THE USER'S SELECTION
        String selection = yesNoCancelDialog.getSelection();	
	boolean saveWork = selection.equals(YesNoCancelDialog.YES);

        // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
        if (saveWork) {
            ePortfolioModel slideShow = ui.getePortfolio();
            //ePortfolioIO.saveEPortfolio(slideShow);
            saved = true;
        } // IF THE USER SAID CANCEL, THEN WE'LL TELL WHOEVER
        // CALLED THIS THAT THE USER IS NOT INTERESTED ANYMORE
        else if (!true) {
            return false;
        }

        // IF THE USER SAID NO, WE JUST GO ON WITHOUT SAVING
        // BUT FOR BOTH YES AND NO WE DO WHATEVER THE USER
        // HAD IN MIND IN THE FIRST PLACE
        return true;
    }
      private void promptToOpen() throws IOException {
        // AND NOW ASK THE USER FOR THE COURSE TO OPEN
        FileChooser slideShowFileChooser = new FileChooser();
        slideShowFileChooser.setInitialDirectory(new File("./data/ePortfolios/"));
        File selectedFile = slideShowFileChooser.showOpenDialog(ui.getPrimaryStage());

        // ONLY OPEN A NEW FILE IF THE USER SAYS OK
        if (selectedFile != null) {
                
		ePortfolioModel slideShowToLoad = ui.getePortfolio();
                slideShowToLoad.setUi(ui);
                PageEditView pev = new PageEditView(slideShowToLoad);
                slideShowToLoad.setPv(pev);
                ePortfolioIO.loadSlideShow(slideShowToLoad, selectedFile.getAbsolutePath());
                ui.reloadPagePane();
                saved = true;
                ui.updateFileToolbarControls(saved);
            
        }
    }

    public void handleLoadPortRequest() {
try {
            // WE MAY HAVE TO SAVE CURRENT WORK
            boolean continueToOpen = true;
            if (!saved) {
                // THE USER CAN OPT OUT HERE WITH A CANCEL
                continueToOpen = promptToSave();
            }

            // IF THE USER REALLY WANTS TO OPEN A POSE
            if (continueToOpen) {
                // GO AHEAD AND PROCEED MAKING A NEW POSE
                promptToOpen();
            }
        } catch (IOException ioe) {
            ErrorHandler eH = ui.getErrorHandler();
            eH.processError(LanguagePropertyType.ERROR_DATA_FILE_LOADING);
        }    }


    public void exportSite(ePortfolioModel slideShowToExport) throws IOException {	
	// THE SITE HOME PATH
	String homeSitePath = SITES_DIR + slideShowToExport.getStudentName() + SLASH;

	// NOW MAKE THE SITE DIRECTORIES AND COPY OVER THE FILES
	// THAT ONLY NEED TO BE COPIED ONCE
	File siteDir = new File(homeSitePath);

	// FIRST DELETE THE OLD FILES IN CASE THINGS
	// LIKE THE PAGE FORMAT MAY HAVE CHANGED
	if (siteDir.exists())
	    deleteDir(siteDir);

	// NOW MAKE THE HOME DIR
	siteDir.mkdir();

	// MAKE THE CSS, DATA, IMG, AND JS DIRECTORIES
	new File(homeSitePath + CSS_DIR).mkdir();
	new File(homeSitePath + DATA_DIR).mkdir();
	new File(homeSitePath + ICONS_DIR).mkdir();
	new File(homeSitePath + IMG_DIR).mkdir();
	new File(homeSitePath + JS_DIR).mkdir();

	// NOW COPY OVER THE HTML, CSS, ICON, AND JAVASCRIPT FILES
	copyAllFiles(BASE_DIR, homeSitePath);
	copyAllFiles(BASE_DIR + CSS_DIR, homeSitePath + CSS_DIR);
	copyAllFiles(BASE_DIR + ICONS_DIR, homeSitePath + ICONS_DIR);
	copyAllFiles(BASE_DIR + JS_DIR, homeSitePath + JS_DIR);

	// NOW FOR THE TWO THINGS THAT WE HAVE TO COPY OVER EVERY TIME,
	// NAMELY, THE DATA FILE AND THE IMAGES
	// FIRST COPY THE DATA FILE
	Path dataSrcPath = new File(SLIDE_SHOWS_DIR + slideShowToExport.getStudentName() + JSON_EXT).toPath();
	Path dataDestPath = new File(homeSitePath + DATA_DIR + DATA_FILE).toPath();

	Files.copy(dataSrcPath, dataDestPath);

	// AND NOW ALL THE SLIDESHOW IMAGES
	for (Page s : slideShowToExport.getPages()) {
	    Path srcBPath = new File(s.getBannerPath() + SLASH + s.getBannerFileName()).toPath();//banner img out
	    Path destBPath = new File(homeSitePath + IMG_DIR + s.getBannerFileName()).toPath();//banner img in
	    Files.copy(srcBPath, destBPath);
            for(Component c : s.getComponents()){
                
                if(c.getType().equals("img")){
                    Img img = (Img)c;
                    Path srcImgPath = new File(img.getImgPath() + SLASH + img.getImgFileName()).toPath();// img out
                    Path destImgPath = new File(homeSitePath + IMG_DIR + img.getImgFileName()).toPath();// img in
                    Files.copy(srcImgPath, destImgPath);
                    
                }else if(c.getType().equals("video")){
                    Video v = (Video)c;
                    Path srcVidPath = new File(v.getVideoPath() + SLASH + v.getVideoFileName()).toPath();//vid out
                    Path destVidPath = new File(homeSitePath + IMG_DIR + v.getVideoFileName()).toPath();//vid in
                    Files.copy(srcVidPath, destVidPath);
                    
                }
            }
	}
    }
    
    public void deleteDir(File dir) {
	File[] files = dir.listFiles();
	for (File f : files) {
	    if (f.isDirectory()) {
		deleteDir(f);
		f.delete();
	    }
	    else
		f.delete();
	}
	dir.delete();
    }

    public void copyAllFiles(String sourceFile, String destinationDir) throws IOException {
	File srcDir = new File(sourceFile);
	File[] files = srcDir.listFiles();
	for (File f : files) {
	    Path srcPath = f.toPath();
	    Path newPath = new File(destinationDir).toPath();
	    if (!f.isDirectory()) {
		Files.copy(srcPath, newPath.resolve(srcPath.getFileName()));
	    }
	}
    }
}

    

