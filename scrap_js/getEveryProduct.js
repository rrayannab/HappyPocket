const playwright = require("playwright");

function sleep(ms) {
	return new Promise((resolve) => setTimeout(resolve, ms));
}

async function getEveryProduct(categoria) {
	url = categoria.url;
	let salir = false;
	const products = [];

	const browser = await playwright.chromium.launch({ 
		headless: false, 
	}); 

	const page = await browser.newPage(); 
	try {
		
		await page.goto(url);
	} catch (error) {
		console.log('Error1', error);
	}

	try {
		
		
		const postalCode = await page.getByText('Continuar').all();
		if (postalCode.length > 0) {
			await page.getByLabel('Código postal').fill('28001');
			await page.getByLabel('Continuar').click()
			await sleep(3000);
		}
	
		while (!salir) {
			
			await page.locator('.footer').scrollIntoViewIfNeeded();
			// await page.evaluate(() => window.scrollTo(0, document.body.scrollHeight));
			
			const tags = await page.locator(".product-container > .product-cell").all();
			
			for (let tag of tags) { 
				const title = await tag.locator('.subhead1-r.product-cell__description-name').first().innerText();
				
				let timeout = 0;
				let imgUrl = await tag.locator('img').first().getAttribute('src');
				while (timeout < 5){
					if (!imgUrl.includes('https://prod')) {
						await sleep(500);
						await tag.locator('img').scrollIntoViewIfNeeded();
						imgUrl = await tag.locator('img').first().getAttribute('src')	;
					}else{
						break;
					}
					timeout += 1;
				}
				const price = (await tag.locator('.product-price').first().innerText()).split('€')[0];
				
				// console.log({title: title, price: price, imgUrl: imgUrl});
				products.push({title: title, price: price, imgUrl: imgUrl, categoria: categoria});
			} 
			
			const nextButton = await page.locator('.category-detail__next-subcategory').all();
			if (nextButton.length > 0) {
				await page.locator('.category-detail__next-subcategory').click();
				console.log('Siguiente Pagina.');
			} else {
				salir = true;
			}
			await sleep(500);
		}
		console.log('Seccion terminada.');
		await browser.close(); 
		
	} catch (error) {
		console.log(error);
	}
	return products;
}

module.exports = getEveryProduct;