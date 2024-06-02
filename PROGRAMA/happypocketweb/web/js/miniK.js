

// minicart.js
export function decreaseQuantity(idx) {
    var input = document.querySelector('.minicartk-quantity[data-minicartk-idx="' + idx + '"]');
    var currentQuantity = parseInt(input.value, 10);
    if (currentQuantity > 1) { // Asegúrate de que la cantidad nunca sea menor que 1
        input.value = currentQuantity - 1;
        paypalm.minicartk.cart.items()[idx].set('quantity', currentQuantity - 1); // Actualiza la cantidad en el carrito
    }
}

export function increaseQuantity(idx) {
    var input = document.querySelector('.minicartk-quantity[data-minicartk-idx="' + idx + '"]');
    var currentQuantity = parseInt(input.value, 10);
    input.value = currentQuantity + 1;
    paypalm.minicartk.cart.items()[idx].set('quantity', currentQuantity + 1); // Actualiza la cantidad en el carrito
}

export function setupEventListeners() {
    document.addEventListener('click', function(event) {
        var button = event.target;
        if (button.classList.contains('minicartk-quantity-decrease')) {
            var idx = button.getAttribute('data-minicartk-idx');
            decreaseQuantity(idx);
        } else if (button.classList.contains('minicartk-quantity-increase')) {
            var idx = button.getAttribute('data-minicartk-idx');
            increaseQuantity(idx);
        }
    });
}