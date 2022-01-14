function validateForm() {

    var myField = document.getElementById("myNumber");
    var value = myField.value;


    if (value == "" || isNaN(value) || value < 0 || value > 10) {
        alert("Bạn vui lòng nhập lại mật khẩu");
        myField.focus();
        return false;
    }


}
function checkLoginEmail() {
    // event.preventDefault();
    let mess = getElementById("errors");
    let getEmail = document.getElementById("exampleInputEmail").value;
    let letter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    if (getEmail == "") {
        document.getElementById("exampleInputEmail").style.backgroundColor = "yellow";
        alert('lỗi');
    } else if (!getEmail.match(letter)) {
        document.getElementById("exampleInputEmail").style.backgroundColor = "yellow";
        mess += "Email sai định dạng vui lòng nhập lại email";
    } else {
        mess.innerHTML;
    }
}


//continue  
// shopping cart 
let btnAddtoCart = document.querySelectorAll(".shop-product-details-content .shop-buttons a");
btnAddtoCart.forEach(function (value, i) {
    value.addEventListener("click", function (event) {
        let btnAdd = event.target;
        let getInforProduct = btnAdd.parentElement.parentElement.parentElement.parentElement;
        console.log(getInforProduct);
        let productIdMain = getInforProduct.querySelector("#productId").value;
        let imgProductMain = getInforProduct.querySelector(".shop-details-thumb-slider-active .item img").src;
        let productNameMain = getInforProduct.querySelector(".shop-product-details-content h2").innerText;
        let productPriceMain = getInforProduct.querySelector(".shop-product-details-content .pricing .discount-price span").innerText;
        let priceProductMain = getInforProduct.querySelector(".shop-product-details-content .pricing .regular-price span").innerHTML;
        let quantityMain = getInforProduct.querySelector(".product-quantity input").value;
        console.log(quantityMain);
        let productMain = {
            productId: productIdMain,
            imgProduct: imgProductMain,
            productName: productNameMain,
            productPrice: productPriceMain, 
            priceRegular: priceProductMain,
            quantity: quantityMain
        };
        console.log(productMain);
        addElementMain(productIdMain, priceProductMain, productPriceMain, productNameMain, imgProductMain, quantityMain);
    })
        cart.push(productMain);
        console.log(productMain);
        addElementMain(productIdMain, priceProductMain, productPriceMain, productNameMain, imgProductMain,quantityMain);
        sessionStorage.setItem("cart", JSON.stringify(cart));
})
function addElementMain(productIdMain, priceProductMain, productPriceMain, productNameMain, imgProductMain,quantityMain) {

    let addElementLiMain = document.createElement("li");
    let addHtmlMain = ` <li>
                    <div class="amm-single-shopping-cart media">
                        <div class="cart-image">
                            <img src="`+ imgProductMain + `" alt="Cart">
                        </div>
                        <div class="cart-content media-body pl-15">
                            <input type="hidden" value="`+ productIdMain + `" id="productId">
                            <h6><a href="#">`+ productNameMain + `</a></h6>
                            <input id="productQuantity" type="number" min="1" style="width: 25px;
                            height: 19px;
                            font-size: 11px;" value="`+ quantityMain + `" ><br>
                            <span class="price-discount">`+ productPriceMain + `</span>
                            <span class="price-close">`+ priceProductMain + `</span>
                            <span class="remove"><i class="fas fa-times"></i></span>
                        </div>
                    </div> 
                
                </li>`;
    addElementLiMain.innerHTML = addHtmlMain;
    let selectEleMain = document.querySelector(".amm-shopping_cart-list-items ul");
    selectEleMain.append(addElementLiMain);
    cartTotalMain();
}

function cartTotalMain() {
    let getcart = sessionStorage.getItem("cart");
    console.log(getcart);
    let cartMain;
    if (getcart.length > 0) {
        debugger;
        cartMain = JSON.parse(getcart);
    }
    console.log(cartMain);
    let cartItem = document.querySelectorAll(".amm-shopping_cart-list-items ul li");
    let sumPay = 0;
    if (cartItem.length > 0) {

        for (let i = 0; i < cartItem.length; i++) {
            let getPrices = cartItem[i].querySelector(".cart-content .price-discount");
            let getPrice = getPrices.innerText;
            let quantityProduct = cartItem[i].querySelector("#productQuantity").value;
            totalB = getPrice * quantityProduct * 1000;
            sumPay += totalB;
            console.log(totalB);

        }
    }
    let appendSum = document.querySelector(".amm-shopping_cart-btn p span");
    console.log(appendSum);
    appendSum.innerHTML = sumPay;
    // changePriceProduct();
   
}

