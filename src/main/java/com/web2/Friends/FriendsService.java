package com.web2.Friends;

import com.web2.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendsService {

    private final FriendsRepository friendsRepository;

    public List<Friends> getFriends(User user, double lat, double lng, double radius) {

        int count = 0;
        List<Friends> people = new ArrayList<>();
        List<Friends> friends = friendsRepository.findNearbyFriends(lat, lng, radius);

        // 5명만 같은 국적의 사람 조회

        for (Friends friend : friends) {
            if(user.getNationality() == friend.getNationality()) {
                people.add(friend);
                count++;
            }
            if(count == 5) {
                break;
            }
        }

        return people;

    }



}
