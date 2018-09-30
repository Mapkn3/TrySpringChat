package ru.mapkn3.TrySpringChat.service;

import ru.mapkn3.TrySpringChat.model.Profile;

import java.util.List;

public interface ProfileService {
    Profile getProfile(Long id);

    Long addNewProfile(Profile profile);

    List<Profile> getAllProfiles();

    Profile updateProfile(Profile profile);

    void deleteProfile(Profile profile);
}
