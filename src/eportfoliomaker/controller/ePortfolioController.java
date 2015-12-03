/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker.controller;

import eportfoliomaker.ErrorHandler;
import eportfoliomaker.ePortfolioJSONFileManager;
import eportfoliomaker.model.ePortfolioModel;
import eportfoliomaker.slideshow.LanguagePropertyType;
import static eportfoliomaker.slideshow.LanguagePropertyType.LABEL_SAVE_UNSAVED_WORK;
import eportfoliomaker.view.ePortfolioAppMakerView;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import ssm.view.YesNoCancelDialog;

/**
 *
 * @author xgao3
 */
public class ePortfolioController {
    private ePortfolioAppMakerView ui;
    private boolean saved;
    private ePortfolioJSONFileManager ePortfolioIO;
    
    
    public ePortfolioController(ePortfolioAppMakerView ui,ePortfolioJSONFileManager ePortIO) {
        this.ui = ui;
        saved = true;
        ePortfolioIO=ePortIO;
    }
    
    

    public void handleNewPortRequest() {
      try {
            // WE MAY HAVE TO SAVE CURRENT WORK
            boolean continueToMakeNew = true;
            if (!saved) {
                // THE USER CAN OPT OUT HERE WITH A CANCEL
                continueToMakeNew = promptToSave();
            }

            // IF THE USER REALLY WANTS TO MAKE A NEW COURSE
            if (continueToMakeNew) {
                // RESET THE DATA, WHICH SHOULD TRIGGER A RESET OF THE UI
                ePortfolioModel ePortfolio = ui.getePortfolio();
		ePortfolio.reset();
                saved = false;
                
                // REFRESH THE GUI, WHICH WILL ENABLE AND DISABLE
                // THE APPROPRIATE CONTROLS
                ui.updateFileToolbarControls(saved);

		// MAKE SURE THE TITLE CONTROLS ARE ENABLED
		//ui.reloadTitleControls();	
		ui.reloadPagePane(ePortfolio.getPv());
            }
        } catch (IOException ioe) {
            ErrorHandler eH = ui.getErrorHandler();
            eH.processError(LanguagePropertyType.ERROR_UNEXPECTED);
        }
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
    
}
