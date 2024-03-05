
const playwright = require("playwright"); 
const readline = require('readline');


function sleep(ms) {
	return new Promise((resolve) => setTimeout(resolve, ms));
}

async function getInfoMercadona(queryStr) { 
	// Launch the headless browser 
	const browser = await playwright.chromium.launch({ 
		headless: false, 
	}); 
	const baseLink = 'https://tienda.mercadona.es/search-results?query='
	const fullLink = baseLink + encodeURIComponent(queryStr)
	console.log(fullLink)

	const page = await browser.newPage(); 
	// await page.goto("https://tienda.mercadona.es/search-results?query=Refresco%20de%20cola%20zero%20Coca-Cola%20lata%2033%20cl"); 
	await page.goto(fullLink);
	
	await page.getByLabel('Código postal').fill('28001');
	await page.getByLabel('Continuar').click()

	await page.waitForSelector('.subhead1-r.product-cell__description-name');

	const data = []; 
	const tags = await page.locator(".product-container > .product-cell").all();

	for (let tag of tags) { 
		// const title = await tag.locator('.subhead1-r.product-cell__description-name').first().innerText();
		// data.push(title);
		
		const title = await tag.locator('.subhead1-r.product-cell__description-name').first().innerText();
		// await tag.waitForSelector('img');
		const imgUrl = await tag.locator('img').first().getAttribute('src');
		const price = (await tag.locator('.product-price').first().innerText()).split('€')[0];
		data.push({title: title, price: price, imgUrl: imgUrl});
	} 
 
	console.log(data); 
	console.log(data.length);
	// await sleep(500);

	await browser.close(); 
} 
 

async function main() {
	const rl = readline.createInterface({
		input: process.stdin,
		output: process.stdout
	});
	
	rl.question('Busque un producto: ', async (inputString) => {
		await getInfoMercadona(inputString.trim());
		// console.log('Cadena en mayúsculas:', mayusculas);
		
		rl.close();
	});
}

main();
