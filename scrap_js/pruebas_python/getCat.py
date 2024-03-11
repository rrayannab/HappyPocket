import time
from selenium_stealth import stealth
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import Select

def get_every_product(url, headless=False):
	# webDriv = getPath.getPath() + "chromedriver"
	driver = webdriver

	options = webdriver.ChromeOptions()
	options.add_argument("start-minimized")
	options.add_argument("log-level=3")	# oculta los mensajes de error
	if headless: options.add_argument("--headless")

	# options.add_experimental_option("excludeSwitches", ["enable-automation"])
	# options.add_experimental_option('useAutomationExtension', False)
	
	# if getPath.isWindows():
	# 	self.driver = webdriver.Chrome(options=options)
	# else:
	# 	service = webdriver.ChromeService(executable_path = '/usr/lib/chromium-browser/chromedriver')
	# 	self.driver = webdriver.Chrome(options=options, service=service)
	
	browser = webdriver.Chrome(options=options)

	stealth(browser,
			languages=["en-US", "en"],
			vendor="Google Inc.",
			platform="Win32",
			webgl_vendor="Intel Inc.",
			renderer="Intel Iris OpenGL Engine",
			fix_hairline=True,
			)
	
	salir = False
	products = []

	try:
		browser.get(url)

		try:
			postal_code_button = browser.find_elements(By.LINK_TEXT, 'Continuar')
			if len(postal_code_button) > 0:
				postal_code_input = browser.find_element(By.LINK_TEXT, 'Código postal')
				postal_code_input.send_keys('28001')
				postal_code_button[0].click()
				time.sleep(0.5)
		except Exception as e:
			print('Error1:', e)

	# 	while not salir:
	# 		browser.execute_script("window.scrollTo(0, document.body.scrollHeight);")

	# 		tags = browser.find_elements_by_css_selector(".product-container > .product-cell")

	# 		for tag in tags:
	# 			title = tag.find_element_by_css_selector('.subhead1-r.product-cell__description-name').text
	# 			img_url = tag.find_element_by_css_selector('img').get_attribute('src')
	# 			price = tag.find_element_by_css_selector('.product-price').text.split('€')[0]

	# 			products.append({'title': title, 'price': price, 'imgUrl': img_url})

	# 		next_button = browser.find_elements_by_css_selector('.category-detail__next-subcategory')

	# 		if next_button:
	# 			next_button[0].click()
	# 			print('next page')
	# 		else:
	# 			salir = True
	# 			print('no more pages')

	# 		time.sleep(0.5)

	# 	print('Seccion terminada')

	except Exception as e:
		print(e)

	finally:
		browser.quit()

	# return products
	return []


catUrl = [
	'https://tienda.mercadona.es/categories/112',
	# 'https://tienda.mercadona.es/categories/156',
	# 'https://tienda.mercadona.es/categories/135',
	# 'https://tienda.mercadona.es/categories/118',
	# 'https://tienda.mercadona.es/categories/89',
	# 'https://tienda.mercadona.es/categories/216',
	# 'https://tienda.mercadona.es/categories/164',
	# 'https://tienda.mercadona.es/categories/86',
	# 'https://tienda.mercadona.es/categories/46',
	# 'https://tienda.mercadona.es/categories/78',
	# 'https://tienda.mercadona.es/categories/48',
	# 'https://tienda.mercadona.es/categories/147',
	# 'https://tienda.mercadona.es/categories/122',
	# 'https://tienda.mercadona.es/categories/201',
	# 'https://tienda.mercadona.es/categories/192',
	# 'https://tienda.mercadona.es/categories/213',
	# 'https://tienda.mercadona.es/categories/27',
	# 'https://tienda.mercadona.es/categories/77',
	# 'https://tienda.mercadona.es/categories/226',
	# 'https://tienda.mercadona.es/categories/206',
	# 'https://tienda.mercadona.es/categories/32',
	# 'https://tienda.mercadona.es/categories/222',
	# 'https://tienda.mercadona.es/categories/65',
	# 'https://tienda.mercadona.es/categories/897',
	# 'https://tienda.mercadona.es/categories/105',
	# 'https://tienda.mercadona.es/categories/99'
]

for u in catUrl:
	print(get_every_product(u))
	print('----------------------')