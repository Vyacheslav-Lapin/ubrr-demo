rm -r ./target/generated-sources/ws
mkdir ./target/generated-sources/ws
wsimport -d ./target/generated-sources/ws -p ru.ubrr.it.courses.java.formats.xml.wsclient -keep "http://localhost:1212/hello?wsdl"
rm ./target/generated-sources/ws/ru/ubrr/it/courses/java/formats/xml/wsclient/*.class
