var title;
var navbar;
var pages;

function initePortfolio(){
    navbar = new Array();
    pages = new Array();
    var jsonFile = "./data/ePortfolioData.json";
    loadData(jsonFile);
}

function loadData(jsonFile) {
    $.getJSON(jsonFile, function (json) {
        alert(json.title);
        loadePortfolio(json);
        initPage();
    });
}

function loadePortfolio(ePortfolioData) {
    title = ePortfolioData.title;
    for(var i =0;i<ePortfolioData.navbar.length;i++){
        var e=document.createElement(type);
        e.innerHTML = ePortfolioData.navbar[i].page;
        navbar.appendChild(e);
        e.attr("href",ePortfolioData.navbar[i].src);
    }
}

function initPage(){
    $("title").html(title);
}