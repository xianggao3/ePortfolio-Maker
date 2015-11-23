package eportfoliomaker.slideshow;

import java.io.File;
import java.net.URL;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import xml_utilities.InvalidXMLFileFormatException;
import properties_manager.PropertiesManager;
import static ssm.LanguagePropertyType.TITLE_WINDOW;
import static ssm.StartupConstants.FINNISH_LANG;
import static ssm.StartupConstants.ICON_WINDOW_LOGO;
import static ssm.StartupConstants.PATH_DATA;
import static ssm.StartupConstants.PATH_IMAGES;
import static ssm.StartupConstants.PROPERTIES_SCHEMA_FILE_NAME;
import ssm.error.ErrorHandler;
import ssm.file.SlideShowFileManager;
import ssm.file.SlideShowSiteExporter;
import ssm.view.LanguageSelectionDialog;
import ssm.view.SlideShowMakerView;

/**
 * SlideShowMaker is a program for making custom image slideshows. It will allow
 * the user to name their slideshow, select images to use, select captions for
 * the images, and the order of appearance for slides.
 *
 * @author McKilla Gorilla & _____________
 */
public class SlideShowMaker extends Application {
    // THIS WILL PERFORM SLIDESHOW READING AND WRITING
    SlideShowFileManager fileManager = new SlideShowFileManager();
    
    // THIS WILL EXPORT THE WEB SITES
    SlideShowSiteExporter siteExporter = new SlideShowSiteExporter();

    // THIS HAS THE FULL USER INTERFACE AND ONCE IN EVENT
    // HANDLING MODE, BASICALLY IT BECOMES THE FOCAL
    // POINT, RUNNING THE UI AND EVERYTHING ELSE
    SlideShowMakerView ui = new SlideShowMakerView(fileManager, siteExporter);

    @Override
    public void start(Stage primaryStage) throws Exception {
	

	    // NOW START THE UI IN EVENT HANDLING MODE
	    ui.startUI(primaryStage);
	
	}
    }
    

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
