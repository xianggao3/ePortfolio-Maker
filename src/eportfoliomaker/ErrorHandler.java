/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker;

import eportfoliomaker.slideshow.LanguagePropertyType;
import eportfoliomaker.view.ePortfolioAppMakerView;
import javafx.scene.control.Alert;
import properties_manager.PropertiesManager;

/**
 *
 * @author xgao3
 */
public class ErrorHandler {
    private ePortfolioAppMakerView ui;

    public ErrorHandler(ePortfolioAppMakerView aThis) {
        ui=aThis;
    }
    public void processError(LanguagePropertyType errorType)
    {
        // GET THE FEEDBACK TEXT
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String errorFeedbackText = props.getProperty(errorType);
             
        // POP OPEN A DIALOG TO DISPLAY TO THE USER
        Alert alertDialog = new Alert(Alert.AlertType.WARNING, errorFeedbackText);
	alertDialog.showAndWait();
    } 
}
