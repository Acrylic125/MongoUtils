package mongoutils.datastore;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import mongoutils.MongoUtils;
import mongoutils.annotations.AnnotationConstants;
import mongoutils.annotations.Pojo;
import mongoutils.query.DocumentQuery;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

public class SimpleDatastore implements Datastore {

    private final MongoDatabase database;

    public SimpleDatastore(@NotNull MongoDatabase database) {
        this.database = database;
    }

    public SimpleDatastore(@NotNull MongoUtils mongoUtils, @NotNull String database) {
        this(mongoUtils.getMongoClient().getDatabase(database));
    }

    @Override
    public @NotNull MongoDatabase getMongoDatabase() {
        return database;
    }

    @Override
    public @NotNull <T> MongoCollection<T> getMongoCollection(@NotNull String collectionName, @NotNull Class<T> clazz) {
        return database.getCollection(collectionName, clazz);
    }

    @Override
    public @NotNull MongoCollection<Document> getMongoCollection(@NotNull String collectionName) {
        return database.getCollection(collectionName);
    }

    @Override
    public @NotNull <T> MongoCollection<T> getMongoCollection(@NotNull Class<T> clazz) {
        return getCollectionFromClass(clazz);
    }

    @Override
    public <T> DocumentQuery<T> query(@NotNull String collectionName, @NotNull Class<T> clazz) {
        return new DocumentQuery<>(getMongoCollection(collectionName, clazz));
    }

    @Override
    public DocumentQuery<Document> query(@NotNull String collectionName) {
        return new DocumentQuery<>(getMongoCollection(collectionName));
    }

    @Override
    public <T> DocumentQuery<T> query(@NotNull Class<T> clazz) {
        return new DocumentQuery<>(getMongoCollection(clazz));
    }

    private <T> void save(@NotNull T object, @NotNull MongoCollection<T> collection) {
        ObjectId id = getObjectID(object);
        T check = collection.find(Filters.eq(id)).first();
        if (check == null) {
            collection.insertOne(object);
        } else {
            collection.replaceOne(Filters.eq(id), object);
        }
    }

    @SuppressWarnings("all")
    private <T> MongoCollection<T> getCollectionFromObject(@NotNull T object) {
        return (MongoCollection<T>) getCollectionFromClass(object.getClass());
    }

    @SuppressWarnings("all")
    private <T> MongoCollection<T> getCollectionFromClass(@NotNull Class<T> clazz) {
        Pojo pojo = clazz.getAnnotation(Pojo.class);
        String name;
        if (pojo == null) {
            name = clazz.getSimpleName().toLowerCase(Locale.ROOT);
        } else {
            name = pojo.collectionName();
            if (name == AnnotationConstants.USE_DEFAULT)
                name = clazz.getName();
        }
        return (MongoCollection<T>) getMongoCollection(name, clazz);
    }

    private <T> void saveByObject(@NotNull T object) {
        save(object, getCollectionFromObject(object));
    }

    @Override
    @SuppressWarnings("all")
    public <T> void save(@NotNull String collectionName, @NotNull T object) {
        MongoCollection<T> collection = (MongoCollection<T>) getMongoCollection(collectionName, object.getClass());
        save(object, collection);
    }

    @Override
    public <T> void save(@NotNull T object) {
        saveByObject(object);
    }

    @Override
    public <T> void saveAll(@NotNull String collectionName, @NotNull T... objects) {
        saveAll(collectionName, Arrays.asList(objects));
    }

    @Override
    public <T> void saveAll(@NotNull T... objects) {

    }

    @Override
    public <T> void saveAll(@NotNull String collectionName, @NotNull Collection<T> objects) {
        saveAll(collectionName, objects.iterator());
    }

    @Override
    public <T> void saveAll(@NotNull Collection<T> objects) {

    }

    @Override
    @SuppressWarnings("all")
    public <T> void saveAll(@NotNull String collectionName, @NotNull Iterator<T> objects) {
        Map<Class<?>, MongoCollection<?>> collectionMap = new HashMap<>();
        while (objects.hasNext()) {
            T obj = objects.next();
            Class<?> type = obj.getClass();
            MongoCollection<T> collection = (MongoCollection<T>) collectionMap.get(obj.getClass());
            if (collection == null) {
                collection = (MongoCollection<T>) getMongoCollection(collectionName, type);
                collectionMap.put(type, collection);
            }
            save(obj, collection);
        }
    }

    @Override
    public <T> void saveAll(@NotNull Iterator<T> objects) {

    }

    @Nullable
    private <T> ObjectId getObjectID(@NotNull T obj) {
        ObjectId possibleID = null;
        boolean foundID = false;
        Class<?> clazz = obj.getClass();
        for (Field declaredField : clazz.getDeclaredFields()) {
            try {
                Object value = declaredField.get(obj);
                if (declaredField.getName().equals("id")) {
                    if (value == null) {
                        possibleID = null;
                    } else {
                        validateObjectID(value);
                        possibleID = (ObjectId) value;
                        foundID = true;
                    }
                } else if (declaredField.getAnnotation(BsonId.class) != null) {
                    validateObjectID(value);
                    return (ObjectId) value;
                }
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        if (!foundID)
            throw new RuntimeException("No object id was found in " + clazz + ". Either annotate the ObjectId variable as @BsonId or have an ObjectId variable named id.");
        return possibleID;
    }

    private void validateObjectID(Object obj) {
        if (!(obj instanceof ObjectId))
            throw new RuntimeException("The id must be an ObjectId object.");
    }


}
