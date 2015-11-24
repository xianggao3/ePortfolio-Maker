package eportfoliomaker.slideshow;

/**
 * This class provides setup constants for initializing the application
 * that are NOT language dependent.
 * 
 * @author McKilla Gorilla & _____________
 */
public class StartupConstants {
    public static String ENGLISH_LANG = "English";
    public static String FINNISH_LANG = "Finnish";

    // WE'LL LOAD ALL THE UI AND LANGUAGE PROPERTIES FROM FILES,
    // BUT WE'LL NEED THESE VALUES TO START THE PROCESS

    public static String PROPERTY_TYPES_LIST = "property_types.txt";
    public static String UI_PROPERTIES_FILE_NAME_English = "properties_EN.xml";
    public static String UI_PROPERTIES_FILE_NAME_Finnish = "properties_FI.xml";
    public static String PROPERTIES_SCHEMA_FILE_NAME = "properties_schema.xsd";
    public static String PATH_DATA = "./data/";
    public static String PATH_SLIDE_SHOWS = PATH_DATA + "slide_shows/";
    public static String PATH_IMAGES = "./images/";
    public static String PATH_ICONS = PATH_IMAGES + "icons/";
    public static String PATH_SLIDE_SHOW_IMAGES = PATH_IMAGES + "img/";
    public static String PATH_CSS = "/eportfoliomaker/slideshow/";
    public static String STYLE_SHEET_UI = PATH_CSS + "SlideShowMakerStyle.css";

    // HERE ARE THE LANGUAGE INDEPENDENT GUI ICONS
    public static String ICON_WINDOW_LOGO = "SSM_Logo.png";
    public static String ICON_NEW_SLIDE_SHOW = "New.png";
    public static String ICON_LOAD_SLIDE_SHOW = "Load.png";
    public static String ICON_SAVE_SLIDE_SHOW = "Save.png";
    public static String ICON_VIEW_SLIDE_SHOW = "View.png";
    public static String ICON_EXIT = "Exit.png";
    public static String ICON_ADD_SLIDE = "Add.png";
    public static String ICON_REMOVE_SLIDE = "Remove.png";
    public static String ICON_MOVE_UP = "MoveUp.png";
    public static String ICON_MOVE_DOWN = "MoveDown.png";
    public static String ICON_PREVIOUS = "Previous.png";
    public static String ICON_NEXT = "Next.png";

    // UI SETTINGS
    public static String    DEFAULT_SLIDE_IMAGE = "DefaultStartSlide.png";
    public static int	    DEFAULT_THUMBNAIL_WIDTH = 200;
    public static int	    DEFAULT_SLIDE_SHOW_HEIGHT = 500;
    
    // CSS STYLE SHEET CLASSES
    
    // CSS - FOR THE LANGUAGE SELECTION DIALOG
    public static String    CSS_CLASS_LANG_DIALOG_PANE = "lang_dialog_pane";
    public static String    CSS_CLASS_LANG_PROMPT = "lang_prompt";
    public static String    CSS_CLASS_LANG_COMBO_BOX = "lang_combo_box";
    public static String    CSS_CLASS_LANG_OK_BUTTON = "lang_ok_button";

    // CSS - FOR THE TOOLBARS
    public static String    CSS_CLASS_HORIZONTAL_TOOLBAR_PANE = "horizontal_toolbar_pane";
    public static String    CSS_CLASS_VERTICAL_TOOLBAR_PANE = "vertical_toolbar_pane";
    public static String    CSS_CLASS_VERTICAL_TOOLBAR_BUTTON = "vertical_toolbar_button";
    public static String    CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON = "horizontal_toolbar_button";

    // CSS - SLIDESHOW EDITING
    public static String    CSS_CLASS_TITLE_PANE = "title_pane";
    public static String    CSS_CLASS_TITLE_PROMPT = "title_prompt";
    public static String    CSS_CLASS_TITLE_TEXT_FIELD = "title_text_field";
    public static String    CSS_CLASS_CAPTION_PROMPT = "caption_prompt";
    public static String    CSS_CLASS_CAPTION_TEXT_FIELD = "caption_text_field";
    
    // CSS - PANELS
    public static String    CSS_CLASS_WORKSPACE = "workspace";
    public static String    CSS_CLASS_SLIDES_EDITOR_PANE = "slides_editor_pane";
    public static String    CSS_CLASS_SLIDE_EDIT_VIEW = "slide_edit_view";
    public static String    CSS_CLASS_SELECTED_SLIDE_EDIT_VIEW = "selected_slide_edit_view";

    // UI LABELS
    public static String    LABEL_SLIDE_SHOW_TITLE = "slide_show_title";
    public static String    LABEL_LANGUAGE_SELECTION_PROMPT = "Select a Language:";
    public static String    OK_BUTTON_TEXT = "OK";
}
