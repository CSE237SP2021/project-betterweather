#!/bin/bash

javac -cp lib/java-json.jar src/betterWeather/*.java
java -cp "src:lib/*" betterWeather.WeatherApp
