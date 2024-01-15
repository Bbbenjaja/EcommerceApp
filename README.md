# Descripción detallada de la ventana de 'Inicio'

Esta es la ventana que se muestra al iniciar la app, contiene un grafo de navegación a través del cual puedes viajar a otros 2 fragmentos.

<table>
  <tr>
    <td>
    Inicio
    <img src="https://github.com/Bbbenjaja/EcommerceApp/blob/feature/home/homeScreens/home.jpg?raw=true" alt="Home" height="600"/> </td>
    <td>
    Detalle del producto
    <img src="https://github.com/Bbbenjaja/EcommerceApp/blob/feature/home/homeScreens/detalle%20producto.jpg?raw=true" alt="Detail" height="600"/> </td>
    <td>
    Añadir producto
    <img src="https://github.com/Bbbenjaja/EcommerceApp/blob/feature/home/homeScreens/agregar%20poducto.jpg?raw=true" alt="Add product" height="600"/></td>
  </tr>
  <tr>
  <td>
  Producto agregado
  <img src="https://github.com/Bbbenjaja/EcommerceApp/blob/feature/home/homeScreens/productoAgregado.jpg?raw=true" alt="Added" height="600"/></td>
  <td>
  Buscar producto
  <img src="https://github.com/Bbbenjaja/EcommerceApp/blob/feature/home/homeScreens/Search.jpeg?raw=true" alt="Search" height="600"/></td>
  </tr>
</table>

___
### Explicación
La navegación sucede de la siguiente manera:
- Desde el home se puede navegar al detalle el producto clickando en cualquiera y la búsqueda de productos simplemente escribiendo el searcher y dando click en la lupa del teclado.
- Desde la búsqueda de productos se puede navegar al detalle de algún producto.
- Desde el detalle del producto se puede añadir a favoritos o añadir al carrito, si se agregó con éxito al carrito muestra un mensaje para ir al carrito que es otro grafo de navegación principal.
___
### Extras
- Los productos que aparecen en la ventana inicio y los que aparecen en el detalle del producto (productos similares) mostrarán mas o menos productos por columna dependiendo del tamaño de la pantalla del dispositivo o si este se encuentra en portrait o landscape.
- En los detalles del producto, se pueden ver todas las imagenes de este, deslizando a la derecha, también se puede realizar zoom a cada imagen.
- La forma en la que se almacenan favoritos y productos en el carrito sucede de manera local, debido a que no se cuenta con un backend real manipulable. Todo con persistencia de datos con Room.
- Si la conexión a la API llega a fallar y no se pueden obtener los productos, estos también son almacenados de manera local cada vez que se ejecuta la app, eliminando los anteriores, por lo que el usuario puede usar la app para la mayoría de sus funciones de manera offline.
