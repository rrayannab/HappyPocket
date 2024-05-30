// minicart.test.js
var minicart = require('./minicart');

describe('minicart', () => {
    beforeEach(() => {
        // Configura el DOM y el carrito de compras antes de cada prueba
        document.body.innerHTML = `
            <input class="minicartk-quantity" data-minicartk-idx="0" value="2">
        `;
        window.paypalm = {
            minicartk: {
                cart: {
                    items: () => [{
                        set: jest.fn()
                    }]
                }
            }
        };
        minicart.initialize(); // Llama a initialize antes de cada prueba
    });

    test('decreaseQuantity', () => {
        minicart.decreaseQuantity(0);
        expect(document.querySelector('.minicartk-quantity').value).toBe('1');
        expect(window.paypalm.minicartk.cart.items()[0].set).toHaveBeenCalledWith('quantity', 1);
    });

    test('increaseQuantity', () => {
        minicart.increaseQuantity(0);
        expect(document.querySelector('.minicartk-quantity').value).toBe('3');
        expect(window.paypalm.minicartk.cart.items()[0].set).toHaveBeenCalledWith('quantity', 3);
    });
});