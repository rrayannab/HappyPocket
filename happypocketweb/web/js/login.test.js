// login.test.js

// Incluir el polyfill
const { TextEncoder, TextDecoder } = require('util');
global.TextEncoder = TextEncoder;
global.TextDecoder = TextDecoder;

const { JSDOM } = require('jsdom');

const html = `
<!DOCTYPE html>
<html lang="es">
<body>
    <form id="loginForm" onsubmit="login(this); return false;">
        <div class="styled-input agile-styled-input-top">
            <input type="text" placeholder="Nombre de usuario" name="Name" required="">
        </div>
        <div class="styled-input">
            <input type="password" placeholder="Contraseña" name="password" required="">
        </div>
        <input type="submit" value="Iniciar Sesión" class="btn">
    </form>
    <div id="loginMessage"></div>
    <script>
        function login(form) {
            const username = form.Name.value;
            const password = form.password.value;
            if (username === 'testuser' && password === 'password') {
                document.getElementById('loginMessage').innerText = 'Login Successful';
            } else {
                document.getElementById('loginMessage').innerText = 'Login Failed';
            }
        }
    </script>
</body>
</html>
`;

let dom;
let document;

beforeEach(() => {
    dom = new JSDOM(html, { runScripts: "dangerously" });
    document = dom.window.document;
});

test('successful login', () => {
    const form = document.getElementById('loginForm');
    form.Name.value = 'testuser';
    form.password.value = 'password';
    form.dispatchEvent(new dom.window.Event('submit'));

    const message = document.getElementById('loginMessage').innerText;
    expect(message).toBe('Login Successful');
});

test('failed login', () => {
    const form = document.getElementById('loginForm');
    form.Name.value = 'wronguser';
    form.password.value = 'wrongpassword';
    form.dispatchEvent(new dom.window.Event('submit'));

    const message = document.getElementById('loginMessage').innerText;
    expect(message).toBe('Login Failed');
});

test('guest login', () => {
    const button = document.querySelector('button[onclick="guestLogin()"]');
    button.click();

    const message = document.getElementById('guestLoginMessage').innerText;
    expect(message).toBe('Guest Login Successful');
});
