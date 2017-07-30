package filmclub.application;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
class SpringProperties {
    private String profile;

    public void setProfile(String profile) {
        this.profile = profile;
        System.setProperty("spring.profiles.active", profile);
    }

    public String getProfile(){
        return profile;
    }

}
