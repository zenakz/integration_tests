package edu.iis.mto.integration.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.iis.mto.integration.dto.PostData;
import edu.iis.mto.integration.dto.UserData;

@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
@Service
public class BlogDataFinder implements DataFinder {

    @Override
    public UserData getUserData(Long userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UserData> findUsers(String searchString) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PostData getPost(Long userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<PostData> getUserPosts(Long userId) {
        // TODO Auto-generated method stub
        return null;
    }

}
