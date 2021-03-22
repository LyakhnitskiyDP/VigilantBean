$(document).ready(function() {

    refreshCart();

});

function refreshCart() {
    $.ajax({
        type: 'GET',
        url: 'api/cart/getCart',
        success: function(data) {
            initCartTable(data);
        },
        error: function(data) {
            console.log(data);
        }
    });
}

function initCartTable(cart) {

    $('#grantTotalValue').html(cart.grandTotal ?? '0');

    initCartItems(cart.cartItems);

    if (cart.cartItems.length > 0) {
        initDiscardButton();
        initQuantityCounter();
    }
}

function initCartItems(cartItems) {

    const cartTableBody = $('#cartTableBody');


    cartTableBody.html('');
    if (cartItems.length < 1)
        $('#cart').html(initEmptyRaw());

    for (const cartItem of cartItems) {
       cartTableBody.append(
        initItemRaw(cartItem)
       );
    }

    function initItemRaw(cartItem) {

        return `
             <tr>
               <td class="productName"><img src="resources/images/products/${cartItem.product.mainPicture.fullName}"/>
               <span>${cartItem.product.name}</span></td>
               <td>${cartItem.product.unitPrice}</td>
               <td>
                 <form>
                   <input class="quantityCounter" type="number" min="1" max="9999"
                    name="quantity" value="${cartItem.quantity}" />
                   <input type="hidden" name="cartItemId" value="${cartItem.cartItemId}" />
                   <span class="discard">&times;</span>
                 </form>
               </td>
               <td>${cartItem.total}</td>
             </tr>
        `;
    }

    function initEmptyRaw() {

        return '<h2>Empty Cart</h2>';
    }

}

function initDiscardButton() {
    $('.discard').click(function(event) {

        const form = event.target.parentElement;
        const cartItemId = form[1].getAttribute('value');

        $.ajax({
            type: 'POST',
            url: 'api/cart/removeCartItem',
            data: { cartItemId : cartItemId },
            success: function(data) {
                refreshCart();
            },
            error: function(data) {
                console.log(data);
            }
        });

    });
}

function initQuantityCounter() {
    $('.quantityCounter').on('input', function(event) {

        const form = event.target.parentElement;

        const cartItemId = form[1].getAttribute('value');
        const quantity = $(this).val();
        $(this).attr('value', quantity);

        $.ajax({
            type: 'PUT',
            url: 'api/cart/updateCartItem',
            data: {
                    cartItemId : cartItemId,
                    quantity : quantity
                  },
            success: function(data) {
                refreshCart();
            },
            error: function(data) {
                console.log(data);
            }
        });

    });
}
