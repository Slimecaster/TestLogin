package com.example.testlogin.DBcontroller;

import com.example.testlogin.Model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Optional;

@org.springframework.stereotype.Repository
public class Repository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Repository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public MyUser createUpdateUser (MyUser myUser){
        try {
            if(myUser.getUserId()==null){
                String sql="INSERT INTO user(userid,fname,sname,password,email,phoneNumber,weight,height,age,gender,activityLevel,goal,role) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                jdbcTemplate.update(sql, myUser.getUserId(), myUser.getFname(), myUser.getSname(), myUser.getPassword(), myUser.getEmail(), myUser.getPhoneNumber(), myUser.getWeight(), myUser.getHeight(), myUser.getAge(), myUser.getGender(), myUser.getActivityLevel(), myUser.getGoal(), myUser.getRole());
            }else{
                String sql = "UPDATE user SET fname=?,sname=?,password=?,email=?,phoneNumber=?,weight=?,height=?,age=?,gender=?,activityLevel=?,goal=?,role=? WHERE userId="+String.valueOf(myUser.getUserId());
                jdbcTemplate.update(sql, myUser.getUserId(), myUser.getFname(), myUser.getSname(), myUser.getPassword(), myUser.getEmail(), myUser.getPhoneNumber(), myUser.getWeight(), myUser.getHeight(), myUser.getAge(), myUser.getGender(), myUser.getActivityLevel(), myUser.getGoal(), myUser.getRole());
            }return myUser;
        } catch(DataAccessException e){
            throw new RuntimeException("Error creating user", e);
        }
    }

    public Optional<MyUser> findUserByEmail(String email) {
        try {
            String sql = "SELECT * FROM user WHERE email = ?";
            MyUser user = jdbcTemplate.queryForObject(sql, new Object[]{email}, userRowMapper());
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty(); // Returner tomt Optional hvis ingen bruger er fundet
        }
    }
    private RowMapper<MyUser> userRowMapper() {
        return (rs, rowNum) -> {
            MyUser user = new MyUser();
            user.setUserId(rs.getLong("userId"));
            user.setFname(rs.getString("fname"));
            user.setSname(rs.getString("sname"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setPhoneNumber(rs.getString("phoneNumber"));
            user.setWeight(rs.getDouble("weight"));
            user.setHeight(rs.getInt("height"));
            user.setAge(rs.getInt("age"));
            user.setGender(rs.getInt("gender"));
            user.setActivityLevel(rs.getInt("activityLevel"));
            user.setGoal(rs.getInt("goal"));
            user.setRole(rs.getString("role"));

            return user;
        };
    }
}