// function deleteProduct() {
//     let cartItem = document.querySelectorAll(".amm-shopping_cart ul>li");
//     console.log(cartItem);
//     for (let i = 0; i < cartItem.length; i++) {

//         let iconDele = document.querySelectorAll(".remove");
//         console.log(iconDele);
//         iconDele[i].addEventListener("click", function (event) {
//             debugger;
//             let getcart = sessionStorage.getItem("cart");
//             let cartMain;
//             if (getcart.length > 0) {
//                 cartMain = JSON.parse(getcart);
//             }
//             let cartDele = event.target;
//             console.log("cartDele");
//             let getProductId = this.id;
//             let products = cartMain.filter(x => x.productId != getProductId);
//             sessionStorage.setItem("cart", JSON.JSON.(products));
//             let cartItemPro = cartDele.parentElement.parentElement.parentElement.parentElement;
//             console.log(cartItemPro);
//             cartItemPro.remove();
//             cartTotal();
//         });

//     }
// }
let icon = document.querySelectorAll(" .cart-list-icon ul> li");
let cart = new Array();
icon.forEach(function (value, index) {
    value.addEventListener("click", function (event) {
        let btnItem = event.target; // lấy đúng cái icon 
        let productMain = btnItem.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement;
        // let productMain = $(this).parents(".single-shop-box");
        let productId = productMain.querySelector("#productId").value;
        let imgProduct = productMain.querySelector("div>img").src;
        let productName = productMain.querySelector(".content a").innerText;
        let productPrice = productMain.querySelector(".content .discount-price").innerText;
        let priceRegular = productMain.querySelector(".content .regular-price").innerText;
        // let indexPro = productMain.querySelector(".cart-content input").value;
        // console.log(indexPro);

        // let product = new Array(productId,priceRegular,productPrice,productName,imgProduct);
        let product = {
            productId: productId,
            imgProduct: imgProduct,
            productName: productName,
            productPrice: productPrice,
            priceRegular: priceRegular,
            quantity: 1
        };
        cart.push(product);
        console.log(product);
        addElement(productId, priceRegular, productPrice, productName, imgProduct);
        sessionStorage.setItem("cart", JSON.stringify(cart));
    })

});

function addElement(productId, priceRegular, productPrice, productName, imgProduct) {

    let addElementLi = document.createElement("li");
    let addHtml = ` 
                    <div class="amm-single-shopping-cart media">
                        <div class="cart-image">
                            <img src="`+ imgProduct + `" alt="Cart">
                        </div>
                        <div class="cart-content media-body pl-15">
                            <input type="hidden" value="`+ productId + `" id="productId">
                            <h6><a href="#">`+ productName + `</a></h6>
                            <input id="productQuantity" type="number" onChange="changePriceProduct(this)" min="1" style="width: 25px;
                            height: 19px; 
                            font-size: 11px;" value="1" ><br>
                            <span class="price-discount">`+ productPrice + `</span><sup>đ</sup>
                            <span class="price-close">`+ priceRegular + `</span><sup>đ</sup>
                            <span class="remove" id="`+ productId + `"><i class="fas fa-times"></i></span>
                        </div>
                    </div> 
                
                `;
    addElementLi.innerHTML = addHtml;
    let selectEle = document.querySelector(".amm-shopping_cart-list-items ul");
    selectEle.append(addElementLi);
    cartTotal();
    deleteProduct();


}

function cartTotal() {
    let getcart = sessionStorage.getItem("cart");
    let cartMain;
    if (getcart.length > 0) {
        debugger;
        cartMain = JSON.parse(getcart);
    }
    console.log(cartMain);
    let cartItem = document.querySelectorAll(".amm-shopping_cart-list-items ul li");
    let sumPay = 0;
    if (cartItem.length > 0) {

        for (let i = 0; i < cartItem.length; i++) {
            let getPrices = cartItem[i].querySelector(".cart-content .price-discount");
            let getPrice = getPrices.innerText;
            let quantityProduct = cartItem[i].querySelector("#productQuantity").value;
            totalB = getPrice * quantityProduct * 1000;
            sumPay += totalB;
            console.log(totalB);

        }
    }
    let appendSum = document.querySelector(".amm-shopping_cart-btn p span");
    console.log(appendSum);
    appendSum.innerHTML = sumPay;
    // changePriceProduct();
}

