$(document).ready(function () {
    calculateTotalPrice()
    conditionallyRenderEmptyCartMessage()

    // Increment quantity
    $('.dec-count-button').on('click', function () {
        let $cartItem = $(this).closest('.cart-item');
        let itemId = $cartItem.data('item-id');
        let $quantityElement = $cartItem.find('h3');

        let currentQuantity = parseInt($quantityElement.text(), 10);
        $quantityElement.text(currentQuantity + 1);
        updateCartQuantity(itemId, 'increment', $quantityElement, currentQuantity, $cartItem);
    });

    // Decrement quantity
    $('.inc-count-button').on('click', function () {
        let $cartItem = $(this).closest('.cart-item');
        let itemId = $cartItem.data('item-id');
        let $quantityElement = $cartItem.find('h3');

        let currentQuantity = parseInt($quantityElement.text(), 10);
        $quantityElement.text(currentQuantity - 1);
        updateCartQuantity(itemId, 'decrement', $quantityElement, currentQuantity, $cartItem);
    });

    // Update cart quantity in the database
    function updateCartQuantity(itemId, action, $quantityElement, previousQuantity, $cartItem) {
        const cartId = $('.cart-list').data('cart-id');
        $.ajax({
            url: `/api/cart/${cartId}/updateQuantity/${itemId}/${action}`,
            method: 'POST',
            success: function (response) {
                calculateTotalPrice()
                if(action === 'decrement' && previousQuantity === 1) {
                    $cartItem.remove();
                }
                conditionallyRenderEmptyCartMessage()
            },
            error: function (error) {
                // Rollback UI update on error
                $quantityElement.text(previousQuantity);
                alert(error.responseText);
            }
        });
    }

    function calculateTotalPrice() {
        let totalPrice = 0;
        $('.cart-item').each(function () {
            let price = parseFloat($(this).find('.price').text().replace('$', '')); // Assuming price is in $ format
            let quantity = parseInt($(this).find('h3').text(), 10);
            totalPrice += price * quantity;
        });
        $('#total-price').text(totalPrice.toFixed(2));
    }

    function conditionallyRenderEmptyCartMessage() {
        let itemCount = 0;
        $('.cart-item').each(function () {
            let quantity = parseInt($(this).find('h3').text(), 10);
            itemCount += quantity;
        });

        // Show or hide the "empty cart" message based on the item count
        if (itemCount === 0) {
            $('#empty-cart-message').show();
            $('#checkout-link').hide();
        } else {
            $('#empty-cart-message').hide();
            $('#checkout-link').show();
        }
    }

});
