package com.zinbo.movie.shared;

import org.springframework.data.annotation.Id;

public class Movie {
    @Id
    public int id;
    public String name;
    public String imdb;
    public String username;
}
