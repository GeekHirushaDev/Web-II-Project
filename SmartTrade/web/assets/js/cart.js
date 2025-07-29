async function loadCartItems() {
    const popup = new Notification();
    const response = await fetch("LoadCartItems");
    if (response.ok) {
        const json = await response.json();
        if (json.status) {
            const cart_item_container = document.getElementById("cart-item-container");
            cart_item_container.innerHTML = "";

            let total = 0;
            let totalQty = 0;
            json.cartItems.forEach(cart => {
                let productSubTotal = cart.product.price * cart.qty;
                total += productSubTotal;
                totalQty += cart.qty;
                let tableData = `<tr id="cart-item-row">
                                        <td class="product-remove"><a href="#" class="remove-wishlist"><i class="fal fa-times"></i></a></td>
                                        <td class="product-thumbnail">
                                            <a href="#">
                                                <img src="product-images\\${cart.product.id}\\image1.png" alt="Product">
                                            </a>
                                        </td>
                                        <td class="product-title"><a href="#">${cart.product.title}</a></td>
                                        <td class="product-price" data-title="Price">
                                        <span class="currency-symbol">Rs. </span>
                                            <span>${new Intl.NumberFormat("en-US",
                        {minimumFractionDigits: 2})
                        .format(cart.product.price)}
                                            </span>
                                        </td>
                                        <td class="product-quantity" data-title="Qty">
                                            <div class="pro-qty">
                                                <input type="number" class="quantity-input" value="${cart.qty}">
                                            </div>
                                        </td>
                                        <td class="product-subtotal" data-title="Subtotal"><span class="currency-symbol">Rs. </span>
                                        <span>
                                            ${new Intl.NumberFormat("en-US",
                        {minimumFractionDigits: 2})
                        .format(productSubTotal)}
                                        </span>
                                        </td>
                                    </tr>
                                    `;
                cart_item_container.innerHTML += tableData;
            });
            document.getElementById("order-total-quantity").innerHTML = totalQty;
            document.getElementById("order-total-amount").innerHTML = new Intl.NumberFormat("en-US",
                    {minimumFractionDigits: 2})
                    .format(total);
        } else {
            popup.error({
                message: json.message
            });
        }
    } else {
        popup.error({
            message: "Cart Items loading failed..."
        });
    }
}