function myFunction() {
    document.getElementById("demo").innerHTML = "Hello World";
}
function printData(){
//    var some_data = "json";

    span = document.getElementById("get-data");
    txt = document.createTextNode("your cool text");
    span.innerText = txt.textContent;
}
function validateForm() {
    var x = document.getElementById("walletItemAmount");

    alert("hh");
}


//$(document).ready(function () {
//  $('#get-data').click(function () {
//    var showData = $('#show-data');
//
//    $.getJSON('http://webtask.future-processing.com:8068/stocks', function (data) {
//      console.log(data);
//
//      var items = data.items.map(function (item) {
//        return item.key + ': ' + item.value;
//      });
//
//      showData.empty();
//
//      if (items.length) {
//        var content = '<li>' + items.join('</li><li>') + '</li>';
//        var list = $('<ul />').html(content);
//        showData.append(list);
//        showData.text(list);
//      }
//    });
//
////    showData.text('Loading the JSON file.');
//  });
//});
////
//$.ajax({
//  type: 'GET',
//  url: 'http://webtask.future-processing.com:8068/stocks',
//  data: data,
//  async: false,
//  beforeSend: function (xhr) {
//    if (xhr && xhr.overrideMimeType) {
//      xhr.overrideMimeType('application/json;charset=utf-8');
//    }
//  },
//  dataType: 'json',
//  success: function (data) {
//    span = document.getElementById("get-data");
//        txt = document.createTextNode("your cool text");
//        span.innerText = txt.textContent;
//  }
//  span = document.getElementById("get-data2");
//          txt = document.createTextNode("transfer complete");
//          span.innerText = txt.textContent;
//
//
//
//});