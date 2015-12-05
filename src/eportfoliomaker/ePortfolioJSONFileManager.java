/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker;

import eportfoliomaker.model.Component;
import eportfoliomaker.model.Img;
import eportfoliomaker.model.ListComp;
import eportfoliomaker.model.Page;
import eportfoliomaker.model.Paragraph;
import eportfoliomaker.model.SlideShowModelComponent;
import eportfoliomaker.model.Video;
import eportfoliomaker.model.ePortfolioModel;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
/**
 *
 * @author xgao3
 */
public class ePortfolioJSONFileManager {
    // JSON FILE READING AND WRITING CONSTANTS
    public static String JSON_TITLE = "title";
    public static String JSON_SLIDES = "slides";
    public static String JSON_IMAGE_FILE_NAME = "image_file_name";
    public static String JSON_IMAGE_PATH = "image_path";
    public static String JSON_CAPTION = "caption";
    public static String JSON_EXT = ".json";
    public static String SLASH = "/";
    /**
     * This method saves all the data associated with a slide show to a JSON
     * file.
     *
     * @param ePortfolioToSave The course whose data we are saving.
     *
     * @throws IOException Thrown when there are issues writing to the JSON
     * file.
     */
    public void saveePortfolio(ePortfolioModel ePToSave)throws IOException{//enter 1 model, will do gen 1 html per page
        for(Page p:ePToSave.getPages()){
            saveePortfolioPage(p);
        }
    }
    
    public void saveePortfolioPage(Page pToSave) throws IOException {
	StringWriter sw = new StringWriter();
        JsonObject singlePgJSON = makePageJsonObject(pToSave);

	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);

	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(singlePgJSON);
	jsonWriter.close();

	// INIT THE WRITER
	String slideShowTitle = "" + pToSave.getTitle();
	String jsonFilePath = "./data/ePortfolios/" + SLASH + slideShowTitle + JSON_EXT;//if this doesnt work try file: and periods and \ or \\ or //
	OutputStream os = new FileOutputStream(jsonFilePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(singlePgJSON);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(jsonFilePath);
	pw.write(prettyPrinted);
	pw.close();
	System.out.println(prettyPrinted);
    }

    /**
     * This method loads the contents of a JSON file representing a slide show
     * into a SlideSShowModel objecct.
     *
     * @param slideShowToLoad The slide show to load
     * @param jsonFilePath The JSON file to load.
     * @throws IOException
     */
    public void loadSlideShow(ePortfolioModel slideShowToLoad, String jsonFilePath) throws IOException {
	// LOAD THE JSON FILE WITH ALL THE DATA
	JsonObject json = loadJSONFile(jsonFilePath);

	// NOW LOAD THE COURSE
	slideShowToLoad.reset();
	slideShowToLoad.setTitle(json.getString(JSON_TITLE));
	JsonArray jsonSlidesArray = json.getJsonArray(JSON_SLIDES);
	for (int i = 0; i < jsonSlidesArray.size(); i++) {
	    JsonObject slideJso = jsonSlidesArray.getJsonObject(i);
	    slideShowToLoad.addSlide(slideJso.getString(JSON_IMAGE_FILE_NAME),
		    slideJso.getString(JSON_IMAGE_PATH),
		    slideJso.getString(JSON_CAPTION));
	}
    }

    // AND HERE ARE THE PRIVATE HELPER METHODS TO HELP THE PUBLIC ONES
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
    }

    private ArrayList<String> loadArrayFromJSONFile(String jsonFilePath, String arrayName) throws IOException {
	JsonObject json = loadJSONFile(jsonFilePath);
	ArrayList<String> items = new ArrayList();
	JsonArray jsonArray = json.getJsonArray(arrayName);
	for (JsonValue jsV : jsonArray) {
	    items.add(jsV.toString());
	}
	return items;
    }
    /*
    private JsonArray makeSlidesJsonArray(List<Component> slides) {
	JsonArrayBuilder jsb = Json.createArrayBuilder();
	for (Component slide : slides) {
	    JsonObject jso = makeSlideJsonObject(slide);
	    jsb.add(jso);
	}
	JsonArray jA = jsb.build();
	return jA;
    }

    private JsonObject makeSlideJsonObject(Page slide) {
	JsonObject jso = Json.createObjectBuilder()
		.add(JSON_IMAGE_FILE_NAME, slide.getImageFileName())
		.add(JSON_IMAGE_PATH, slide.getImagePath())
		.add(JSON_CAPTION, slide.getCaption())
		.build();
	return jso;
    }
    */
    
    //make a single page
    public JsonObject makePageJsonObject(Page p){
        JsonObject jso= Json.createObjectBuilder()
                .add("title", p.getTitle())
                .add("name", p.getStudentName())
                .add("banner",p.getBanner())
                .add("bannertext",p.getBannerText())
                .add("color",p.getColorTheme())
                .add("layout", p.getLayoutTheme())
                .add("font",p.getPageFont())
                .add("footer",p.getFooter())
                .add("components",makeCompArrayObject(p))
                .build() ;
        return jso;
    }
    
    //creates array of all comps in a page
    public JsonArray makeCompArrayObject(Page p){
        JsonArrayBuilder jsb= Json.createArrayBuilder();
        for(Component c:p.getComponents()){
            jsb.add(makeCompObject(c));
        }
        JsonArray JA= jsb.build();
        return JA;
    }
    
    //make a 1 component into json-
    public JsonObject makeCompObject(Component comp){
        
        if(comp.getType()=="p"){
                return makeTextObject((Paragraph)comp);
        }else if(comp.getType()=="list"){
                return makeListObject((ListComp)comp);
        }else if(comp.getType()=="img"){
                return makeImgObject((Img)comp);
        }else if(comp.getType()=="video"){
                return makeVideoObject((Video)comp);
        }else if(comp.getType()=="slideshow"){
                return makeSSObject((SlideShowModelComponent)comp);
        }else{
            return null;
        }
    }
    public JsonObject makeTextObject(Paragraph p){
        JsonObject jso = Json.createObjectBuilder()
                .add("type", p.getType())
                .add("pgfont",p.getFont())
                .add("text",p.getText())
                .build();
        return jso;
    }
    public JsonObject makeListObject(ListComp p){
        JsonObject jso = Json.createObjectBuilder()
                .add("type", p.getType())
                .add("text",p.getBullets())
                .build();
        return jso;
    }
    public JsonObject makeImgObject(Img p){
        JsonObject jso = Json.createObjectBuilder()
                .add("type", p.getType())
                .add("src",p.getImgFileName()+p.getImgPath())
                .add("orientation",p.getOrientation())
                .add("width",p.getImgW())
                .add("height",p.getImgH())
                .add("caption",p.getCaption())
                .build();
        return jso;
    }
    public JsonObject makeVideoObject(Video p){
        JsonObject jso=Json.createObjectBuilder()
                .add("type",p.getType())
                .add("src", p.getSrc()+p.getVideoFileName())
                .add("width",p.getVideoW())
                .add("height", p.getVideoH())
                .add("caption",p.getCaption())
                .build();
        return jso;
    }
    public JsonObject makeSSObject(SlideShowModelComponent p ){
        JsonObject jso=Json.createObjectBuilder()
                .add("type",p.getType())
                .build();
                return jso;
                
    }
    
}


