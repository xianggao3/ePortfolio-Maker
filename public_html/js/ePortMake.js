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
        loadePortfolio(json);
        initPage();
    });
}

function loadePortfolio(ePortfolioData) {
       title = ePortfolioData.title;

}

function initPage(){
    $("title").html(title);
}