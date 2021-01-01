package com.tickets_reservation_system.controller;

import com.tickets_reservation_system.entity.History;
import com.tickets_reservation_system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class ApiController {
    /*
    @Autowired
    JdbcTemplate jdbcTemplate;



    //Add new user to database and return the added user
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/add-user")
    public List<User> addUser() {
        jdbcTemplate.execute("INSERT INTO test_search_engine.users VALUES (NULL)");

        List<User> user = this.jdbcTemplate.query(
                "select * from test_search_engine.users ;",
                new RowMapper<User>() {
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        User u = new User(rs.getInt("id"));
                        return u;
                    }
                });
        return user;
    }

    //Add to history of a user
    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/history")
    public void addHistory(@RequestBody History history) {
        String q = "INSERT IGNORE INTO test_search_engine.history (user, page) VALUES ("+String.valueOf(history.getUser())+
                ","+String.valueOf(history.getPage())+");";
        System.out.println(q);
        jdbcTemplate.execute(q);

        int times = jdbcTemplate.queryForObject("select times from test_search_engine.history where (user ="+
                        String.valueOf(history.getUser())+") and (page =" +String.valueOf(history.getPage())+")",
                (us, RN) -> us.getInt("times"));
        times++;

        String q1 =  "UPDATE test_search_engine.history SET times = "+String.valueOf(times)+"  WHERE (user ="+String.valueOf(history.getUser())+
                ") and (page ="+String.valueOf(history.getPage())+");";
        System.out.println(q1);
        jdbcTemplate.execute(q1);

    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/get-results")
    public List<Integer> getResults(@RequestParam(name = "text") String text, @RequestParam(name = "page") int page,
                                                     @RequestParam(name = "user") int user ) {

        text = text.replace("\"", " \" ");

        String words = "";
        List<String> impWords = new ArrayList<String>();
        List<String> phrases = new ArrayList<String>();
        List<List<String>> impPhraseWords = new ArrayList<List<String>>();

        String[] splits = text.split(" (?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        for (int i = 0; i < splits.length; i++) {
            String split = splits[i].trim();
            if (split.length() != 0) {

                if (split.charAt(0) == '"' && split.charAt(split.length() - 1) == '"') {
                    phrases.add(split);
                }
                else {
                    words = words.concat(split + " ");
                }
            }
        }

        int offset = (page - 1)*20;
        return null;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/get-images")
    public List<String> getImages(@RequestParam(name = "text") String text, @RequestParam(name = "page") int page,
                         @RequestParam(name = "user") int user ) {

        List<String> impWords = new ArrayList<String>();
        int offset = (page - 1)*20;
        return null;
    }
    */
}
