package mongoutils;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import mongoutils.datastore.Datastore;
import mongoutils.datastore.SimpleDatastore;
import mongoutils.testing.CarFactory;
import org.bson.UuidRepresentation;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.jetbrains.annotations.NotNull;

public class MongoUtils {

    private final MongoClient mongoClient;

    public static MongoUtils connectByURL(@NotNull String url) {
        return new MongoUtils(url);
    }

    public static MongoUtils connectByCredential(@NotNull MongoCredential credential) {
        return new MongoUtils(credential);
    }

    @NotNull
    private static MongoClientSettings.Builder generateDefaultSettings() {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        return MongoClientSettings.builder()
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .codecRegistry(pojoCodecRegistry)
                .retryReads(true)
                .retryWrites(true);
    }


    private MongoUtils(@NotNull MongoCredential credential) {
        this(generateDefaultSettings().credential(credential).build());
    }

    private MongoUtils(@NotNull String url) {
        this(generateDefaultSettings().applyConnectionString(new ConnectionString(url)).build());
    }

    public MongoUtils(@NotNull MongoClientSettings mongoClientSettings) {
        this.mongoClient = MongoClients.create(mongoClientSettings);
    }

    public MongoUtils(@NotNull MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    @NotNull
    public Datastore createDatastore(@NotNull String databaseName) {
        return new SimpleDatastore(this, databaseName);
    }

    public static void main(String[] args) {
        String url = "mongodb://localhost:27017"; //"mongodb+srv://UserTest:UserTestPW@acrylic.f7wea.gcp.mongodb.net/test?retryWrites=true&w=majority";

        MongoUtils mongoUtils = new MongoUtils(url);
        Datastore datastore = mongoUtils.createDatastore("cars");
        datastore.saveAll(CarFactory.getDeliveryCar(), CarFactory.getRaceCar(), CarFactory.getNewSimpleCar());
    }


}
