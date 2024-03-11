import time
from selenium_stealth import stealth
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import Select
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import WebDriverWait




from selenium.common.exceptions import NoSuchElementException
from selenium.common.exceptions import StaleElementReferenceException
from selenium.webdriver.support import expected_conditions


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
	
	driver = webdriver.Chrome(options=options)

	stealth(driver,
			languages=["en-US", "en"],
			vendor="Google Inc.",
			platform="Win32",
			webgl_vendor="Intel Inc.",
			renderer="Intel Iris OpenGL Engine",
			fix_hairline=True,
			)
	driver.get(url)

	try:
		postal_code_button = driver.find_element(By.XPATH, '//*[@id="root"]/div[5]/div/div[2]/div/form/button')
	except:
		postal_code_button = None

	if postal_code_button:
		postal_code_input = driver.find_element(By.XPATH, '//*[@id="root"]/div[5]/div/div[2]/div/form/div/input')
		postal_code_input.send_keys('28001')
		postal_code_button.click()
		# time.sleep(0.5)


	salir = False
	productos = []

	while not salir:		
		
		footer = driver.find_element(By.CLASS_NAME,'footer')
		driver.execute_script("arguments[0].scrollIntoView();", footer)
		# driver.execute_script("window.stop();");

		WebDriverWait(driver, 3).until(EC.presence_of_element_located((By.CSS_SELECTOR, '.product-container > .product-cell')))

		items = driver.find_element(By.CSS_SELECTOR, '.category-detail__content').find_elements(By.CSS_SELECTOR, '.product-cell')

		for item in items:
			
			ignored_exceptions=(NoSuchElementException,StaleElementReferenceException,)
			title = WebDriverWait(driver, 0.5, ignored_exceptions=ignored_exceptions)\
                        .until(expected_conditions.presence_of_element_located((By.CSS_SELECTOR, 'h4'))).get_attribute('innerText')
			imgUrl = WebDriverWait(driver, 0.5, ignored_exceptions=ignored_exceptions)\
                        # .until(expected_conditions.presence_of_element_located((By.CSS_SELECTOR, 'img'))).get_attribute('src')
			price = WebDriverWait(driver, 0.5, ignored_exceptions=ignored_exceptions)\
                        .until(expected_conditions.presence_of_element_located((By.CLASS_NAME, 'product-price'))).get_attribute('innerText')
			


			# title = item.find_element(By.CSS_SELECTOR, 'h4').get_attribute('innerText')
			imgUrl = item.find_element(By.CSS_SELECTOR, 'img').get_attribute('src')
			# price = item.find_element(By.CLASS_NAME, 'product-price').get_attribute('innerText')

			# print({title: title, price: price, imgUrl: imgUrl})
			productos.append({title: title, price: price, imgUrl: imgUrl})


		try:
			# next_button = driver.find_element(By.XPATH, '//*[@id="root"]/div[3]/div[2]/div[1]/div/div/button')
		
			next_button = WebDriverWait(driver, 0.5, ignored_exceptions=ignored_exceptions)\
                        .until(expected_conditions.presence_of_element_located((By.XPATH, '//*[@id="root"]/div[3]/div[2]/div[1]/div/div/button')))
		except:
			next_button = None
		
		
		if next_button != None:
			next_button.click()
		else:
			salir = True

	driver.quit()
	return productos



catUrl = [
	'https://tienda.mercadona.es/categories/112',
	'https://tienda.mercadona.es/categories/156',
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

productos = []
cont = 1
for u in catUrl:
	productos += get_every_product(u, False)
	print(cont, 'de', len(catUrl), 'categorias escaneadas.')
	cont += 1
print(productos)