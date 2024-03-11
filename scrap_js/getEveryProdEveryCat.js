
const SaveFile = require('./SaveFile.js');
const getEveryProduct = require('./getEveryProduct.js');

const categorias = [
	{name: 'Aceite, especias y salsas', url: 'https://tienda.mercadona.es/categories/112'},
	{name: 'Agua y refrescos', url: 'https://tienda.mercadona.es/categories/156'},
	{name: 'Aperitivos', url: 'https://tienda.mercadona.es/categories/135'},
	{name: 'Arroz, legumbres y pasta', url: 'https://tienda.mercadona.es/categories/118'},
	{name: 'Azúcar, caramelos y chocolate', url: 'https://tienda.mercadona.es/categories/89'},
	{name: 'Bebé', url: 'https://tienda.mercadona.es/categories/216'},
	{name: 'Bodega', url: 'https://tienda.mercadona.es/categories/164'},
	{name: 'Cacao, café e infusiones', url: 'https://tienda.mercadona.es/categories/86'},
	{name: 'Carne', url: 'https://tienda.mercadona.es/categories/46'},
	{name: 'Cereales y galletas', url: 'https://tienda.mercadona.es/categories/78'},
	{name: 'Charcutería y quesos', url: 'https://tienda.mercadona.es/categories/48'},
	{name: 'Congelados', url: 'https://tienda.mercadona.es/categories/147'},
	{name: 'Conservas, caldos y cremas', url: 'https://tienda.mercadona.es/categories/122'},
	{name: 'Cuidado del cabello', url: 'https://tienda.mercadona.es/categories/201'},
	{name: 'Cuidado facial y corporal', url: 'https://tienda.mercadona.es/categories/192'},
	{name: 'Fitoterapia y parafarmacia', url: 'https://tienda.mercadona.es/categories/213'},
	{name: 'Fruta y verdura', url: 'https://tienda.mercadona.es/categories/27'},
	{name: 'Huevos, leche y mantequilla', url: 'https://tienda.mercadona.es/categories/77'},
	{name: 'Limpieza y hogar', url: 'https://tienda.mercadona.es/categories/226'},
	{name: 'Maquillaje', url: 'https://tienda.mercadona.es/categories/206'},
	{name: 'Marisco y pescado', url: 'https://tienda.mercadona.es/categories/32'},
	{name: 'Mascotas', url: 'https://tienda.mercadona.es/categories/222'},
	{name: 'Panadería y pastelería', url: 'https://tienda.mercadona.es/categories/65'},
	{name: 'Pizzas y platos preparados', url: 'https://tienda.mercadona.es/categories/897'},
	{name: 'Postres y yogures', url: 'https://tienda.mercadona.es/categories/105'},
	{name: 'Zumos', url: 'https://tienda.mercadona.es/categories/99'},
];

async function main() {
	let fullProducts = [];

	const saveFile = new SaveFile('productos.json');

	for (let c of categorias) {
		const p = await getEveryProduct(c);
		
		fullProducts = fullProducts.concat(p);
		console.log(categorias.indexOf(c) + 1, 'de', categorias.length, 'secciones completadas');
		saveFile.guardar(p);
	}
}

main()