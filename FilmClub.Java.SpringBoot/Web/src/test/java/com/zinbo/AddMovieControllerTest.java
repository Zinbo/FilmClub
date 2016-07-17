package com.zinbo;

import com.zinbo.shared.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
@WebIntegrationTest
public class AddMovieControllerTest {

    RestTemplate restTemplate = new TestRestTemplate();
    String addMovieUrl = "http://localhost:8080/movies";

    @Test
    public void addMovie_withValidMovie_returns201(){
        // arrange
        Movie movieToAdd = new Movie("Movie Name", "imdb id", "Movie Desc");
        HttpStatus expected = HttpStatus.CREATED;

        // act
        ResponseEntity<Movie> returnedMovieEntity = restTemplate.postForEntity(addMovieUrl, movieToAdd, Movie.class);
        HttpStatus actual = returnedMovieEntity.getStatusCode();

        // assert
        assertThat(expected, equalTo(actual));
    }

    @Test
    public void addMovie_withValidMovie_returnsMovieWithIdNot0(){
        // arrange
        Movie movieToAdd = new Movie("Movie Name", "imdb id", "Movie Desc");
        Long invalidId = 0L;

        // act
        ResponseEntity<Movie> returnedMovieEntity = restTemplate.postForEntity(addMovieUrl, movieToAdd, Movie.class);
        Movie movie = returnedMovieEntity.getBody();
        Long actual = movie.getId();

        // assert
        assertThat(actual, not(invalidId));
    }

    @Test
    public void addMovie_with2MoviesWithSameImdbId_returnsConflict(){
        // arrange
        Movie movieToAdd = new Movie("Movie Name", "imdb id", "Movie Desc");
        HttpStatus expected = HttpStatus.CONFLICT;

        // act
        restTemplate.postForEntity(addMovieUrl, movieToAdd, Movie.class);
        ResponseEntity<Movie> returnedSecondMovieEntity = restTemplate.postForEntity(addMovieUrl, movieToAdd, Movie.class);
        HttpStatus actual = returnedSecondMovieEntity.getStatusCode();

        // assert
        assertThat(actual, equalTo(expected));
    }
}
