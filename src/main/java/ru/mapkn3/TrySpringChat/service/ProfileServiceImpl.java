package ru.mapkn3.TrySpringChat.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mapkn3.TrySpringChat.dao.PrettyEntityDao;
import ru.mapkn3.TrySpringChat.model.Profile;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class ProfileServiceImpl implements ProfileService {
    private final static Logger logger = Logger.getLogger(ProfileServiceImpl.class);

    @Autowired
    private PrettyEntityDao profileDao;

    @Override
    @Transactional(readOnly = true)
    public Profile getProfile(Long id) {
        logger.debug("Getting profile with id = " + id);
        return profileDao.findById(Profile.class, id);
    }

    @Override
    public Long addNewProfile(Profile profile) {
        Long id = (Long) profileDao.save(profile);
        logger.debug("Id of new Profile: " + id);
        return id;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Profile> getAllProfiles() {
        List<Profile> profiles = profileDao.getAll(Profile.class);
        logger.debug("Get " + profiles.size() + " profiles:");
        for (Profile profile : profiles) {
            logger.debug(profile.toString());
        }
        return profiles;
    }

    @Override
    public Profile updateProfile(Profile profile) {
        Profile oldProfile =profileDao.findById(Profile.class, profile.primaryKey());
        Profile newProfile = profileDao.update(profile);
        logger.debug("Old profile: " + oldProfile.toString());
        logger.debug("New profile: " + newProfile.toString());
        return newProfile;
    }

    @Override
    public void deleteProfile(Profile profile) {
        logger.debug("Delete profile: " + profile.toString());
        profileDao.delete(profile);
    }
}
