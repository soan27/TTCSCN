/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
let icon = document.querySelectorAll(".cart-list-icon ul .shopping-bag");
let getNumberShoppingCart = document.querySelector(".amm-shopping-cart-open .quantity-amm-shopping-cart-open");
let cart = new Array();
if (JSON.parse(sessionStorage.getItem("cart")) !== null) {
    cart = JSON.parse(sessionStorage.getItem("cart"));
}

let btnAddtoCart = document.querySelectorAll(".shop-product-details-content .shop-buttons a");
let duplication = 0;

window.addEventListener('DOMContentLoaded', (event) => {
    countProduct();
});
function saveCart() {
    sessionStorage.setItem('cart', JSON.stringify(cart));
}

// Load cart
function loadCart() {
    cart = JSON.parse(sessionStorage.getItem('cart'));
}
function shoppingCarts() {
    let getIconShopping = document.getElementsByClassName("amm-shopping-cart-open");
    let canvasShopping = document.getElementById("amm-shopping-cart-canvas");
    let overlay = document.getElementById("overlay");
    canvasShopping.classList.add("open");
    overlay.classList.add("open");
    addItem();
}
function removeShoppingCart() {
    let canvasShopping = document.getElementById("amm-shopping-cart-canvas");
    let overlay = document.getElementById("overlay");
    let getIconShoppingClose = document.getElementsByClassName("amm-shopping-cart-close");
    canvasShopping.classList.remove("open");
    overlay.classList.remove("open");
}
function openOverlay() {
    let canvasShopping = document.getElementById("amm-shopping-cart-canvas");
    let overlay = document.getElementById("overlay");
    let getIconShoppingClose = document.getElementsByClassName("overlay");
    canvasShopping.classList.remove("open");
    overlay.classList.remove("open");
}

icon.forEach(function (value, index) {
    value.addEventListener("click", function (event) {
        let btnItem = event.target; // lấy đúng cái icon
        let productMain = btnItem.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement;
        // let productMain = $(this).parents(".single-shop-box");
        let productId = productMain.querySelector("#productId").value;
        let imgProduct = productMain.querySelector(".thumb a>img").src;
        let productName = productMain.querySelector(".content a").innerText;
        let productPrice = productMain.querySelector(".content .discount-price").innerText;
        let priceRegular = productMain.querySelector(".content .regular-price").innerText;
        let quantity = 1;
        let product = {
            productId: productId,
            imgProduct: imgProduct,
            productName: productName,
            productPrice: productPrice,
            priceRegular: priceRegular,
            quantity: quantity
        };
        if (checkProductOnCart(product) == false) {
            cart.push(product);
            sessionStorage.setItem("cart", JSON.stringify(cart));
        }
        else { // 1 2 3
            let list = cart.filter(x => x.productId != product.productId); // 2 3
            let pro = cart.filter(x => x.productId === product.productId);  // 1
            pro[0].quantity +=1; // cap nhat thang 1
            list.push(pro[0]); // push 1 vao 2 3 => 1 2 3
            sessionStorage.setItem("cart", JSON.stringify(list));
        }
//        addElement(productId, priceRegular, productPrice, productName, imgProduct, quantity);
        addItem();
    })
});

function checkProductOnCart(product) {
    if (cart.length == 0) {
        return false;
    }
    let old = cart.filter(x => x.productId === product.productId);
    if (old.length > 0) {
        return true;
    }
    return false;
}

function addItem() {
    document.querySelector(".amm-shopping_cart-list-items ul").innerHTML = cart.map(prod =>
            `<li>
                <div class="amm-single-shopping-cart media">
                        <div class="cart-image">
                            <img src="` + prod.imgProduct + `" alt="Cart">
                        </div>
                        <div class="cart-content media-body pl-15">
                            <input type="hidden" value="` + prod.productId + `" id="productId">
                            <h6><a href="#">` + prod.productName + `</a></h6>
                            <input id="productQuantity" type="number" onChange="changePriceProduct(this)" min="1" style="width: 25px;
                            height: 19px; 
                            font-size: 11px;" value="` + prod.quantity + `" ><br>
                            <span class="price-discount">` + prod.productPrice + `</span><sup>đ</sup>
                            <span class="price-close">` + prod.priceRegular + `</span><sup>đ</sup>
                            <span class="remove" id="` + prod.productId + `"><i class="fas fa-times"></i></span>
                        </div>
                    </div> 
            </li>`
    ).join()
    countProduct();
    cartTotal();
    deleteProduct();

}

