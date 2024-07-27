# DWF404_MELENDEZ_ALVIN_VASQUEZ_DANIEL

CRUD y Webservice implementado con Jakarta Server Faces, REST API para consumo con WebServices

### Características del proyecto
- **CRUD:** Crear, Leer, Actualizar y Eliminar para reqgistrar y gestionar videojuegos y ventas de videojuegos.
- **Webservice:** Permite buscar ventas y videojuegos de manera sencilla mediante un ID

### Requisitos
- **IntelliJ IDEA**
- **Payara Server:** Versión 5.2022.21
- **MySQL**
- **JDK:** Java Development Kit versión 11

### Pasos para ejecutar el proyecto.

1. **Clonar el proyecto**
   ```sh
   git clone https://github.com/RealAlvinJS/DWF404_MELENDEZ_ALVIN_VASQUEZ_DANIEL.git
   cd DWF_MELENDEZ_SERRANO_VASQUEZ_VENTURA

2. Abrir la carpeta del proyecto con *IntelliJ IDEA*
3. Ejecutar el archivo `db.sql` que se encuentra en `src/java/main/database` en un gestor de base de datos MySQL
4. Descargar las dependencias necesarias desde el archivo `pom.xml`
5. Configurar el servidor *Payara 5.2022.21* para ejecutar la aplicación
6. De ser necesario, modificar las credenciales y el servidor de la base de datos desde la clase `DatabaseConnection` que se encuentra en la carpeta `src/java/main/database`
7. Finalmente, deployar y ejecutar la aplicación con *Payara Server*

### ¿Cómo continuar?
Al ejecutar el proyecto, se abrirá una vista con la información de este, más la documentación para el consumo de la API

