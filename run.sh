#!/bin/bash

#runs our project
javac -cp lib/java-json.jar src/betterWeather/*.java
java -cp "src:lib/*" betterWeather.WeatherApp

echo "Now running tests..."

#runs our tests 
javac -cp "lib/*" src/betterWeather/*.java
javac -cp "src:lib/*" src/tests/*.java

echo "If no errors appear, then our tests successfully ran!"