package eportfoliomaker.slideshow;

import eportfoliomaker.ErrorHandler;
import static eportfoliomaker.slideshow.LanguagePropertyType.TITLE_WINDOW;
import static eportfoliomaker.slideshow.StartupConstants.ICON_WINDOW_LOGO;
import static eportfoliomaker.slideshow.StartupConstants.PATH_DATA;
import static eportfoliomaker.slideshow.StartupConstants.PATH_IMAGES;
import static eportfoliomaker.slideshow.StartupConstants.PROPERTIES_SCHEMA_FILE_NAME;
import java.io.File;
import java.net.URL;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 * SlideShowMaker is a program for making custom image slideshows. It will allow
 * the user to name their slideshow, select images to use, select captions for
 * the images, and the order of appearance for slides.
 *
 * @author McKilla Gorilla & _____________
 */
public class SlideShowMaker extends Application {
    // THIS HAS THE FULL USER INTERFACE AND ONCE IN EVENT
    // HANDLING MODE, BASICALLY IT BECOMES THE FOCAL
    // POINT, RUNNING THE UI AND EVERYTHING ELSE
    SlideShowMakerView ui = new SlideShowMakerView();

    @Override
    public void start(Stage primaryStage) throws Exception {
	String imagePath = PATH_IMAGES + ICON_WINDOW_LOGO;
	File file = new File(imagePath);
	
	// GET AND SET THE IMAGE
	URL fileURL = file.toURI().toURL();
	Image windowIcon = new Image(fileURL.toExternalForm());
	primaryStage.getIcons().add(windowIcon);
	
        // LOAD APP SETTINGS INTO THE GUI AND START IT UP
        
	    ui.startUI(primaryStage,"Slideshow Maker");
    }
    
    /**
     * Loads this application's properties file, which has a number of settings
     * for initializing the user interface.
     * 
     * @return true if the properties file was loaded successfully, false otherwise.
     */

    /**
     * This is where the application starts execution. We'll load the
     * application properties and then use them to build our user interface and
     * start the window in event handling mode. Once in that mode, all code
     * execution will happen in response to user requests.
     *
     * @param args This application does not use any command line arguments.
     */
    public static void main(String[] args) {
	launch(args);
    }
}
