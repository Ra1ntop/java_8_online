javac -sourcepath ./src -d build/classes -cp ./libs/commons-text-1.10.0.jar:./libs/commons-lang3-3.13.0.jar ./src/ua/com/alevel/ForSend.java
java -cp build/classes/:./libs/commons-text-1.10.0.jar:./libs/commons-lang3-3.13.0.jar ua.com.alevel.ForSend
#java -cp build/classes/:./libs/commons-lang3-3.13.0.jar ua.com.alevel.Hello
