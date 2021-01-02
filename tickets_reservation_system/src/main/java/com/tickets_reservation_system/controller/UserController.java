package com.tickets_reservation_system.controller;

import com.tickets_reservation_system.entity.User;
import com.tickets_reservation_system.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private JwtUtil jwtUtil;

    UserController() {
        this.jwtUtil = new JwtUtil();
    }

    /*
    This endpoint enable user to edit his data in the database.
    */
    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT, value = "/edit/{changePw}")
    public ResponseEntity<Object> edit(@RequestBody User modifiedUser,
                                       @PathVariable("changePw") Boolean changePw) {
        // Example get authenticated user info.
        Object user = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String query = "UPDATE match_reservation_system.user SET ";
        if (changePw) {
            query = query + "pw = '" + passwordEncoder.encode(modifiedUser.getPw()) + "', ";
        }
        query = query
                + "fname = '" + modifiedUser.getFname() + "', "
                + "lname = '" + modifiedUser.getLname() + "', "
                + "dob = '" + modifiedUser.getDob() + "', "
                + "gender = '" + modifiedUser.getGender() + "', "
                + "city = '" + modifiedUser.getCity() + "', "
                + "address = '" + modifiedUser.getAddress() + "', "
                + "user_type = '" + modifiedUser.getUser_type() + "' "
                + "WHERE username = '" + ((User) user).getUsername() + "';";

        try {
            jdbcTemplate.execute(query);
            query = "Select * from match_reservation_system.user where " +
                    "username = '" + ((User) user).getUsername() + "' ";
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
                User newUserInfo = userFromDB.get(0);
                String token = this.jwtUtil.generateToken(newUserInfo.getUsername());
                List<Object> response = new ArrayList<>();
                Dictionary tokenDic = new Hashtable();
                tokenDic.put("token", token);
                Dictionary userDic = new Hashtable();
                userDic.put("user", newUserInfo);
                response.add(tokenDic);
                response.add(userDic);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            } catch (Exception e) {
                System.out.println("Error:");
                System.out.println(e);
                return new ResponseEntity<>("Error", HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            System.out.println("Error:");
            System.out.println(e);
            return new ResponseEntity<>("Error", HttpStatus.CONFLICT);
        }
    }


}
