jQuery(document).ready(function($) {
    var span = document.getElementById("connectionError");
    $('.sellConfirm').on('click', function () {
        if(span.innerText != ""){
            alert("No connection to stock server. Transactions are not available.");
                return false;
            }
        return confirm('Are you sure you want to sell this stock?');
    });
    $('.buyConfirm').on('click', function () {
            return confirm('Are you sure you want to buy this stock?');
        });

});

