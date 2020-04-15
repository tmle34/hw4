package com.example.demo.Models;
import org.springframework.data.repository.CrudRepository;


public interface JokeRepo extends CrudRepository<Joke, String> {
}