function cartTotal() {
    // debugger;
    let cartItem = document.querySelectorAll(".amm-shopping_cart-list-items ul li");
    let sumPay = 0;
    if (cartItem.length > 0) {

        for (let i = 0; i < cartItem.length; i++) {
            let getPrices = cartItem[i].querySelector(".cart-content .price-discount");
            let getPrice = getPrices.innerText;
            let quantityProduct = cartItem[i].querySelector("#productQuantity").value;
            totalB = getPrice * quantityProduct;
            sumPay += totalB;
        }
    }
    let appendSum = document.querySelector(".amm-shopping_cart-btn p span");
    appendSum.innerHTML = sumPay;
    // changePriceProduct();
}
function deleteProduct() {
    let cartItem = document.querySelectorAll(".amm-shopping_cart-list-items ul li");
    for (let i = 0; i < cartItem.length; i++) {
        let iconDele = document.querySelectorAll(".remove");
        iconDele[i].addEventListener("click", function (event) {
            let cartDele = event.target;
            let getProductId = this.id;
            let products = cart.filter(x => x.productId != getProductId);
            sessionStorage.setItem("cart", JSON.stringify(products));
            let cartItemPro = cartDele.parentElement.parentElement.parentElement.parentElement;
            cartItemPro.remove();
            cartTotal();
            countProduct();
            window.location.reload();
        });

    }
}
function changePriceProduct(productItem) {
    let getcart = sessionStorage.getItem("cart");
//    debugger;
    let quantity = productItem.value;
    let sum = 0;
    let getParent = productItem.parentElement;
    let productId = getParent.querySelector("#productId").value;
    let products = cart.filter(x => x.productId === productId);
    if (products.length > 0) {
        let product = products[0];
        // console.log(product);
        product.quantity = quantity; // cập nhật quantity vào carts 
    }

    //lấy tất cả các sản phẩm có trong carts và tính toán tổng giá trị đơn hàng
    cart.forEach(product => {
        let sumProduct = parseInt(product.quantity) * product.productPrice;
        sum += sumProduct;
    });
    let appendSum = document.querySelector(".amm-shopping_cart-btn p");
    //console.log(appendSum);
    appendSum.innerHTML = sum;
    // luu trên sessions
    sessionStorage.setItem("cart", JSON.stringify(cart));
}

//viewCart

//show sp vào giỏ hàng 
function showProduct() {
    loadCart();
    //priceRegular,productPrice,productName,imgProduct
    let payment = '';
    for (let i = 0; i < cart.length; i++) {

        payment += `  <li>
                        <div class="cover-product row">
                            <div class="img-product pl-5">
                                <img src="` + cart[i].imgProduct + `" alt="">
                            </div>
                            <div class="infor-product pl-3">
                                <input type="hidden" value="` + cart[i].productId + `" id="` + cart[i].productId + `">
                                <a href="./productdetail.html">
                                    <h3>` + cart[i].productName + `</h3>
                                </a>
                                <p><strong>Kích thước:</strong> <span>1</span>kg</p>
                                <div class="discount-price"><span>` + cart[i].productPrice + ` </span> <sup>đ</sup> </div>

                            </div>
                            <div class="change-pro">
                                <div class="buttons_added" >
                                <input class="inputQuantity" onChange="updateQuantity(this)"  id='a` + cart[i].productId + `' " min='1' name='quantity' type='number' value='` + cart[i].quantity + `' />
                                
                                <div class="icon-delete" id="` + cart[i].productId + `">
                                    <i class="icon-delete1 fas fa-trash-alt"></i>
                                </div>
                                </div>
                            </div>
                            <div class="sumPro">
                                <span>` + cart[i].productPrice + ` </span>đ
                            </div>

                        </div>
                    </li>`;

    }
    ;
    var mainCart = $("#mainCart");
    if (mainCart && mainCart !== null) {
        $(mainCart)[0].innerHTML = payment;
    }
    // changeQuantity();
    deleteProductViewCart();
    deleteAllProductViewCart();
}

