function refreshData()
{$.ajax({
    type: 'GET',
    url: 'http://webtask.future-processing.com:8068/stocks',
    async: true,
    beforeSend: function (xhr) {
    if (xhr && xhr.overrideMimeType) {
      xhr.overrideMimeType('application/json;charset=utf-8');
    }
  },
    dataType: 'json',
    success: function (data) {
    console.log(data);

    var items = data.items.map(function (item) {
        return  '<td>' + item.name + '</td><td>' + item.code  + '</td><td>' + item.unit + '</td><td>' + item.price + '</td>' + '<td><a href = "./buyStock?stockName='+ item.name+'&stockBuyPrice=' + item.price +'&stockUnit=' + item.unit +'">Buy</a></td>';
    });

    var month = new Array();
    month[0] = "01";
    month[1] = "02";
    month[2] = "03";
    month[3] = "04";
    month[4] = "05";
    month[5] = "06";
    month[6] = "07";
    month[7] = "08";
    month[8] = "09";
    month[9] = "10";
    month[10] = "11";
    month[11] = "12";

    if (items.length) {
        var d = new Date(data.publicationDate);
        var columns = ["Company name", "Company code", "Unit", "Price", "Action"];
        var resultString='<p>Stock refresh time: ' + d.getFullYear() + '-' + month[d.getMonth()]  + '-' + d.getDate() + '     ' + d.getHours()  + ':' +
        d.getMinutes() + ':' + d.getSeconds() +'</p>'+ '<table class = "mainTable">';

        for(var j=0;j<columns.length;j++){
            resultString+= '<th>'+ columns[j] + '</th>';
        }
        $(items).each(function(i, items) {
            resultString+='<tr>';
            resultString+= items;
            resultString+='</tr>';
        });
        resultString+='</table>';
        $('#fpData').html(resultString);
   }
   }
});
}
setInterval(refreshData, 15000);
