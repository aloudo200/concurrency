package org.personal.concurrency.exercises.dependencies;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class CustomerProfile {

    private static final AtomicLong idCounter  = new AtomicLong(100L);
    private final Long id;

    @Getter
    private String name;

    @Getter
    private List<Long> likesReceived = new ArrayList<>();

    @Getter
    private List<CustomerProfile> matchedProfiles = new ArrayList<>();

    private final List<String> privatePhotos = List.of("private1", "private2");


    public CustomerProfile(String name) {
        this.name = name;
        this.id = idCounter.incrementAndGet();
    }

    public long getId() {
        return this.id;
    }

    public void addMatch(CustomerProfile match) {
        this.matchedProfiles.add(match);
    }

    public void addLike(Long id) {
        this.likesReceived.add(id);
    }

    public String getPrivatePhotos(CustomerProfile incomingRequestor) {

        List<String> privatePhotosList = new ArrayList<>();

        if (this.incomingRequestorCanViewPrivatePhotos(incomingRequestor)) {
            privatePhotosList = Optional.of(this.privatePhotos).orElseGet(ArrayList::new);
        }

        return privatePhotosList.toString();

    }

    public boolean incomingRequestorCanViewPrivatePhotos(CustomerProfile profile) {
        if (this.matchedProfiles.contains(profile)) {
            System.out.printf("Success, able to view private photos since you matched with %s\n", profile.getName());
            return true;
        } else {
            System.out.println("Cannot view private photos because you haven't matched with this user!");
            return false;
        }
    }


}
