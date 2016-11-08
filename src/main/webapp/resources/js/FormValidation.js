function validateStockForm() {
   var stockAmount = document.getElementById("walletItemAmount");
   var stockUnit = document.getElementById("stockUnit");
   var stockAvailable = document.getElementById("stockAmount");
   var walletResources = document.getElementById("walletResources");
   var stockPrice = document.getElementById("walletItemPrice");

   if(parseInt(stockAmount.value,10)==0){
       span = document.getElementById("errorblockAmount");
       text = document.createTextNode("Incorrect amount. Must be more than 0.");
       span.innerText = text.textContent;
       return false;
    }
   if(parseInt(stockAmount.value,10) > parseInt(stockAvailable.value,10)){
      span = document.getElementById("errorblockAmount");
      text = document.createTextNode("Incorrect amount. Must be less than or equal to available stock amount");
      span.innerText = text.textContent;
      return false;
   }
   if((stockAmount.value%stockUnit.value) != 0){
      span = document.getElementById("errorblockAmount");
      text = document.createTextNode("Incorrect amount. Must be multiplier of stock unit");
      span.innerText = text.textContent;
      return false;
   }
   if(walletResources.value < stockAmount.value * stockPrice.value){
       span = document.getElementById("errorblockAmount");
       text = document.createTextNode("Insufficient founds.");
       span.innerText = text.textContent;
       return false;
   }

   return true;
      
}
function validateUserForm(){

    var walletResource = document.getElementById("wallet.walletResource");
    walletResourceValue = walletResource.value

    for (var i = 0; i < walletResourceValue.length; i++) {
        if((walletResourceValue.charCodeAt(i) >= 48 && walletResourceValue.charCodeAt(i) <= 57) ||  walletResourceValue.charCodeAt(i) <= 46){
            return true;
        }
        else {
            span = document.getElementById("errorblockResource");
            text = document.createTextNode("Value must contain only numbers 0-9");
            span.innerText = text.textContent;
            return false;
        }
    }

    
}