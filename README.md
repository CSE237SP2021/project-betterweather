# cse237-project
Members: Armando Q, Kevin A, Mau K, Maclee M

## What user stories were completed this iteration?

We have completed 5 user stories. Two of them being the API calls made to OpenWeather API. A third story allows the user to input a city name directly in the console/command line. The two API stories take the user input as a parameter and uses it to get the current weather at that city from the api and display it to the user. We then created and completed a fourth story which formats the cities inputed by the user; the API requires spaces to be sent in as "%20" (thus "New York" has to be converted to "New%20York" in order for the API to accept it). And the fifth story was to create a simple bash script which allows the users to run the program from the command line (note: for anyone testing our project this may be the easiest way to run our code since we have an external .jar library for our API which may require you to configure your build path if you try to run it through Eclipse). 

## What user stories do you intend to complete next iteration?
For the next iteration we will be displaying more weather data in our API call (such as sunshine, rain, etc.). As of now, we are only displaying the current temperature of a given city. 

We also intend to potentially create a more extensive menu for users to be able to input more than just a city to get a weather report (for example, we may allow users to put their Zip code and get the weather for that area). 


## Is there anything that you implemented but doesn't currently work?

Everything from our first iteration works correctly (although there is one thing we are not currently using; in our UserInput class we also have a method which allows for the user to give their coordinates, but we figured that it was unlikely that someone knew their coordinates so we will be replacing this option for more practical options in the following iteration). 

## What commands are needed to compile and run your code from the command line

The user simply needs to run the run.sh script (which is found in the "parent folder" of our project). 

## Iteration 1 Demo: 

<img src="http://g.recordit.co/SSy3nyg4k7.gif" width=1000 height=500><br>
