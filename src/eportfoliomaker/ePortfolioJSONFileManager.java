/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker;

import org.json.*;
import eportfoliomaker.model.Component;
import eportfoliomaker.model.Header;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
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

    /**ALL LOADING HERE
     * This method loads the contents of a JSON file representing a slide show
     * into a SlideSShowModel objecct.
     *
     * @param ePortToLoad The slide show to load
     * @param jsonFilePath The JSON file to load.
     * @throws IOException
     */
    public void loadSlideShow(ePortfolioModel ePortToLoad, String jsonFilePath) throws IOException {
	// LOAD THE JSON FILE WITH ALL THE DATA
        ePortToLoad.getUi().reset();
	JsonObject json = loadJSONFile(jsonFilePath);

	// NOW LOAD THE COURSE
	ePortToLoad.reset();
	ePortToLoad.setStudentName(json.getString("name"));
	JsonArray pageJsonArray = json.getJsonArray("pages");
	for (int i = 0; i < pageJsonArray.size(); i++) {
	    JsonObject pageJso = pageJsonArray.getJsonObject(i);
            Page p = new Page(ePortToLoad.getUi());
	    ePortToLoad.getPages().add(p);
            
            p.setTitle(pageJso.getString("title"));
            p.setBannerText(pageJso.getString("bannertext"));
            p.setBannerPath(pageJso.getString("bannerpath"));
            p.setBannerFileName(pageJso.getString("bannerfilename"));
            p.setColorTheme(pageJso.getString("color"));
            p.setLayoutTheme(pageJso.getString("layout"));
            p.setPageFont(pageJso.getString("font"));
            p.setFooter(pageJso.getString("footer"));
            System.out.println(pageJso.getJsonArray("components"));
            p.setComponents(loadCompsArray(pageJso.getJsonArray("components")));
           Tab t = new Tab();
           t.setText(p.getTitle());
           ePortToLoad.getUi().getTabPane().getTabs().add(t);
           ePortToLoad.getUi().setCurrentTab(t);
            ePortToLoad.setSelectedPage(ePortToLoad.getPages().get(i));
            ePortToLoad.getUi().reloadPagePane();
	}
        
    }
    public ObservableList<Component> loadCompsArray(JsonArray compArray) throws IOException{
        ObservableList<Component> comps = FXCollections.observableArrayList();
        for(int i=0;i<compArray.size();i++){
            String compType = compArray.getJsonObject(i).getString("type");
            System.out.println(compType);
            if(compType.equals("p")){
                Paragraph a = new Paragraph();
                a.setFont(compArray.getJsonObject(i).getString("pgfont"));
                a.setText(compArray.getJsonObject(i).getString("text"));
                comps.add(a);
            }else if (compType.equals("header")){
                Header a = new Header();
                a.setText(compArray.getJsonObject(i).getString("text"));
                comps.add(a);
                
            }else if(compType.equals("list")){
                ListComp a = new ListComp();
                a.setBullets(compArray.getJsonObject(i).getString("text"));
                comps.add(a);
            }else if(compType.equals("img")){
                Img a = new Img();
                a.setCaption(compArray.getJsonObject(i).getString("caption"));
                a.setImgH((compArray.getJsonObject(i).getString("height")));
                a.setImgW((compArray.getJsonObject(i).getString("width")));
                a.setOrientation(compArray.getJsonObject(i).getString("orientation"));
                a.setImgFileName(compArray.getJsonObject(i).getString("imgfilename"));
                a.setImgPath(compArray.getJsonObject(i).getString("imgpath"));
                comps.add(a);
            }else if(compType.equals("video")){
                Video a = new Video();
                a.setCaption(compArray.getJsonObject(i).getString("caption"));
                a.setVideoH((compArray.getJsonObject(i).getString("height")));
                a.setVideoW((compArray.getJsonObject(i).getString("width")));
                a.setVideoFileName(compArray.getJsonObject(i).getString("videofilename"));
                a.setVideoPath(compArray.getJsonObject(i).getString("videopath"));
                comps.add(a);
            }else if(compType.equals("slideshow")){
                
            }
        }return comps;
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
    
    
    
    //ALL SAVING HERE
     public void saveePortfolioPage(ePortfolioModel pToSave) throws IOException {
	StringWriter sw = new StringWriter();
        JsonObject singlePgJSON = saveePortfolio(pToSave);

	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);

	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(singlePgJSON);
	jsonWriter.close();

	// INIT THE WRITER
	String slideShowTitle = "" + pToSave.getStudentName();
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

    
     public JsonObject saveePortfolio(ePortfolioModel ePToSave){//enter 1 model, will do gen 1 html per page
       JsonObject jso= Json.createObjectBuilder()
               .add("name", ePToSave.getStudentName())
               .add("pages",makePagesArrayObject(ePToSave))
               .build();
       return jso;
    }
     
    public JsonArray makePagesArrayObject(ePortfolioModel ep){
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for(Page p: ep.getPages()){
            jsb.add(makePageJsonObject(p));
        }
        JsonArray JA=jsb.build();
        return JA;
       
    }
    
    //make a single page
    public JsonObject makePageJsonObject(Page p){
        JsonObject jso= Json.createObjectBuilder()
                .add("title", p.getTitle())
                .add("bannerpath",p.getBannerPath())
                .add("bannerfilename", p.getBannerFileName())
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
        }else if(comp.getType()=="header"){
                return makeHeaderObject((Header)comp);
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
    public JsonObject makeHeaderObject(Header p){
        JsonObject jso=Json.createObjectBuilder()
                .add("type",p.getType())
                .add("text",p.getText())
                .build();
        return jso;
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
                .add("imgpath",p.getImgPath())
                .add("imgfilename",p.getImgFileName())
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
                .add("videopath", p.getVideoPath())
                .add("videofilename",p.getVideoFileName())
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


