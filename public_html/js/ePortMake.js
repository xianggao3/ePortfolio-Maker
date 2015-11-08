var title;
var navbar;
var pages;
var htmlpg = document.location.pathname.match(/[^\/]+$/)[0];

function initePortfolio(){
    navbar = new Array();
    pages = new Array();
    var jsonFile = "./data/"+htmlpg.substr(0,htmlpg.length-4)+"json";
    loadData(jsonFile);
}

function loadData(jsonFile) {
    $.getJSON(jsonFile, function (json) {
        loadePortfolio(json);
        initPage();
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
        bannerTitle.innerHTML=ePortfolioData.pages[currentPage].banner;
        $(bannerTitle).attr("id","bannerTitle");
        document.getElementById("banner").appendChild(bannerTitle);
    
}

function initPage(){
    $("title").html(title);
}