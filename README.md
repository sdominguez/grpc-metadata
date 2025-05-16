# Proyecto gRPC: Servidor Node.js y Cliente Java

Este proyecto contiene una arquitectura cliente-servidor basada en gRPC, con dos componentes principales:

- **Servidor gRPC en Node.js**
- **Cliente gRPC en Java 11 (IntelliJ IDEA)**

---

## 🖥️ Estructura del Proyecto

```
.
├── grpc-server-node/      # Servidor gRPC en Node.js
│   ├── index.js
│   ├── metadatos.proto
│   ├── package.json
│   └── ...
├── grpc-client-java/      # Cliente gRPC en Java (IntelliJ)
│   ├── src/
│   ├── build.gradle
│   └── ...
└── README.md
```

---

## 🚀 Servidor gRPC en Node.js

### 📦 Instalación de dependencias

Desde la carpeta `grpc-server-node/`:

```bash
npm install
```

Esto instalará las siguientes dependencias necesarias:

- [`@grpc/grpc-js`](https://www.npmjs.com/package/@grpc/grpc-js)
- [`@grpc/proto-loader`](https://www.npmjs.com/package/@grpc/proto-loader)

### ▶️ Ejecución

```bash
node server.js
```

---

## ☕ Cliente gRPC en Java (IntelliJ)

### 🛠️ Requisitos

- Java 11
- IntelliJ IDEA
- Protoc (`protoc` y `protoc-gen-grpc-java` configurados)

### ⚙️ Compilación de Protobuf

Desde IntelliJ o por terminal:

```bash
./gradlew build
```

Asegúrate de tener configurado el plugin de protobuf como se especifica en `build.gradle`.

### ▶️ Ejecución

Puedes ejecutar el cliente desde IntelliJ o por consola usando:

```bash
./gradlew run
```

---

## 📝 Notas

- Asegúrate de que el archivo `.proto` sea idéntico en ambos lados (cliente y servidor).
- El cliente Java utiliza los stubs generados automáticamente a partir del `.proto`.

---
