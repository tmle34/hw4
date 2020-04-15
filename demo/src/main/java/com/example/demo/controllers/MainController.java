package com.example.demo.controllers;

import com.example.demo.Models.Joke;
import com.example.demo.Models.JokeRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Optional;
import java.util.UUID;

@Controller
public class MainController {

    @Autowired
    JokeRepo jokeRepo;

    @RequestMapping("/")
    public ModelAndView doHome(){
        ModelAndView mv = new ModelAndView("index");
        String jokeNumber = "";
        String jokeString = "";
        String completeJoke = "";
        String apikey = "0aa2a43b96mshd59f162ce4c0a9ap18c65fjsn2959029b91ed";
        try
        {
            URL url = new URL("https://matchilling-chuck-norris-jokes-v1.p.rapidapi.com/jokes/random");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.setRequestMethod("GET");
            connection.setRequestProperty("x-rapidapi-host", "matchilling-chuck-norris-jokes-v1.p.rapidapi.com");
            connection.setRequestProperty("x-rapidapi-key", apikey);
            connection.setRequestProperty("accept", "");
            connection.connect();
            BufferedReader r  = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                json.append(line);
            }
            JSONObject obj = new JSONObject(json.toString());
            jokeString = obj.getString("value");
            jokeNumber = obj.getString("id");
            completeJoke = jokeString + ": " + jokeNumber;
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        mv.addObject("listfor", this.jokeRepo.findAll());
        mv.addObject("joke", completeJoke);
        return mv;
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@RequestParam("completeJoke") String completeJoke){
        ModelAndView mv = new ModelAndView("redirect:/");
        Joke joketoSave ;
        joketoSave = new Joke();
        joketoSave.setId(UUID.randomUUID().toString());
        joketoSave.setCompletejoke(completeJoke);
        jokeRepo.save(joketoSave);
        mv.addObject("jokelist", jokeRepo.findAll());
        return mv;
    }
/*
    @RequestMapping( value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") String id){
        ModelAndView mv = new ModelAndView("redirect:/");
        employeesRepo.deleteById(id);
        return mv;
    }
    */
}