function deleteProduct() {
    let cartItem = document.querySelectorAll(".amm-shopping_cart ul>li");
    console.log(cartItem);
    for (let i = 0; i < cartItem.length; i++) {

        let iconDele = document.querySelectorAll(".remove");
        iconDele[i].addEventListener("click", function (event) {
            debugger;
            let getcart = sessionStorage.getItem("cart");
            let cartMain;
            if (getcart.length > 0) {
                cartMain = JSON.parse(getcart);
            }
            let cartDele = event.target;
            let getProductId = this.id;
            let products = cartMain.filter(x => x.productId != getProductId);
            sessionStorage.setItem("cart", JSON.stringify(products));
            let cartItemPro = cartDele.parentElement.parentElement.parentElement.parentElement;
            console.log(cartItemPro);
            cartItemPro.remove();
            cartTotal();
        });

    }
}


function changePriceProduct(productItem) {
    let getcart = sessionStorage.getItem("cart");
    let cartMain = JSON.parse(getcart);
    debugger;

    let quantity = productItem.value;
    let sum = 0;
    let getParent = productItem.parentElement;
    let productId = getParent.querySelector("#productId").value;
    let products = cart.filter(x => x.productId === productId);
    if (products.length > 0) {
        let product = products[0];
        console.log(product);
        product.quantity = quantity; // cập nhật quantity vào carts 
    }

    //lấy tất cả các sản phẩm có trong carts và tính toán tổng giá trị đơn hàng
    cart.forEach(product => {
        let sumProduct = parseInt(product.quantity) * product.productPrice * 1000;
        sum += sumProduct;
    });
    let appendSum = document.querySelector(".amm-shopping_cart-btn p");
    console.log(appendSum);
    appendSum.innerHTML = sum.toLocaleString('de-DE');
    // luu trên sessions
    sessionStorage.setItem("cart", JSON.stringify(cart));
}

function viewCarts() {
    console.log("da vao ");
    cart = new Array();
    let mainProduct = document.querySelectorAll(".amm-shopping_cart-list-items ul li");
    console.log("mainProduct");
    for (let i = 0; i < mainProduct.length; i++) {
        let productId = mainProduct[i].querySelector("#productId").value;
        let productName = mainProduct[i].querySelector(".cart-content h6").innerText;
        let productImg = mainProduct[i].querySelector(".cart-image img").src;
        let productPrice = mainProduct[i].querySelector(".cart-content .price-discount").innerText;
        let quantity = mainProduct[i].querySelector("#productQuantity").value;
        let inforProduct = {
            productId: productId,
            productName: productName,
            productImg: productImg,
            productPrice: productPrice,
            quantity: quantity
        }
        cart.push(inforProduct);
    }
    //console.log(cart);
    sessionStorage.setItem("cart", JSON.stringify(cart));
}

//show sp vào giỏ hàng 
function showProduct() {

    let getcart = sessionStorage.getItem("cart");
    let cartMain = JSON.parse(getcart);
    let payment = "";

    //priceRegular,productPrice,productName,imgProduct
    for (let i = 0; i < cartMain.length; i++) {
       
        payment += `  <li>
                        <div class="cover-product row">
                            <div class="img-product pl-5">
                                <img src="`+ cartMain[i].productImg + `" alt="">
                            </div>
                            <div class="infor-product pl-3">
                                <input type="hidden" value="`+ cartMain[i].productId +`" id="`+ cartMain[i].productId +`">
                                <a href="./productdetail.html">
                                    <h3>`+ cartMain[i].productName + `</h3>
                                </a>
                                <p><strong>Kích thước:</strong> <span>1</span>kg</p>
                                <div class="discount-price"><span>`+ cartMain[i].productPrice + ` </span> <sup>đ</sup> </div>

                            </div>
                            <div class="change-pro">
                                <div class="buttons_added" >
                                <input class="inputQuantity" onChange="updateQuantity(this)"  id='a`+ cartMain[i].productId + `' " min='1' name='quantity' type='number' value='1' />
                                
                                <div class="icon-delete" id="`+ cartMain[i].productId +`">
                                    <i class="icon-delete1 fas fa-trash-alt"></i>
                                </div>
                                </div>
                            </div>
                            <div class="sumPro">
                                <span>25.000 </span>đ
                            </div>

                        </div>
                    </li>`;

    };
 // <input
                                    //     onclick="var result = document.getElementById('a`+ cartMain[i].productId + `'); var qty = result.value; if( !isNaN(qty) &amp; qty > 1 ) result.value--;return false;"
                                    //     type='button' value='-' />
                                    // 
                                    // <input
                                    //     onclick="var result = document.getElementById('a`+ cartMain[i].productId + `'); var qty = result.value; if( !isNaN(qty)) result.value++;return false;"
                                    //     type='button' value='+' />
    var mainCart = $("#mainCart");
    if (mainCart && mainCart !== null) {
        $(mainCart)[0].innerHTML = payment;
        // immidiateSum();
    }
    // changeQuantity();
    deleteProductViewCart();
}


