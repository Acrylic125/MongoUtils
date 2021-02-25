package mongoutils.query;

import mongoutils.collections.DocumentCollection;
import org.bson.Document;

public class DocumentQuery<TDocument> extends IteratorQuery<TDocument> {

    public DocumentQuery(DocumentCollection<TDocument> collection) {
        super(collection.getCollection().find().iterator());
    }
}
