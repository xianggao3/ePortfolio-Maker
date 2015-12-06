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
        initPage(json);
    });
}

function loadePortfolio(ePortfolioData) {
    title = ePortfolioData.title;
    loadNavBar(ePortfolioData);
    loadBanner(ePortfolioData);
}

function loadNavBar(ePortfolioData){
    for(var i =0; i<ePortfolioData.navbar.length;i++){
        var e=document.createElement("a");
        e.innerHTML = ePortfolioData.navbar[i].page;
        $(e).attr("href",ePortfolioData.navbar[i].src);
        document.getElementById("navbar").appendChild(e);
    }
}

function loadBanner(ePortfolioData){
    var bannerTitle = document.createElement("h1");
    bannerTitle.innerHTML=ePortfolioData.pages.banner;
    $(bannerTitle).attr("id","bannerTitle");
    document.getElementById("banner").appendChild(bannerTitle);
    var bannerImg=document.createElement("img");
    $(bannerImg).attr("src",ePortfolioData.pages.bannerImgPath);
    document.getElementById("banner").appendChild(bannerImg);
    
}

function initPage(ePortfolioData){
    $("title").html(title);
    var i;
    ePortfolioData.pages.content.every(addContent(ePortfolioData));
}

function addContent(ePortfolioData){
    for(var i =0;i<ePortfolioData.pages.content.length;i++){
        if(ePortfolioData.pages.content[i].type=="p"){
            var p = document.createElement("p");
            p.innerHTML=ePortfolioData.pages.content[i].p;
            
            document.getElementById("content").appendChild(p);
        }
        
        else if(ePortfolioData.pages.content[i].type=="list"){
            var list= document.createElement("ul");
            var li;
            for(var x =0; x<ePortfolioData.pages.content[i].list.length; x++){
                li = document.createElement("li");
                var r=ePortfolioData.pages.content[i].list[x].li;
                li.innerHTML=ePortfolioData.pages.content[i].list[x].li;
                list.appendChild(li);
            }
            document.getElementById("content").appendChild(list);
        }
        
        else if(ePortfolioData.pages.content[i].type=="video"){
            var video = document.createElement("video");
            $(video).attr("width","810");
            $(video).attr("length","455");
            $(video).attr("autoplay","");
            var source = document.createElement("source");
            $(source).attr("src",ePortfolioData.pages.content[i].source);
            video.appendChild(source);
            document.getElementById("content").appendChild(video);
        }
        else if(ePortfolioData.pages.content[i].type=="img"){
            var img = document.createElement("img");
            $(img).attr("src",ePortfolioData.pages.content[i].src);
            $(img).attr("height","400");
            $(img).attr("width","400");
            document.getElementById("content").appendChild(img);
        }
        else if(ePortfolioData.pages.content[i].type=="slideshow"){
            var slideshow=document.createElement("iframe");
            $(slideshow).attr("width","100%");
            $(slideshow).attr("height","700px");
            $(slideshow).attr("src",ePortfolioData.pages.content[i].iframe);
            document.getElementById("content").appendChild(slideshow);
        }
    }
}

