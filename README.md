# popmovie 
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/5e118b6c16a143448563735b0810d374)](https://www.codacy.com/app/heiho1/popmovie?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=heiho1/popmovie&amp;utm_campaign=Badge_Grade)
A simple android app integrating with themoviedb.org using retrofit and butterknife while demonstrating caching of all data, images and video and unit/integration testing of major  pieces.

# Installation
Create a keys.properties file in your src/main/resources directory and define the key "the_movie_db" within that file to have the value of your themoviedb.org API key.  You must create a free account on themoviedb.org to have access to such an API key.

# Testing
Unit tests are runnable from src/test/groovy and integration tests are runnable from src/androidTest/java.
