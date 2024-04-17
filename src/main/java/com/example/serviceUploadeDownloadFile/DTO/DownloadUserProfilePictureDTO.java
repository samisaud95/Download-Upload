package com.example.serviceUploadeDownloadFile.DTO;

import com.example.serviceUploadeDownloadFile.entity.User;

public class DownloadUserProfilePictureDTO {
    private User user;
    private byte[] profilePicture;

    public DownloadUserProfilePictureDTO() {
    }

    public DownloadUserProfilePictureDTO(User user, byte[] profilePicture) {
        this.user = user;
        this.profilePicture = profilePicture;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }
}
