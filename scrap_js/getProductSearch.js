
const playwright = require("playwright"); 
const readline = require('readline');
const getEveryProduct = require('./getEveryProduct.js');


function sleep(ms) {
	return new Promise((resolve) => setTimeout(resolve, ms));
}

async function getEveryProductQuery(queryStr, headless = false) { 
	// Launch the headless browser 
	const browser = await playwright.chromium.launch({ 
		headless: headless, 
	}); 
	const baseLink = 'https://tienda.mercadona.es/search-results?query='
	const fullLink = baseLink + encodeURIComponent(queryStr)
	console.log(fullLink)
	const products = await getEveryProduct(fullLink);

} 
 

async function main() {
	const rl = readline.createInterface({
		input: process.stdin,
		output: process.stdout
	});
	
	rl.question('Busque un producto: ', async (inputString) => {
		await getEveryProductQuery(inputString.trim(), true);
		// console.log('Cadena en mayúsculas:', mayusculas);
		
		rl.close();
	});
}

main();
