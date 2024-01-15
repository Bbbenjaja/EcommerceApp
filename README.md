# E-Commerce App

### Descripción general 
Esta app es una de demostración para portfolio, consiste en una app tipo E-Commerce, contiene 4 pestañas, desde las cuales se puede navegar a otras más, cada pestaña principal está apartada en su respectiva rama de este mismo repositorio, según se fueron desarrollando con el nombre de "feature/nombre-ventana" y en las que se muestra información mas detallada de cada una.
___

### Características :bookmark_tabs:
- Contiene 4 grafos de navegación principales, cada uno con otras pantallas.
- Clean arquitecture MVVM(Model-View View-Model).
- Consumo de datos de API con Retrofit2
- Persistencia de datos con Room.
- Inyección de dependencias con Dagger Hilt.
- Pruebas unitarias usando Kotlin-Test, Junit, Mockk,
- Navegación con Navigation Component.
- Offline first.
- Uso de LiveData para observar cambios en el ViewModel.
- Manejo de corroutines y manejo del ciclo de vida de fragments.
- La cantidad de productos por columna dentro del RecyclerView se adaptan dependiendo del tamaño de la pantalla.

### Ventanas implementadas (de momento) :iphone: :left_right_arrow: :iphone: :heavy_check_mark:

 - :heavy_check_mark: Inicio :arrow_right: [ir a ver detalles](https://github.com/Bbbenjaja/EcommerceApp/tree/feature/home)
 - :x: Categorías 
 - :x: Carrito 
 - :x: Perfil 

### Ventanas principales :iphone:
[Inicio](https://github.com/Bbbenjaja/EcommerceApp/tree/feature/home)

<img src="https://github.com/Bbbenjaja/EcommerceApp/blob/feature/home/homeScreens/home.jpg?raw=true" alt="Home" height="900"/>

### Hecho en 🛠
 - Kotlin.
 - Diseño de vistas en XML.
___
### Oportunidades de mejora :exclamation:
Algunas oportunidades para mejorar esta app:
- **Uso de Jetpack Compose**; Entiendo la importancia de Jetpack Compose actualmente y yo le apuesto a dicha tecnología a futuro, use XML principalmente porque a la fecha en que comencé a desarrollar este proyecto (10 Dic. 2023) no me siento capaz de crear algo así en compose debido a que es una tecnología que aún sigo aprendiendo. Mis futuros proyectos solo estarán construidos en dicha tecnología.
- **Uso de Flow, StateFlow y SharedFlow**; No apliqué los flows en este proyecto, porque no consideré que necesitara ser una app muy reactiva, debido a que no se iban a producir muchos cambios en la UI por estar consumiendo datos de una Fake API principalmente, sin embargo entiendo que en un proyecto igual a este pero real, si debo de estar atento a cambios en el backend y actualizar la UI en base a eso.
- **Testing**; He aplicado al menos las pruebas unitarias, pongo este punto porque a la fecha de hoy (14 Enero 2024) aún sigo aprendiendo a realizar testing, entre los planes está agregar test de UI.

## Contacto
- Email: <benjaminvalenzuela023@gmail.com>
