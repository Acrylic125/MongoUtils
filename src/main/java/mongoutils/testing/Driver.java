package mongoutils.testing;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;

import java.util.Locale;

@BsonDiscriminator
public interface Driver {

    String getName();

    float getAge();

    Locale getLanguage();

}
