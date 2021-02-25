package mongoutils;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertOneOptions;
import mongoutils.dcl.DocumentClassLoaders;
import mongoutils.dcl.SimpleDocumentClassLoaders;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;

import static org.bson.codecs.configuration.CodecRegistries.*;

public class MongoUtils {

    private final MongoClient mongoClient;
    private final DocumentClassLoaders classLoaders;

    public MongoUtils(@NotNull MongoClient mongoClient) {
        this(mongoClient, new SimpleDocumentClassLoaders());
    }

    public MongoUtils(@NotNull MongoClient mongoClient, @NotNull DocumentClassLoaders classLoaders) {
        this.mongoClient = mongoClient;
        this.classLoaders = classLoaders;
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public DocumentClassLoaders getClassLoaders() {
        return classLoaders;
    }

    public static MongoUtils connectByURL(@NotNull String url) {
        return new MongoUtils(MongoClients.create(url));
    }

    public static void main(String[] args) {
        String url = "mongodb://localhost:27017"; //"mongodb+srv://UserTest:UserTestPW@acrylic.f7wea.gcp.mongodb.net/test?retryWrites=true&w=majority";
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().register(Test.class).automatic(true).build()));
        MongoClient mongoClient = MongoClients.create(
                MongoClientSettings.builder()
                        .codecRegistry(pojoCodecRegistry)
                        .applyConnectionString(new ConnectionString(url))
                        .retryReads(true)
                        .retryWrites(true)
                        .build()
        );
        for (String listDatabaseName : mongoClient.listDatabaseNames()) {
            System.out.println(listDatabaseName);
        }
        MongoDatabase database = mongoClient.getDatabase("test");
        System.out.println(database);
        MongoCollection<Test> test = database.getCollection("a", Test.class);
        test.insertOne(new Test());
        System.out.println(test.getDocumentClass());
    }

    private static class Test {

        private ObjectId id = new ObjectId();
        private long testlong = 1000;
        private double gay = 1000.3d;
    }

}
