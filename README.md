 This is a morphia-like recreation.
 
 This recreation is due to the fact that some mapping tools provided
 in morphia <2.0 are not satisfactory to what I need, thereby needing me
 to create this project.
 
 As of morphia 2.0+, morphia has provided more useful utility features
 to access MongoDB that are included in this package. This thereby makes
 this project obsolete for such versions. As of the time of me making this,
 I am not able to run later versions of java, thereby restricting me to
 morphia 1.6.1.
 
 This project is just a one time solution to resolving my needs. This will
 not be maintained after completion of said project.
 Use it at your own risk!
 
 [![](https://jitpack.io/v/Acrylic125/MongoUtils.svg)](https://jitpack.io/#Acrylic125/MongoUtils)
 
 Gradle
 ```xml
allprojects {
repositories {
   ...
  maven { url 'https://jitpack.io' }
  }
}
 ...
dependencies {
  implementation 'com.github.Acrylic125:MongoUtils:v1.0b'
}
```

Maven:
```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
...
<dependencies>
  <dependency>
    <groupId>com.github.Acrylic125</groupId>
    <artifactId>MongoUtils</artifactId>
    <version>v1.0b</version>
  </dependency>
</dependencies>
```
