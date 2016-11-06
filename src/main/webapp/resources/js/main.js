jQuery(document).ready(function($) {
    $('.sellConfirm').on('click', function () {
        return confirm('Are you sure you want to sell this stock?');
    });
    $('.buyConfirm').on('click', function () {
            return confirm('Are you sure you want to buy this stock?');
        });

});