function immidiateSum() {

    let getcart = sessionStorage.getItem("cart");
    let cartMain;
    if (getcart.length > 0) {
        // debugger;
        cartMain = JSON.parse(getcart);
    }
    let getInforProduct = document.querySelectorAll(".displayProduct ul>li");
    let sumPay = 0;
    let sum = 0;
    if (getInforProduct.length > 0) {
        for (let i = 0; i < getInforProduct.length; i++) {
            let getPrice = getInforProduct[i].querySelector("div .discount-price span").innerText;
            let quantityProduct = getInforProduct[i].querySelector(".change-pro .buttons_added .inputQuantity").value;
            totalB = getPrice * quantityProduct;
            sumPay += totalB;

        }
    }
    let sumPrice = document.querySelector(".immidiate-fee span");
    sumPrice.innerHTML = sumPay.toLocaleString("de-DE");

    let sumPriceMain = document.querySelector(".sum-fee span");
    sumPriceMain.innerHTML = sumPay.toLocaleString("de-DE");
    sumShip();
}
function deleteProductViewCart() {
    let cartItem = document.querySelectorAll(".displayProduct ul>li");

    for (let i = 0; i < cartItem.length; i++) {

        let iconDele = document.querySelectorAll(".icon-delete");

        iconDele[i].addEventListener("click", function (event) {
            // debugger;
            let getcart = sessionStorage.getItem("cart");
            let cartMain;
            if (getcart.length > 0) {
                cartMain = JSON.parse(getcart);
            }
            let cartDele = event.target;

            let getProductId = this.id;
            let products = cartMain.filter(x => x.productId != getProductId);
            sessionStorage.setItem("cart", JSON.stringify(products));
            let cartItemPro = cartDele.parentElement.parentElement.parentElement.parentElement.parentElement;

            cartItemPro.remove();
            immidiateSum();
        });

    }


}

function updateQuantity(productPayment) {

    let getcart = sessionStorage.getItem("cart");
    let cartMain;
    if (getcart.length > 0) {
        cartMain = JSON.parse(getcart);
    }
    // debugger;

    let quantity = productPayment.value;
    let sum = 0;
    let getParent = productPayment.parentElement.parentElement.parentElement;
    let productId1 = getParent.querySelector(".infor-product input").value;
    let products = cartMain.filter(x => x.productId === productId1);
    if (products.length > 0) {
        let product = products[0];
        product.quantity = quantity; // cập nhật quantity vào carts 
    }

    //lấy tất cả các sản phẩm có trong carts và tính toán tổng giá trị đơn hàng
    cartMain.forEach(product => {
        let sumProduct = parseInt(product.quantity) * product.productPrice;
        sum += sumProduct;
        sumShip();
    });
    let sumPrice = document.querySelector(".immidiate-fee span");
    sumPrice.innerHTML = sum.toLocaleString("de-DE");
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
    let getInforProduct = document.querySelectorAll(".displayProduct ul>li");
    let temporarySum = document.querySelector(".immidiate-fee  .prices").innerText;
    if (temporarySum >= 50.000 || getInforProduct.length <= 0) {
        ship = 0;
        temporarySum = (parseInt(temporarySum) + parseInt(ship)) * 1000;
    } else {
        ship = priceShip;
        temporarySum = (parseInt(temporarySum) + parseInt(ship)) * 1000;

    }
    shipping.innerHTML = parseInt(ship).toLocaleString("de-DE") * 1000;
    let sumPriceMain = document.querySelector(".sum-fee span");
    sumPriceMain.innerHTML = parseInt(temporarySum).toLocaleString("de-DE");

}

function deleteAllProductViewCart() {
    let cartItem = document.querySelectorAll(".displayProduct ul>li");
//    debugger;
    for (let i = 0; i < cartItem.length; i++) {
        let iconDele = document.querySelector(".delete");
        iconDele.addEventListener("click", function (event) {
            let getcart = sessionStorage.getItem("cart");
            let cartMain;
            if (getcart.length > 0) {
                cartMain = JSON.parse(getcart);
            }
            let cartDele = event.target;
            let getProductId = this.id;
            let products = cartMain.filter(x => x.productId != getProductId);
            sessionStorage.setItem("cart", JSON.stringify(products));
            let coverCart = cartDele.parentElement.parentElement;
            let cartItemPro = coverCart.querySelector("#mainCart");
            cartItemPro.remove();
            immidiateSum();
        });
    }
}
// shopping cart main 

