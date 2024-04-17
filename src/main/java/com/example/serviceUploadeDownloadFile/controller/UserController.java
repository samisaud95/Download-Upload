package com.example.serviceUploadeDownloadFile.controller;

import com.example.serviceUploadeDownloadFile.DTO.DownloadUserProfilePictureDTO;
import com.example.serviceUploadeDownloadFile.entity.User;
import com.example.serviceUploadeDownloadFile.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping("/create")
  public ResponseEntity<User> create(@RequestBody  User user){
    return ResponseEntity.ok(userService.createUser(user));
  }
  @GetMapping("/getall")
  public ResponseEntity<List<User>> getAll(){
    return ResponseEntity.ok(userService.getAllUser());
  }
  @GetMapping("/get/{id}")
  public ResponseEntity<User> getFromId(@PathVariable Long id){
    Optional<User> userOptional = userService.getUserFromId(id);
    if(userOptional.isPresent()){
      return ResponseEntity.ok(userOptional.get());
    }else {
      return ResponseEntity.badRequest().build();
    }
  }
  @PutMapping("/update/{id}")
  public ResponseEntity<User> update(@PathVariable Long id,@RequestBody User user){
    Optional<User> userOptional = userService.updateUser(id,user);
    if(userOptional.isPresent()){
      return ResponseEntity.ok(userOptional.get());
    }else {
      return ResponseEntity.badRequest().build();
    }
  }
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<User> deleteFromId(@PathVariable Long id){
    Optional<User> userOptional = userService.deleteUserFromId(id);
    if(userOptional.isPresent()){
      return ResponseEntity.ok(userOptional.get());
    }else {
      return ResponseEntity.badRequest().build();
    }
  }
  @PatchMapping("/upload/profilepicture/{id}")
  public ResponseEntity<User> uploadProfilePicture(@PathVariable Long id , @RequestParam MultipartFile picture) throws IOException, IOException {
    Optional<User> userOptional = userService.uploadProfilePicture(id,picture);
    if(userOptional.isPresent()){
      return ResponseEntity.ok(userOptional.get());
    }else {
      return ResponseEntity.badRequest().build();
    }
  }
  @GetMapping("/download/profilepicture/{id}")
  public ResponseEntity<DownloadUserProfilePictureDTO> downloadProfilePicture(@PathVariable Long id, HttpServletResponse response) throws IOException {
    Optional<DownloadUserProfilePictureDTO> profilePictureDTOOptional = userService.downloadProfilePicture(id,response);
    if(profilePictureDTOOptional.isPresent()){
      return ResponseEntity.ok(profilePictureDTOOptional.get());
    }else {
      return ResponseEntity.badRequest().build();
    }
  }


}
