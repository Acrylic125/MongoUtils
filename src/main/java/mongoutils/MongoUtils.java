package mongoutils;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import dev.morphia.DatastoreImpl;
import mongoutils.datastore.Datastore;
import mongoutils.datastore.SimpleDatastore;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MongoUtils {

    private final MongoClient mongoClient;

    @NotNull
    private static MongoClientSettings.Builder generateDefaultSettings() {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        return MongoClientSettings.builder()
                .codecRegistry(pojoCodecRegistry)
                .retryReads(true)
                .retryWrites(true);
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

    public static MongoUtils connectByURL(@NotNull String url) {
        return new MongoUtils(MongoClients.create(url));
    }

    public static void main(String[] args) {
        String url = "mongodb://localhost:27017"; //"mongodb+srv://UserTest:UserTestPW@acrylic.f7wea.gcp.mongodb.net/test?retryWrites=true&w=majority";

        MongoUtils mongoUtils = new MongoUtils(url);
        Datastore datastore = mongoUtils.createDatastore("test");
        Test test1 = new Test();
        test1.map.put("Obj1", new Concrete());
        test1.map.put("Obj2", new Forgery());
        datastore.save("a", test1);
        Test queried = datastore.query("a", Test.class)
                .filterID("6039f1d00581b22a11f047b0")
                .queryFirst();
        System.out.println(queried);
        if (queried != null) {
            queried.horny = "Updated";
            datastore.save("a", queried);
        }
    }

    @BsonDiscriminator
    public interface Abstract {

    }

    @BsonDiscriminator
    public static class Concrete implements Abstract {

        public int cunt = 1000;
        public String gay = "Hello";
        public boolean fag = true;

        @Override
        public String toString() {
            return "Concrete{" +
                    "cunt=" + cunt +
                    ", gay='" + gay + '\'' +
                    ", fag=" + fag +
                    '}';
        }
    }

    @BsonDiscriminator
    public static class Forgery implements Abstract {
        public int forge = 69;
        public double iq = 120;

        @Override
        public String toString() {
            return "Forgery{" +
                    "forge=" + forge +
                    ", iq=" + iq +
                    '}';
        }
    }


    @BsonDiscriminator
    public static class Test {

        public ObjectId id = new ObjectId();
        public long testlong = 68188;
        public double gay = 4423133;
        public String horny = "I am not horny!";
        @BsonProperty(useDiscriminator = true)
        public Map<String, Abstract> map = new HashMap<>();

        @Override
        public String toString() {
            return "Test{" +
                    "testlong=" + testlong +
                    ", gay=" + gay +
                    ", horny='" + horny + '\'' +
                    ", map=" + map +
                    '}';
        }
    }

}
