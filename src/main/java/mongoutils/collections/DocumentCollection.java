package mongoutils.collections;

import com.mongodb.client.MongoCollection;
import mongoutils.dcl.DocumentClassLoaders;

public interface DocumentCollection<TDocument> {

    MongoCollection<TDocument> getCollection();

    DocumentClassLoaders<TDocument> getClassLoaders();



}