btnAddtoCart.forEach(function (value, i) {
    value.addEventListener("click", function (event) {
//        debugger;
        let btnAdd = event.target;
        let getInforProduct = btnAdd.parentElement.parentElement.parentElement.parentElement;
        duplication += 1;
        let productId = getInforProduct.querySelector("#productId").value;
        let imgProduct = getInforProduct.querySelector(".shop-details-thumb-slider-active .item img").src;
        let productName = getInforProduct.querySelector(".shop-product-details-content h2").innerText;
        let productPrice = getInforProduct.querySelector(".shop-product-details-content .pricing .discount-price").innerText;
        let priceRegular = getInforProduct.querySelector(".shop-product-details-content .pricing .regular-price").innerHTML;
        let quantity = getInforProduct.querySelector(".product-quantity input").value;
        let products = {
            productId: productId,
            imgProduct: imgProduct,
            productName: productName,
            productPrice: productPrice,
            priceRegular: priceRegular,
            quantity: quantity
        };
        cart.push(products);
        countProduct();
        sessionStorage.setItem("cart", JSON.stringify(cart));
//        addElement(productId, priceRegular, productPrice, productName, imgProduct, quantity);
    })

})

function countProduct() {
    let countPro = cart.length;
    getNumberShoppingCart.innerHTML = countPro;
}

function changePriceProductMain(productItem) {
    let getcart = sessionStorage.getItem("cart");
    let cartMain = JSON.parse(getcart);
//    
//    debugger;
    let quantity = productItem.value;
    let sum = 0;
    let getParent = productItem.parentElement;
    let productId = getParent.querySelector("#productId").value;
    let products = cart.filter(x => x.productIdMain === productId);
    if (products.length > 0) {
        let product = products[0];
        product.quantityMain = quantity; // cập nhật quantity vào carts
    }
    //lấy tất cả các sản phẩm có trong carts và tính toán tổng giá trị đơn hàng
    cart.forEach(product => {
        let sumProduct = parseInt(product.quantityMain) * product.productPriceMain;
        sum += sumProduct;
    });
    let appendSum = document.querySelector(".amm-shopping_cart-btn p");
    appendSum.innerHTML = sum;
    // luu trên sessions
    sessionStorage.setItem("cart", JSON.stringify(products));
}

function showProductMain() {
    let payment = "";
    for (let i = 0; i < cart.length; i++) {
        payment += `  <li>
                        <div class="cover-product row">
                            <div class="img-product pl-5">
                                <img src="` + cart[i].imgProductMain + `" alt="">
                            </div>
                            <div class="infor-product pl-3">
                                <input type="hidden" value="` + cart[i].productIdMain + `" id="` + cart[i].productIdMain + `">
                                <a href="./productdetail.html">
                                    <h3>` + cart[i].productNameMain + `</h3>
                                </a>
                                <p><strong>Kích thước:</strzong> <span>1</span>kg</p>
                                <div class="discount-price"><span>` + cart[i].productPriceMain + ` </span> <sup>đ</sup> </div>

                            </div>
                            <div class="change-pro">
                                <div class="buttons_added" >
                                <input class="inputQuantity" onChange="updateQuantity(this)"  id='` + cart[i].productIdMain + `' " min='1' name='quantity' type='number' value='` + cart[i].quantityMain + `' />
                                
                                <div class="icon-delete" id="` + cart[i].productIdMain + `">
                                    <i class="icon-delete1 fas fa-trash-alt"></i>
                                </div>
                                </div>
                            </div>
                            <div class="sumPro">
                                <span>` + cart[i].productPriceMain + ` </span><sup>đ</sup>
                            </div>

                        </div>
                    </li>`;

    }
    ;

    var mainCart = $("#mainCart");
    if (mainCart && mainCart !== null) {
        $(mainCart)[0].innerHTML = payment;
    }
    // changeQuantity();
    deleteProductViewCart();
    deleteAllProductViewCart();
}
//payment
//show sp vào thanh toán
function showProductToPayment() {
    loadCart();
    let payment = "";

    //priceRegular,productPrice,productName,imgProduct
    for (let i = 0; i < cart.length; i++) {

        payment += `  <li>
        <div class="cover-product-pay row pb-3">
            <div class="img-product-payment pl-5 col-4 ">
                <img src="` + cart[i].imgProduct + `" width="50px" height="50px" alt="">
                <span class="quantityPayment ">` + cart[i].quantity + `</span>
            </div>
            <div class="infor-product col-4 ">
                <p href="./productdetail.html" >
                    <h5 style="font-size: 14px;">` + cart[i].productName + `</h5>1kg 
                </p>
            </div>
            <div class="price-product-pay pl-3 col-4 pt-25">
            <span class="discount-price-pay">` + cart[i].productPrice + ` </span><sup>đ</sup>
            </div>
        </div>
    </li>`;
        ;

    }

    var mainCart = $("#mainCartPayment");
    if (mainCart && mainCart !== null) {
        $(mainCart)[0].innerHTML = payment;
        // immidiateSum();
    }
    debugger;
    immidiateSum_Payment();
    sumShipPayment();
}

