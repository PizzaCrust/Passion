# Passion

**Passion** is a multi-purpose general library dealing with:

- Bytecode modification
- Hashing algorithms
- File system abstraction and utilities
- Reflection scanning utilities
- Srg mapping utilities

## Resolving

**Passion** has been published on JCenter. You can access this library using Maven, similarly to this:
```xml
<repositories>
   <repository>
      <id>jcenter<id>
      <url>http://jcenter.bintray.com/</url>
   </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>online.pizzacrust</groupId>
    <artifactId>Passion</artifactId>
    <version>1.0</version>
  </dependency>
</dependencies>
```

Feel brave? Test out new features using snapshots. You can access them using Maven, similarly to this:
```xml
<repositories>
  <repository>
    <id>jfrog</id>
    <url>https://oss.jfrog.org/artifactory/oss-snapshot-local</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>online.pizzacrust</groupId>
    <artifactId>Passion</artifactId>
    <version>2.0-SNAPSHOT</version>
  </dependency>
</dependencies>
```