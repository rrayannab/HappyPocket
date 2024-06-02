# HappyPocket!
El proyecto software que desarrollaremos tendrá la función de ser un buscador para encontrar los mejores precios y ofertas de productos, con opción de ser indexados por categorías, dando la posibilidad de crear la cesta de la compra. 

Realizando un estudio de mercado hemos visto que no hay disponibles muchas aplicaciones que ofrezcan este servicio, algunas de estas son SoySuper, tiendeo, FindItApp y Onprice.

Algunas de los problemas que el software quiere solucionar es: 
1. Ahorro de tiempo: a menudo los clientes visitan varios supermercados para comparar precios y encontrar las mejores ofertas. 
2. Optimización de costes: Los clientes buscan constantemente maneras de ahorrar dinero en sus compras diarias.
3. Transparencia de precios: Algunas tiendas pueden tener estrategias de precios confusas o poco claras. 
4. Facilitar la elección: La variedad de productos y marcas disponibles en los supermercados puede ser abrumadora. 

Por lo tanto, desarrollando esta aplicación web conseguiremos ahorrar tiempo a los usuarios. Tendrán acceso, en una misma página, a todos los productos de distintos supermercados como Mercadona, Carrefour, etc. Esto les permitirá poder elegir el producto que más les convenga en función de sus intereses. También ofreceremos la posibilidad de hacer la lista de la compra y que la web ofrezca al usuario un listado recomendado de los productos que se podrían comprar en cada uno de los supermercados disponibles en nuestra web.

# Miembros
|Nº de miembro| Nombre | Apellido | Correo|
|--|--|--|--|
| 1 | Isabel | Frías Castillo | isabelfriasc@uma.es |
| 2| María | Hurtado Valverde | mariahurtado@uma.es |
| 3| Marta | Muñoz Aguilar | martamunozaguilar@uma.es |
| 4| Eduardo J. | Cantero Ramón | eduardocantero@uma.es |
| 5| Rayan | Abarkan | rayan10@uma.es |
| 6| Jorge | Rodríguez Mata | jorge_roma@uma.es |

# Cómo ejecutar
1. Ejecutar el servidor backend, tenemos dos opciones:
   - ubicado en backendHappyPockets/src/main/java/com/happyPockets/BackendHappyPocketsApplication.java
   - desde terminal ubicarse en la careta backendHappyPockets y lanzar el comando ./mvnw spring-boot:run
2. Abrir el frontend, ubicado en happypocketweb/web/index.html

# Cómo ejecutar las preubas del servidor backend
Tenemos varias opciones:
1. Desde terminal ubicarse en la careta backendHappyPockets y lanzar el comando ./mvnw test
2. Desde IntelliJ hacer clic derecho sobre la carpeta src/test/java lo que ejecutara todos los tests a la vez

Estos tests costan de:
1. Para la clase User, 2 pruebas unitarias 
2. Para la clase UserService, 6 pruebas unitarias 
3. Para la clase Products, 3 pruebas unitarias 
4. Para la clase ProductService, 7 pruebas unitarias 
5. Para la clase ProductController, 6 pruebas de integracion, cubriendo los casos de uso de busqueda: Búsqueda por filtro, Por categoría, Por rango de precios, Alfabéticamente, Búsqueda sin filtro. 
6. Para la clase UserController, 4 pruebas de integracion, cubriendo los casos de uso de Iniciar sesion y Registro

# Cómo ejecutar las pruebas del frontend
