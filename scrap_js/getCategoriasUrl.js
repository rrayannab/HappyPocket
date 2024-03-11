
const playwright = require("playwright");


async function getCategoriasUrls() { 

	cat = [
		'Aceite, especias y salsas',
		'Agua y refrescos',
		'Aperitivos',
		'Arroz, legumbres y pasta',
		'Azúcar, caramelos y chocolate',
		'Bebé',
		'Bodega',
		'Cacao, café e infusiones',
		'Carne',
		'Cereales y galletas',
		'Charcutería y quesos',
		'Congelados',
		'Conservas, caldos y cremas',
		'Cuidado del cabello',
		'Cuidado facial y corporal',
		'Fitoterapia y parafarmacia',
		'Fruta y verdura',
		'Huevos, leche y mantequilla',
		'Limpieza y hogar',
		'Maquillaje',
		'Marisco y pescado',
		'Mascotas',
		'Panadería y pastelería',
		'Pizzas y platos preparados',
		'Postres y yogures',
		'Zumos'
	  ]

	const catUrl = [
		'https://tienda.mercadona.es/categories/112',
		'https://tienda.mercadona.es/categories/156',
		'https://tienda.mercadona.es/categories/135',
		'https://tienda.mercadona.es/categories/118',
		'https://tienda.mercadona.es/categories/89',
		'https://tienda.mercadona.es/categories/216',
		'https://tienda.mercadona.es/categories/164',
		'https://tienda.mercadona.es/categories/86',
		'https://tienda.mercadona.es/categories/46',
		'https://tienda.mercadona.es/categories/78',
		'https://tienda.mercadona.es/categories/48',
		'https://tienda.mercadona.es/categories/147',
		'https://tienda.mercadona.es/categories/122',
		'https://tienda.mercadona.es/categories/201',
		'https://tienda.mercadona.es/categories/192',
		'https://tienda.mercadona.es/categories/213',
		'https://tienda.mercadona.es/categories/27',
		'https://tienda.mercadona.es/categories/77',
		'https://tienda.mercadona.es/categories/226',
		'https://tienda.mercadona.es/categories/206',
		'https://tienda.mercadona.es/categories/32',
		'https://tienda.mercadona.es/categories/222',
		'https://tienda.mercadona.es/categories/65',
		'https://tienda.mercadona.es/categories/897',
		'https://tienda.mercadona.es/categories/105',
		'https://tienda.mercadona.es/categories/99'
	]

	// Launch the headless browser 
	const browser = await playwright.chromium.launch({ 
		headless: false, 
	}); 
	// const baseLink = 'https://tienda.mercadona.es/search-results?query='
	// const fullLink = baseLink + encodeURIComponent(queryStr)
	// console.log(fullLink)

	const page = await browser.newPage(); 
	await page.goto("https://tienda.mercadona.es/categories/"); 
	// await page.goto(fullLink);
	
	await page.getByLabel('Código postal').fill('28001');
	await page.getByLabel('Continuar').click()

	// await page.waitForSelector('.category-menu');

	// const titles = []; 
	// const buttons = await page.locator(".category-menu").locator('button').all();
	// for (let bt of buttons) { 
	// 	const title = await bt.innerText();
	// 	// await bt.click();
	// 	// const title = page.url();
	// 	titles.push(title);
		
	// 	// const title = await tag.locator('.subhead1-r.product-cell__description-name').first().innerText();
	// 	// // await tag.waitForSelector('img');
	// 	// const imgUrl = await tag.locator('img').first().getAttribute('src');
	// 	// const price = (await tag.locator('.product-price').first().innerText()).split('€')[0];
	// 	// data.push({title: title, price: price, imgUrl: imgUrl});
	// } 

	const urls = [];
	for (let t of cat) {
		await page.locator("button", { has: page.getByText(t) }).click();

		// await page.getByText(t).locator('button').first().click();
		console.log(page.url());
		// await page.getByText(t).locator('button').click();
		// urls.push(page.url());
	}
 
	console.log(urls); 
	await sleep(5000);

	await browser.close(); 
} 
