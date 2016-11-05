function setup(){
    loadJSON("http://webtask.future-processing.com:8068/stocks", gotData, 'jsonp');

}

function gotData(data){
    println(data);
}
function printData(){
//    var some_data = "json";

    span = document.getElementById("msg");
    txt = document.createTextNode("your cool text");
    span.innerText = txt.textContent;
}