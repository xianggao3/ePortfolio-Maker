package eportfoliomaker.slideshow;

import eportfoliomaker.controller.SlideshowMakerView;
import eportfoliomaker.model.SlideShowModelComponent;
import static eportfoliomaker.slideshow.LanguagePropertyType.DEFAULT_IMAGE_CAPTION;
import static eportfoliomaker.slideshow.StartupConstants.DEFAULT_SLIDE_IMAGE;
import static eportfoliomaker.slideshow.StartupConstants.PATH_SLIDE_SHOW_IMAGES;
import properties_manager.PropertiesManager;
/**
 * This controller provides responses for the slideshow edit toolbar,
 * which allows the user to add, remove, and reorder slides.
 * 
 * @author McKilla Gorilla & _____________
 */
public class SlideShowEditController {
    // APP UI
    private SlideshowMakerView ui;
    
    /**
     * This constructor keeps the UI for later.
     */
    public SlideShowEditController(SlideshowMakerView initUI) {
	ui = initUI;
    }
    
    /**
     * Provides a response for when the user wishes to add a new
     * slide to the slide show.
     */
    public void processAddSlideRequest() {
	SlideShowModelComponent slideShow = ui.getSlideShow();
	PropertiesManager props = PropertiesManager.getPropertiesManager();
	slideShow.addSlide(DEFAULT_SLIDE_IMAGE, PATH_SLIDE_SHOW_IMAGES, props.getProperty(DEFAULT_IMAGE_CAPTION));
	ui.updateFileToolbarControls(false);
    }

    /**
     * Provides a response for when the user has selected a slide
     * and wishes to remove it from the slide show.
     */
    public void processRemoveSlideRequest() {
	SlideShowModelComponent slideShow = ui.getSlideShow();
	slideShow.removeSelectedSlide();
	ui.reloadSlideShowPane();
	ui.updateFileToolbarControls(false);
    }

    /**
     * Provides a response for when the user has selected a slide
     * and wishes to move it up in the slide show.
     */
    public void processMoveSlideUpRequest() {
	SlideShowModelComponent slideShow = ui.getSlideShow();
	slideShow.moveSelectedSlideUp();	
	ui.updateFileToolbarControls(false);	
    }

    /**
     * Provides a response for when the user has selected a slide
     * and wises to move it down in the slide show.
     */
    public void processMoveSlideDownRequest() {
	SlideShowModelComponent slideShow = ui.getSlideShow();
	slideShow.moveSelectedSlideDown();	
	ui.updateFileToolbarControls(false);
    }
}