function immidiateSum() {

    let getcart = sessionStorage.getItem("cart");
    let cartMain;
    if (getcart.length > 0) {
        debugger;
        cartMain = JSON.parse(getcart);
    }
    let getInforProduct = document.querySelectorAll(".displayProduct ul>li");
    let sumPay = 0;
    let sum = 0;
    if(getInforProduct.length > 0){
    for (let i = 0; i < getInforProduct.length; i++) {
        let getPrice = getInforProduct[i].querySelector("div .discount-price span").innerText;
        let quantityProduct = getInforProduct[i].querySelector(".change-pro .buttons_added .inputQuantity").value;
        totalB = getPrice * quantityProduct * 1000;
        sumPay += totalB;
        console.log(totalB);
    }
    
        }
        let sumPrice = document.querySelector(".immidiate-fee span");
        sumPrice.innerHTML = sumPay.toLocaleString("de-DE");
        console.log(sumPrice);
        let sumPriceMain = document.querySelector(".sum-fee span");
        sumPriceMain.innerHTML = sumPay.toLocaleString("de-DE");
        sumShip();
}
function deleteProductViewCart() {
    let cartItem = document.querySelectorAll(".displayProduct ul>li");
    console.log(cartItem);
    for (let i = 0; i < cartItem.length; i++) {

        let iconDele = document.querySelectorAll(".icon-delete");
        console.log(iconDele);
        iconDele[i].addEventListener("click", function (event) {
            debugger;
            let getcart = sessionStorage.getItem("cart");
            let cartMain;
            if (getcart.length > 0) {
                cartMain = JSON.parse(getcart);
            }
            let cartDele = event.target;
            console.log("cartDele");
            let getProductId = this.id;
            let products = cartMain.filter(x => x.productId != getProductId);
            sessionStorage.setItem("cart", JSON.stringify(products));
            let cartItemPro = cartDele.parentElement.parentElement.parentElement.parentElement.parentElement;
            console.log(cartItemPro);
            cartItemPro.remove();
            immidiateSum();
        });

    }


}
function immidiateSum_Payment() {

    let getcart = sessionStorage.getItem("cart");
    let cartMain = JSON.parse(getcart);
    console.log(cartMain);
    let getInforProduct = document.querySelectorAll(".showProduct ul>li");
    let sumPayment = 0;
    if(getInforProduct.length>0){
    for (let i = 0; i < getInforProduct.length; i++) {

        let getPrice = getInforProduct[i].querySelector(".cover-product-pay .discount-price-pay").innerText;
        console.log(getPrice);
        let quantityProduct = getInforProduct[i].querySelector(".infor-product-pay .quantityPayment").innerText;
        console.log(quantityProduct);
        totalB = getPrice * quantityProduct * 1000;
        sumPayment += totalB;
        console.log(totalB);

    }
}

    let sumPrice = document.querySelector(".sumPriceproduct .padding-price1");
    sumPrice.innerHTML = sumPayment.toLocaleString("de-DE");
    console.log(sumPrice);
    let sumPriceMain = document.querySelector(".sumarizePrice .padding-price");
    sumPriceMain.innerHTML = sumPayment.toLocaleString("de-DE");


}
function updateQuantity(productPayment) {
    
        let getcart = sessionStorage.getItem("cart");
        let cartMain;
        if (getcart.length > 0) {
            cartMain = JSON.parse(getcart);
        }
    
    debugger;

    let quantity = productPayment.value;
    let sum = 0;
    let getParent = productPayment.parentElement.parentElement.parentElement;
    let productId1 = getParent.querySelector(".infor-product input").value;
    console.log(productId1);
    let products = cartMain.filter(x => x.productId === productId1);
    if (products.length > 0) {
        let product = products[0];
        console.log(product);
        product.quantity = quantity; // cập nhật quantity vào carts 
    }

    //lấy tất cả các sản phẩm có trong carts và tính toán tổng giá trị đơn hàng
    cartMain.forEach(product => {
        let sumProduct = parseInt(product.quantity) * product.productPrice * 1000;
        sum += sumProduct;
        console.log(sum);
    });
    let sumPrice = document.querySelector(".immidiate-fee span");
    sumPrice.innerHTML = sum.toLocaleString("de-DE");
    console.log(sumPrice);
    let sumPriceMain = document.querySelector(".sum-fee span");
    sumPriceMain.innerHTML = sum.toLocaleString("de-DE");
    // luu trên sessions
    sessionStorage.setItem("cart", JSON.stringify(products));
}
function sumShip() {
    let getcart = sessionStorage.getItem("cart");
    let cartMain = JSON.parse(getcart);
    let shipping = document.querySelector(".shipping-fee .prices")
    let ship = shipping.innerText;
    let priceShip = 20.000;
    console.log(typeof (priceShip));
    console.log(ship);
    let getInforProduct = document.querySelectorAll(".displayProduct ul>li");
    let temporarySum = document.querySelector(".immidiate-fee  .prices").innerText;
    console.log(temporarySum);
    if (temporarySum >= 50.000 || getInforProduct.length <= 0) {
        ship = 0;
        temporarySum = (parseInt(temporarySum) + parseInt(ship)) * 1000;
    }
    else {
        ship = priceShip;
        temporarySum = (parseInt(temporarySum) + parseInt(ship)) * 1000;

    }
    console.log(temporarySum);
    shipping.innerHTML = ship * 1000;
    console.log(shipping);
    let sumPriceMain = document.querySelector(".sum-fee span");
    sumPriceMain.innerHTML = parseInt(temporarySum).toLocaleString("de-DE");

}
function sumShipPayment() {
    let getcart = sessionStorage.getItem("cart");
    let cartMain = JSON.parse(getcart);
    let shipping = document.querySelector(".sumPriceproduct .padding-price2")
    let ship = shipping.innerText;
    let priceShip = 20.000;
    console.log(typeof (priceShip));
    console.log(ship);
    let getInforProduct = document.querySelectorAll("#mainCartPayment li");
    let temporarySum = document.querySelector(".sumPriceproduct  .padding-price1").innerText;
    console.log(temporarySum);
    if (temporarySum >= 50.000 || getInforProduct.length <= 0) {
        ship = 0;
        temporarySum = (parseInt(temporarySum) + parseInt(ship)) * 1000;
    }
    else {
        ship = priceShip;
        temporarySum = (parseInt(temporarySum) + parseInt(ship)) * 1000;

    }
    console.log(temporarySum);
    shipping.innerHTML = ship * 1000;
    console.log(shipping);
    let sumPriceMain = document.querySelector(".sumarizePrice .padding-price");
    sumPriceMain.innerHTML = parseInt(temporarySum).toLocaleString("de-DE");

}
//show sp vào thanh toán
function showProductToPayment() {

    let getcart = sessionStorage.getItem("cart");
    let cartMain = JSON.parse(getcart);
    let payment = "";

    //priceRegular,productPrice,productName,imgProduct
    for (let i = 0; i < cartMain.length; i++) {

        payment += `  <li>
        <div class="cover-product-pay row pb-3">
            <div class="img-product-payment pl-5">
                <img src="`+ cartMain[i].productImg + `" width="50px" height="50px" alt="">
            </div>
            <div class="infor-product-pay pl-3 ">
                <a href="./productdetail.html">
                    <h5>`+ cartMain[i].productName + `</h5>
                </a>
                <p>SL: <span class="quantityPayment">`+ cartMain[i].quantity + `</span>
                  <span class="discount-price-pay">`+ cartMain[i].productPrice + ` </span><sup>đ</sup>
                </p>
                

            </div>
            
        </div>
    </li>`;

    };

    var mainCart = $("#mainCartPayment");
    if (mainCart && mainCart !== null) {
        $(mainCart)[0].innerHTML = payment;
        // immidiateSum();
    }
    immidiateSum_Payment();
    sumShipPayment();
}
