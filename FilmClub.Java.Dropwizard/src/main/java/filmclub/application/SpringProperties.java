package filmclub.application;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
class SpringProperties {
    private String profile;
    private String applicationContextClass;

    public void setProfile(String profile) {
        this.profile = profile;
        System.setProperty("spring.profiles.active", profile);
    }

    public String getProfile(){
        return profile;
    }

    public String getApplicationContextClass() {
        return applicationContextClass;
    }

    public void setApplicationContextClass(String applicationContextClass) {
        this.applicationContextClass = applicationContextClass;
        System.setProperty("spring.applicationclass", applicationContextClass);
    }
}
