package com.example.demo.Models;
import javax.persistence.*;


@Entity
@Table(name = "joke")
public class Joke
{
    @Id
    @Column(name = "id")
    public String id;
    @Column(name = "completejoke")
    public String completejoke;
    public Joke()
    {

    }
    public Joke(String completejoke) {
        this.completejoke = completejoke;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompletejoke() {
        return completejoke;
    }

    public void setCompletejoke(String completejoke) {
        this.completejoke = completejoke;
    }

}

