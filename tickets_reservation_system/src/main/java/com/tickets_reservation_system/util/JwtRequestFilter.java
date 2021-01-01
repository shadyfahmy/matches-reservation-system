package com.tickets_reservation_system.util;

import com.tickets_reservation_system.entity.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class JwtRequestFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;
    @Autowired
    JdbcTemplate jdbcTemplate;

    public JwtRequestFilter() {
        this.jwtUtil = new JwtUtil();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
    throws ServletException, IOException {

        String authToken = request.getHeader("authorization");
        String username = null;
        if (authToken != null) {
            try {
                username = this.jwtUtil.extractUsername(authToken);
            } catch (IllegalArgumentException e) {
                logger.error("an error occured during getting username from token", e);
            } catch (ExpiredJwtException e) {
                logger.warn("the token is expired and not valid anymore", e);
            } catch(SignatureException e){
                logger.error("Authentication Failed. Username or Password not valid.");
            }
        } else {
            logger.warn("couldn't find token");
        }
        User user = new User();
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            String query = "Select * from match_reservation_system.user where " +
                    "username = '" + username + "' ";
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
                user = userFromDB.get(0);
            } catch (Exception e) {
                logger.error(e);
            }

            if (this.jwtUtil.validateToken(authToken, user)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        user, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                logger.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }

}
