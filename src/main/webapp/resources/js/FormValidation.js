    function validateForm() {
         var stockAmount = document.getElementById("walletItemAmount");
         var stockUnit = document.getElementById("stockUnit");
         var stockAvailable = document.getElementById("stockAmount");

         if(stockAmount.value==0){
             span = document.getElementById("errorblockAmount");
             txt = document.createTextNode("Incorrect amount. Must be more than 0.");
             span.innerText = txt.textContent;
             return false;
             }
         if(stockAmount.value > stockAvailable.value){
            span = document.getElementById("errorblockAmount");
            txt = document.createTextNode("Incorrect amount. Must be less than available stock amount");
            span.innerText = txt.textContent;
            return false;
         }
         if((stockAmount.value%stockUnit.value) != 0){
            span = document.getElementById("errorblockAmount");
            txt = document.createTextNode("Incorrect amount. Must be multiplier of stock unit");
            span.innerText = txt.textContent;
            return false;
         }


         return true;

         }