function immidiateSum_Payment() {

    loadCart();
    let getInforProduct = document.querySelectorAll(".showProduct ul>li");
    let sumPayment = 0;
    if (cart.length > 0) {
        for (let i = 0; i < cart.length; i++) {
            let getPrice = cart[i].productPrice;
            let quantityProduct = cart[i].quantity;
            totalB = getPrice * quantityProduct;
            sumPayment += totalB;
        }
    }
    let sumPrice = document.querySelector(".sumPriceproduct .padding-price1");
    sumPrice.innerHTML = sumPayment.toLocaleString("de-DE");

    let sumPriceMain = document.querySelector(".sumarizePrice .padding-price");
    sumPriceMain.innerHTML = sumPayment.toLocaleString("de-DE");


}

function sumShipPayment() {
    let getcart = sessionStorage.getItem("cart");
    let cartMain = JSON.parse(getcart);
    let shipping = document.querySelector(".sumPriceproduct .padding-price2")
    let ship = shipping.innerText;
    let priceShip = 20.000;
    let getInforProduct = document.querySelectorAll("#mainCartPayment li");
    let temporarySum = document.querySelector(".sumPriceproduct  .padding-price1").innerText;
    if (temporarySum >= 50.000 || getInforProduct.length <= 0) {
        ship = 0;
        temporarySum = (parseInt(temporarySum) + parseInt(ship)) * 1000;
    } else {
        ship = priceShip;
        temporarySum = (parseInt(temporarySum) + parseInt(ship)) * 1000;
    }
    shipping.innerHTML = ship * 1000;
    let sumPriceMain = document.querySelector(".sumarizePrice .padding-price");
    sumPriceMain.innerHTML = parseInt(temporarySum - discount).toLocaleString("de-DE");

}

// promotion discount
let promotionCode = {
    A: 10,
    B: 20,
    C: 30,
    D: 40,
};
let inputPromotion = document.querySelector('#promo-code');

function checkPromotion() {
    let value = inputPromotion.value;
    console.log(value);
    if (promotionCode[value]) {
        return promotionCode[value];
    }
    return 0;
}
let discount = document.querySelector('.discount');
let discountEle = document.querySelector('.sumPriceproduct .padding-price3');

// Cập nhật tổng tiền
function updateTotalMoney() {
    loadCart();
    let totalMoney = 0;
    let discountMoney = 0;
    let sumTemporary = document.querySelector(".sumPriceproduct .padding-price1").innerHTML;
    let sum = document.querySelector(".sumarizePrice  .padding-price").innerHTML;
    // Có mã giảm giá hay không?
    // Mã giảm giá có hợp lệ hay không?
    let data = checkPromotion();
    if (data) {
        discountMoney = ((sumTemporary * data * 1000) / 100).toLocaleString("de-DE");
    }
    discountEle.innerText = discountMoney;
    let sum1 = (parseInt(sum) - discountMoney) * 1000;
    document.querySelector(".sumarizePrice .padding-price").innerHTML = sum1.toLocaleString("de-DE");
}
let btnPromotion = document.querySelector('.applyDiscount button');

btnPromotion.addEventListener('click', function () {
    debugger;
    updateTotalMoney();
});

function checkProduct() {
    let getcart = sessionStorage.getItem("cart");
    let cartMain = JSON.parse(getcart);
    showProductToPayment();
    let checkPro = document.getElementById("mainCartPayment");
    let checkProLi = document.querySelector("#mainCartPayment li");
    console.log(checkPro);
    if (cartMain.length > 3) {
        checkPro.style.height = '400px';
    } else if (cartMain.length == 0) {
        checkPro.style.display = 'none';
    }
}
