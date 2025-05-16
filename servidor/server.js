const grpc = require('@grpc/grpc-js');
const protoLoader = require('@grpc/proto-loader');


const packageDefinition = protoLoader.loadSync('service.proto');
const greeterProto = grpc.loadPackageDefinition(packageDefinition).Greeter;

const sayHello = (call, callback) => {

    const metadata = call.metadata.getMap();
    console.log('Metadatos recibidos:', metadata);

    const authToken = metadata['authorization'];
    if (!authToken || !authToken.startsWith('Bearer ')) {
        return callback({
            code: grpc.status.UNAUTHENTICATED,
            message: 'Token de autorización requerido'
        });
    }

    const responseMetadata = new grpc.Metadata();
    responseMetadata.add('server-version', '1.0.0');
    responseMetadata.add('processed-by', 'nodejs-server');

    call.sendMetadata(responseMetadata);

    callback(null, {
        message: `Hola ${call.request.name} (Token: ${authToken.substring(7, 12)}...)`
    });
};


const main = () => {
    const PORT = 50051;
    const server = new grpc.Server();
    server.addService(greeterProto.service, { sayHello });
    server.bindAsync(`localhost:${PORT}`, grpc.ServerCredentials.createInsecure(), (err, port) => {
        if (err) {
            console.error(err);
            return;
        }
        console.log(`Servidor en ejecución en http://localhost:${port}`);
    });
};

main();