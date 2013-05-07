del /s/q *.class
javac -classpath "./jars/mysql-connector-java.jar;./jars/jCalendar/lib/jcalendar-1.4.jar" ./src/database/*.java ./src/gui/*.java ./src/model/*.java ./src/data/*.java ./src/output/*.java -d "./bin"
cd bin
jar cf "../jars/myclasses.jar" "database/*.class" "gui/*.class" "model/*.class" "output/*.class" "data/*.class"
cd ..
pause