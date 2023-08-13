javac -sourcepath ./src -d build/classes -cp ./libs/commons-math3-3.6.1.jar ./src/ua/com/alevel/ForSend.java
jar cvfm build/jar/forsend.jar resources/MANIFEST.MF -C build/classes .
java -jar build/jar/forsend.jar
