package com.tickets_reservation_system.controller;

import com.tickets_reservation_system.entity.User;
import com.tickets_reservation_system.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private JwtUtil jwtUtil;

    AuthController(){
        this.jwtUtil = new JwtUtil();
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        String query = "INSERT INTO match_reservation_system.user VALUES ("
                + "'" + user.getUsername() + "', "
                + "'" + passwordEncoder.encode(user.getPw()) + "', "
                + "'" + user.getFname() + "', "
                + "'" + user.getLname() + "', "
                + "'" + user.getDob() + "', "
                + "'" + user.getGender() + "', "
                + "'" + user.getCity() + "', "
                + "'" + user.getAddress() + "', "
                + "'" + user.getMail() + "', "
                + "'" + user.getUser_type() + "', "
                + "0"                           // User not approved yet
                + ");";
        //System.out.println(query);
        try {
            jdbcTemplate.execute(query);
            return new ResponseEntity<>("User Registered successfully", HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println("Error:");
            System.out.println(e);
            return new ResponseEntity<>("User Not Registered, Either username or email" +
                    " already exists in the system", HttpStatus.FORBIDDEN);
        }

    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/signin")
    public  ResponseEntity<Object> signin(@RequestBody User user) {
        String query = "Select * from match_reservation_system.user where " +
                "username = '" + user.getUsername() +"' ";
        try {
            List<User> userFromDB = this.jdbcTemplate.query(query,
                    new RowMapper<User>() {
                        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                            User u = new User();
                            u.setUsername(rs.getString("username"));
                            u.setPw(rs.getString("pw"));
                            u.setApproved(rs.getInt("approved"));
                            u.setAddress(rs.getString("address"));
                            u.setCity(rs.getString("city"));
                            u.setDob(rs.getDate("dob").toString());
                            u.setFname(rs.getString("fname"));
                            u.setLname(rs.getString("lname"));
                            u.setMail(rs.getString("mail"));
                            u.setGender(rs.getString("gender"));
                            u.setUser_type(rs.getString("user_type"));
                            return u;
                        }
                    });
            // Make sure that there is a user with the given username
            if(userFromDB.size() == 0) {
                return new ResponseEntity<>("Incorrect username", HttpStatus.FORBIDDEN);
            }
            // Make sure that the password is correct.
            if(! passwordEncoder.matches(user.getPw(), userFromDB.get(0).getPw())) {
                return new ResponseEntity<>("Incorrect password", HttpStatus.FORBIDDEN);
            }
            // Make sure that the user is approved.
            if(userFromDB.get(0).getApproved() == 0) {
                return new ResponseEntity<>("User not approved yet", HttpStatus.UNAUTHORIZED);
            }
            String token = this.jwtUtil.generateToken(user.getUsername());
            List<Object> response = new ArrayList<>();
            Dictionary tokenDic = new Hashtable();
            tokenDic.put("token", token);
            Dictionary userDic = new Hashtable();
            userDic.put("user", userFromDB.get(0));
            response.add(tokenDic);
            response.add(userDic);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
        catch(Exception e) {
            System.out.println("Error:");
            System.out.println(e);
            return new ResponseEntity<>("Error", HttpStatus.FORBIDDEN);
        }
    }
}
