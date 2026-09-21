# Laboratotio--Gestor-De-Impresiones-en-JAVA
Aplicación en Java que simula un gestor de impresiones combinando dos estructuras de datos lineales: una **cola** para los documentos pendientes y una **pila** para el historial de impresiones, que permite recuperar la última.

##  Descripción

- La **cola** (FIFO) conserva los documentos pendientes en orden de llegada. El primero en registrarse es el primero en imprimirse.
- La **pila** (LIFO) registra los documentos ya impresos. El último impreso queda en la cima y puede recuperarse.

Ambas estructuras se declaran como `Deque<String>` (implementación `ArrayDeque`), pero cada una usa únicamente las operaciones propias de su contrato.

##  Operaciones

| Acción | Estructura | Operación | Resultado |
|--------|-----------|-----------|-----------|
| Registrar documento | `pendientes` | `offerLast(nombre)` | Entra al final |
| Imprimir siguiente | `pendientes` | `pollFirst()` | Sale el más antiguo |
| Guardar impresión | `historial` | `push(nombre)` | Queda en la cima |
| Recuperar última | `historial` y `pendientes` | `pop()` y `addFirst()` | Vuelve al frente |

Antes de retirar elementos, el programa valida que la estructura no esté vacía.

##  Requisitos

- Java 8 o superior (JDK)

##  Compilación y ejecución

```bash
javac GestorImpresiones.java
java GestorImpresiones
```

##  Ejemplo de ejecución

El `main` ejecuta 10 operaciones combinadas. Estas son las acciones que se muestran en consola:

```
Recuperar última -> el historial está vacío
Registrar "Informe.pdf" -> entra al final de pendientes
Registrar "Tarea.docx" -> entra al final de pendientes
Registrar "Foto.png" -> entra al final de pendientes
Imprimir siguiente -> imprimiendo "Informe.pdf"
Imprimir siguiente -> imprimiendo "Tarea.docx"
Recuperar última -> "Tarea.docx" vuelve al frente de pendientes
Imprimir siguiente -> imprimiendo "Tarea.docx"
Imprimir siguiente -> imprimiendo "Foto.png"
Imprimir siguiente -> no hay documentos pendientes
```

Después de cada operación se imprime el estado de ambas estructuras:

```
   pendientes (frente -> final): [Foto.png]
   historial  (cima -> base)   : [Tarea.docx, Informe.pdf]
```

##  Estructura del proyecto

```
.
├── GestorImpresiones.java
└── README.md
```

##  Posibles mejoras

- Reemplazar `String` por una clase `Documento` (nombre, fecha, número de páginas).
- Limitar el tamaño del historial.
- Permitir varias recuperaciones consecutivas.
- Agregar un menú interactivo por consola.

##  Contexto

Laboratorio de estructuras de datos: colas y pilas con `Deque` en Java.
