# Clinica JavaFX

Aplicación de escritorio JavaFX para la gestión básica de citas, registro de pacientes y pago de copagos.

## Requisitos

- Java 21 instalado
- Maven wrapper incluido en el proyecto
- Windows (se usa `mvnw.cmd` en los ejemplos)

## Cómo ejecutar

1. Abrir PowerShell o CMD en la carpeta del proyecto:
   ```powershell
   cd "C:\Users\laura\OneDrive\Escritorio\Clinica"
   ```
2. Compilar el proyecto:
   ```powershell
   .\mvnw.cmd -DskipTests compile
   ```
3. Ejecutar la aplicación JavaFX:
   ```powershell
   .\mvnw.cmd javafx:run
   ```

## Usuarios demo

### Médico
- Usuario: `medico`
- Contraseña: `doctor123`

### Pacientes de ejemplo

En la aplicación, el médico debe registrar los pacientes. El nombre de usuario y la contraseña de cada paciente se generan como su cédula.

Ejemplos de cuentas de paciente para crear desde el panel del médico:

1. Nombre: `Juan Pérez`
   - Cédula: `1234567890`
   - Usuario: `1234567890`
   - Contraseña: `1234567890`
   - Email: `juan.perez@example.com`

2. Nombre: `María Gómez`
   - Cédula: `0987654321`
   - Usuario: `0987654321`
   - Contraseña: `0987654321`
   - Email: `maria.gomez@example.com`

3. Nombre: `Carlos Díaz`
   - Cédula: `1112223334`
   - Usuario: `1112223334`
   - Contraseña: `1112223334`
   - Email: `carlos.diaz@example.com`

> Nota: el paciente debe ser registrado por el médico antes de poder iniciar sesión como paciente.

## Flujo recomendado para demo

1. Iniciar sesión con el médico.
2. Registrar uno o varios pacientes.
3. Crear citas para esos pacientes.
4. Cerrar sesión y volver a ingresar como paciente con la cédula.
5. Pagar el copago de la cita desde la vista del paciente.

## Casos de aceptación (Given-When-Then)

1. **G**: Usuario con 3 préstamos | **W**: intenta nuevo | **T**: mensaje “sin cupo” y no crea préstamo.
2. **G**: Ejemplar prestado | **W**: confirmar | **T**: bloquea y mantiene estado.
3. **G**: Paciente no registrado | **W**: intenta agendar cita | **T**: muestra mensaje de error y no crea cita.
4. **G**: Fecha de cita en el pasado | **W**: intenta agendar | **T**: muestra mensaje de error y no crea cita.
5. **G**: Cita pagada | **W**: intenta pagar copago | **T**: botón deshabilitado o muestra mensaje de aviso y no repite pago.

## Endpoints sugeridos (diseño)

Aunque el proyecto actual no implementa una API REST, estos endpoints pueden usarse como especificación para una versión futura:

- `GET /usuarios?query=` – buscador de usuarios/pacientes
- `GET /ejemplares?query=` – buscador de ejemplares (o servicios en el contexto de clínica)
- `POST /prestamos` – valida reglas y crea préstamo (o cita)
- `PATCH /ejemplares/{id}` – cambia estado del ejemplar
- `GET /usuarios`, `POST /usuarios`, `PATCH /usuarios/{id}`, `DELETE /usuarios` – CRUD de usuarios

## Validaciones de negocio importantes

- Solo el médico puede registrar pacientes y citas.
- El paciente debe existir antes de crear una cita.
- No se puede agendar una cita en una fecha pasada.
- No se permite crear citas duplicadas para el mismo paciente en la misma fecha y hora.
- El copago solo se paga si la factura no está marcada como pagada y su total es mayor a cero.

## Observaciones

- Las cuentas de paciente usan `cedula` como usuario y contraseña.
- Los mensajes de alerta se muestran dentro de la GUI, y la aplicación no tiene un backend web REST implementado.
- Para pruebas de demo, registra al menos 3 pacientes y varias citas, luego paga el copago desde la vista de paciente.
