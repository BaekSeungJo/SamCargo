package com.sclnetworks.backend.controller;

/**
 * Created by J.minjun on 2019.07.3
 *
 */

import com.sclnetworks.backend.model.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {



    //------------------------------------------------------------------------------------------------------------------
    // User
    //------------------------------------------------------------------------------------------------------------------
    @GetMapping(value = "/user/", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUser(@RequestParam Map<String,String> param) {

        Map<String, Object> resultMap = new HashMap<String,Object>();


        return new ResponseEntity<Map<String,Object>>(resultMap, HttpStatus.OK);
    }

    @PostMapping(value = {"/user","/user/"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestParam Map<String, String> param) {

        Map<String, Object> resultMap = new HashMap<String,Object>();


        return new ResponseEntity<Map<String,Object>>(resultMap, HttpStatus.OK);
    }

    @PutMapping(value = {"/user","/user/"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@RequestBody UserEntity user){

        Map<String, Object> resultMap = new HashMap<String,Object>();


        return new ResponseEntity<Map<String,Object>>(resultMap, HttpStatus.OK);
    }

    @DeleteMapping(value = {"/user","/user/"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUser(@RequestBody UserEntity user){
        Map<String, Object> resultMap = new HashMap<String,Object>();


        return new ResponseEntity<Map<String,Object>>(resultMap, HttpStatus.OK);
    }

}
