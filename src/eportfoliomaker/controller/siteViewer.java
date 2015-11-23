/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliomaker.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author xgao3
 */
public class siteViewer extends Stage{
    ScrollPane scrollPane;
    WebView webView;
    WebEngine webEngine;
    
    public siteViewer() throws MalformedURLException{
        webView = new WebView();
	scrollPane = new ScrollPane(webView);
        String indexPath = "SITES_DIR + slides.getTitle() + SLASH + INDEX_FILE;";
	File indexFile = new File(indexPath);
	URL indexURL = indexFile.toURI().toURL();
	
	// SETUP THE WEB ENGINE AND LOAD THE URL
	webEngine = webView.getEngine();
	webEngine.load("https://www.google.com");
	webEngine.setJavaScriptEnabled(true);
        
	this.setTitle("DUMMY WEBVIEWER");
        Scene scene = new Scene(webView, 1100, 650);
	setScene(scene);
	this.showAndWait();
	
    }
}
