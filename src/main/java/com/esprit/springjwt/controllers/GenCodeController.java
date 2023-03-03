package com.esprit.springjwt.controllers;

import com.esprit.springjwt.entity.GenCode;
import com.esprit.springjwt.entity.User;
import com.esprit.springjwt.payload.response.MessageResponse;
import com.esprit.springjwt.repository.GencodeRepository;
import com.esprit.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/resetpassword")

public class GenCodeController {

  @Autowired
  GencodeRepository gencodeRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder encoder;

  public String generateCode() {
    String code = "";
    for (int i = 0; i < 6; i++) {
      code += (int) (Math.random() * 10);
    }
    return code;
  }

  @PostMapping("/generatecode")
//post with check if email exist in user model
  public ResponseEntity<?> generateCode(@RequestBody GenCode genCode) {
    if (userRepository.existsByEmail(genCode.getEmail())) {
      gencodeRepository.save(genCode);
      return ResponseEntity.ok(new MessageResponse("Code generated successfully!"));
    } else {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is not found!"));
    }
  }

  @GetMapping("/checkcode/{code}")
//check if code exist
  public ResponseEntity<?> checkCode(@PathVariable String code) {
    if (gencodeRepository.existsByCode(code)) {
      return ResponseEntity.ok(new MessageResponse("Code is correct!"));
    } else {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Code is not correct!"));
    }
  }

  // update password by email
  @PatchMapping("/updatepassword/{email}")
  public ResponseEntity<?> updatePassword(@PathVariable String email, @RequestBody User user) {
    if (userRepository.existsByEmail(email)) {
      User user1 = userRepository.findByEmail(email);
      user1.setPassword(encoder.encode(user.getPassword()));
      userRepository.save(user1);
      return ResponseEntity.ok(new MessageResponse("Password updated successfully!"));
    } else {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is not found!"));
    }
  }



}
