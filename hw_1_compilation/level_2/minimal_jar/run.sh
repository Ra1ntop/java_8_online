javac -sourcepath ./src -d build/classes ./src/ua/com/alevel/ForSend.java
jar cvfm build/jar/ForSend.jar resources/MANIFEST.MF -C build/classes .
java -jar build/jar/ForSend.jar