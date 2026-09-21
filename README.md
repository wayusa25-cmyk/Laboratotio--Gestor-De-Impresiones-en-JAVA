# Laboratotio--Gestor-De-Impresiones-en-JAVA
Aplicación en Java que simula un gestor de impresiones combinando dos estructuras de datos lineales: una **cola** para los documentos pendientes y una **pila** para el historial de impresiones, que permite recuperar la última.

## Descripción

- La **cola** (FIFO) conserva los documentos pendientes en orden de llegada. El primero en registrarse es el primero en imprimirse.
- La **pila** (LIFO) registra los documentos ya impresos. El último impreso queda en la cima y puede recuperarse.

Ambas estructuras se declaran como `Deque<String>` (implementación `ArrayDeque`), pero cada una usa únicamente las operaciones propias de su contrato.

## Operaciones

| Acción | Estructura | Operación | Resultado |
|--------|-----------|-----------|-----------|
| Registrar documento | `pendientes` | `offerLast(nombre)` | Entra al final |
| Imprimir siguiente | `pendientes` | `pollFirst()` | Sale el más antiguo |
| Guardar impresión | `historial` | `push(nombre)` | Queda en la cima |
| Recuperar última | `historial` y `pendientes` | `pop()` y `addFirst()` | Vuelve al frente |

Antes de retirar elementos, el programa valida que la estructura no esté vacía.

## TDA (Tipo de Dato Abstracto)

El gestor se apoya en dos TDA lineales. Cada uno se define por sus datos y por las operaciones permitidas, sin depender de cómo se implemente internamente.

### TDA Cola de documentos pendientes

**Descripción:** colección ordenada de documentos donde las inserciones se hacen por el final y las eliminaciones por el frente (FIFO).

**Datos:** secuencia de elementos `String` (nombres de documentos), con un frente y un final.

| Operación | Descripción | Precondición | Postcondición | Equivalente en Java |
|-----------|-------------|--------------|---------------|---------------------|
| `encolar(nombre)` | Agrega un documento al final | Ninguna | El documento queda al final de la cola | `offerLast(nombre)` |
| `desencolar()` | Retira y devuelve el documento del frente | La cola no está vacía | Se elimina el elemento más antiguo | `pollFirst()` |
| `insertarAlFrente(nombre)` | Reincorpora un documento al frente (solo para recuperación) | Ninguna | El documento queda en el frente | `addFirst(nombre)` |
| `estaVacia()` | Indica si no hay elementos | Ninguna | No modifica la cola | `isEmpty()` |

### TDA Pila de historial

**Descripción:** colección de documentos donde las inserciones y eliminaciones se hacen por un mismo extremo, la cima (LIFO).

**Datos:** secuencia de elementos `String` (nombres de documentos impresos), con una cima.

| Operación | Descripción | Precondición | Postcondición | Equivalente en Java |
|-----------|-------------|--------------|---------------|---------------------|
| `apilar(nombre)` | Guarda un documento en la cima | Ninguna | El documento queda en la cima | `push(nombre)` |
| `desapilar()` | Retira y devuelve el documento de la cima | La pila no está vacía | Se elimina el último elemento apilado | `pop()` |
| `estaVacia()` | Indica si no hay elementos | Ninguna | No modifica la pila | `isEmpty()` |

### TDA Gestor de impresiones

Combina los dos TDA anteriores.

| Operación | Descripción | Uso de los TDA |
|-----------|-------------|----------------|
| `registrar(nombre)` | Agrega un documento a la lista de pendientes | `encolar` en la cola |
| `imprimirSiguiente()` | Imprime el documento más antiguo y lo guarda en el historial | `desencolar` en la cola y `apilar` en la pila |
| `recuperarUltima()` | Devuelve la última impresión al frente de los pendientes | `desapilar` en la pila e `insertarAlFrente` en la cola |

**Invariantes:**

- Un documento se encuentra en la cola o en la pila, nunca en ambas a la vez.
- La cola mantiene el orden de llegada, salvo un documento recuperado, que pasa al frente.
- La cima de la pila siempre es el último documento impreso.

## Requisitos

- Java 8 o superior (JDK)

## Compilación y ejecución

```bash
javac GestorImpresiones.java
java GestorImpresiones
```

## Ejemplo de ejecución

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

## Estructura del proyecto

```
.
├── GestorImpresiones.java
└── README.md
```

## Posibles mejoras

- Reemplazar `String` por una clase `Documento` (nombre, fecha, número de páginas).
- Limitar el tamaño del historial.
- Permitir varias recuperaciones consecutivas.
- Agregar un menú interactivo por consola.

## Contexto

Laboratorio de estructuras de datos: colas y pilas con `Deque` en Java.