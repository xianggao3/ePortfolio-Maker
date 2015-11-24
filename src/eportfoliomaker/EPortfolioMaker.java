/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker;

import static eportfoliomaker.slideshow.StartupConstants.DEFAULT_SLIDE_IMAGE;
import static eportfoliomaker.slideshow.StartupConstants.PATH_SLIDE_SHOW_IMAGES;
import eportfoliomaker.view.ePortfolioAppMakerView;
import java.io.File;
import java.net.URL;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author xgao3
 */
public class EPortfolioMaker extends Application{

    ePortfolioJSONFileManager fileManager=new ePortfolioJSONFileManager();
    ePortfolioAppMakerView ui = new ePortfolioAppMakerView(fileManager);
    
    /**
     * @param args the command line arguments
     */

    @Override
    public void start(Stage primaryStage) throws Exception {
        String windowIconPath="./images/icons/Logo.png";
        File file=new File(windowIconPath);
        //setting icon
        URL fileURL = file.toURI().toURL();
	Image windowIcon = new Image(fileURL.toExternalForm());
	primaryStage.getIcons().add(windowIcon);
        ui.startUI(primaryStage, "ePortfolio Maker");
    }
    
    public static void main(String[] args) {
        launch(args);
        
        System.out.println(PATH_SLIDE_SHOW_IMAGES);
        System.out.println(DEFAULT_SLIDE_IMAGE);
    }
}
