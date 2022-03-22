package ua.com.javenier.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "players")
public class Player extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private Integer age;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "start_of_career")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startOfCareer;
    private String nationality;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getStartOfCareer() {
        return startOfCareer;
    }

    public void setStartOfCareer(Date startOfCareer) {
        this.startOfCareer = startOfCareer;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Player{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", startOfCareer=" + startOfCareer +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}