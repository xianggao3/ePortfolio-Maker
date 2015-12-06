/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var navbar;
var htmlpg = document.location.pathname.match(/[^\/]+$/)[0];
// DATA FOR CURRENT eport
var name;
var pages;
//pages var
var title;
var bannerpath;
var bannerfilename;
var bannertext
var color;
var layout;
var font
var footer;
var components;
//components var
var type;
var text;
var caption;
var pgfont;
var imgpath;
var imgfilename;
var orientation;
var width;
var height;
var caption;
var videopath;
var videofilename;
var i;
var stylesheet="stylesheet";

function Component(type) {
    this.type=type;
}
function Page(title, bannertext,bannerpath,bannerfilename,  components, color, layout, font, footer,studentName){
    this.title = title;
    this.bannertext=bannertext;
    this.bannerpath=bannerpath;
    this.bannerfilename=bannerfilename;
    this.components=components;
    this.color=color;
    this.layout=layout;
    this.font=font;
    this.footer=footer;
    this.studentName=studentName;
    }


function initePortfolio(){
    navbar = new Array();
    pages = new Array();
    var jsonFile = "./data/"+htmlpg.substr(0,htmlpg.length-4)+"json";
    loadData(jsonFile);
}

function loadData(jsonFile) {
    $.getJSON(jsonFile, function (json) {
        loadePortfolio(json);
    });
}

function loadePortfolio(ePortfolioData) {
    name = ePortfolioData.name;
    loadNavBar(ePortfolioData);
    initPage(ePortfolioData.pages[0]);
}

function loadNavBar(ePortfolioData){
    for(i =0; i<ePortfolioData.pages.length;i++){
        var e=document.createElement("a");
        e.innerHTML = ePortfolioData.pages[i].title;
        //if pressed event: initPage(ePortfolioData.pages[i]);?
        //$(e).attr("href",ePortfolioData.navbar[i].src);
        document.getElementById("navbar").appendChild(e);
        
        var studentName = document.createElement("h5");
        studentName.innerHTML=ePortfolioData.name;
        document.getElementById("navbar").appendChild(studentName);
        
    }
}

function initPage(pageData){
    $("title").html(title);
    loadBanner(pageData);
    ePortfolioData.pages.content.every(addContent(ePortfolioData));
}

function loadBanner(pageData){
    var bannerTitle = document.createElement("h1");
    bannerTitle.innerHTML=pageData.bannertext;
    document.getElementById("banner").appendChild(bannerTitle);
    
    var bannerImg=document.createElement("img");
    $(bannerImg).attr("src",(pageData.bannerpath+pageData.bannerfilename));
    document.getElementById("banner").appendChild(bannerImg);
    
    var color = document.createElement("link");
    $(color).attr("rel","stylesheet");
    $(color).attr("type","text/css");
    $(color).attr("href",pageData.color+".css");
    document.getElementById("body").appendChild(color);
    
    var layout = document.createElement("link");
    $(layout).attr("rel","stylesheet");
    $(layout).attr("type","text/css");
    $(layout).attr("href",pageData.layout+".css");
    document.getElementById("body").appendChild(layout);
    
    var font = document.createElement("link");
    $(font).attr("rel","stylesheet");
    $(font).attr("type","text/css");
    $(font).attr("href",pageData.font+".css");
    document.getElementById("body").appendChild(font);
    
    addContent(pageData.components);
}


function addContent(ePortfolioData){
    for(var i =0;i<ePortfolioData.length;i++){
        if(ePortfolioData[i].type=="p"){
            var p = document.createElement("p");
            p.innerHTML=ePortfolioData[i].text;
            $(p).attr("rel","stylesheet");
            $(p).attr("type","text/css");
            $(p).attr("href",ePortfolioData.pgfont+".css");
            document.getElementById("content").appendChild(p);
        }if(ePortfolioData[i].type=="header"){
            var p = document.createElement("h4");
            p.innerHTML=ePortfolioData[i].text;
            document.getElementById("content").appendChild(p);
        }else if(ePortfolioData[i].type=="list"){
            var list= document.createElement("ul");
            list.innerHTML.ePortfolioData[i].text;
            document.getElementById("content").appendChild(list);
        }
        else if(ePortfolioData.pages.content[i].type=="video"){
            var video = document.createElement("video");
            $(video).attr("width",ePortfolioData[i].width);
            $(video).attr("length",ePortfolioData[i].height);
            $(video).attr("autoplay","");
            var source = document.createElement("source");
            $(source).attr("src",ePortfolioData[i].videopath+ePortfolioData[i].videofilename);
            video.appendChild(source);
            document.getElementById("content").appendChild(video);
            
            var p = document.createElement("h5");
            p.innerHTML=ePortfolioData[i].caption;
            document.getElementById("content").appendChild(p);
        }
        else if(ePortfolioData.pages.content[i].type=="img"){
            var img = document.createElement("img");
            $(img).attr("src",ePortfolioData[i].imgpath+ePortfolioData[i].imgfilename);
            $(img).attr("height",ePortfolioData[i].height);
            $(img).attr("width",ePortfolioData[i].width);
            document.getElementById("content").appendChild(img);
            
            var p = document.createElement("h5");
            p.innerHTML=ePortfolioData[i].caption;
            document.getElementById("content").appendChild(p);
        }
        else if(ePortfolioData.pages.content[i].type=="slideshow"){
            var slideshow=document.createElement("iframe");
            $(slideshow).attr("width","100%");
            $(slideshow).attr("height","700px");
            $(slideshow).attr("src",ePortfolioData[i].iframe);
            document.getElementById("content").appendChild(slideshow);
        }
    }
}

