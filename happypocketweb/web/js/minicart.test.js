const { JSDOM } = require('jsdom');

describe('minicart', () => {
    let dom;
    let container;

    beforeEach(() => {
        dom = new JSDOM(`<!DOCTYPE html><body><script src="js/minicart.js"></script></body></html>`, { runScripts: 'dangerously' });
        container = dom.window.document;
    });

    it('decreases quantity when decrease button is clicked', () => {
        // Set up your document with the necessary HTML
        container.body.innerHTML = `
            <button class="minicartk-quantity-decrease" data-minicartk-idx="0"></button>
            <input class="minicartk-quantity" data-minicartk-idx="0" value="2">
        `;

        // Mock the cart
        dom.window.paypalm = {
            minicartk: {
                cart: {
                    items: () => [{
                        set: jest.fn(),
                    }],
                },
            },
        };

        // Simulate a click event
        container.querySelector('.minicartk-quantity-decrease').click();

        // Check that the quantity was decreased
        expect(container.querySelector('.minicartk-quantity').value).toBe('1');
        expect(dom.window.paypalm.minicartk.cart.items()[0].set).toHaveBeenCalledWith('quantity', 1);
    });

    // You can add more tests for the other functionality in your script
});