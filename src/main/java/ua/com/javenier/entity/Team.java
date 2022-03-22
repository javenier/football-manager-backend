package ua.com.javenier.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teams")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Team extends BaseEntity {

    private String name;
    @Column(name = "commission_fee")
    private Integer commissionFee;
    @Column(name = "transfer_balance")
    private Long transferBalance;
    private String country;
    private String city;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "foundation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date foundationDate;
    @Column(name = "coach_name")
    private String coachName;
    @OneToMany(mappedBy = "team", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Set<Player> players;

    public Team() {
        this.players = new HashSet<>();
        this.transferBalance = 10000000L;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCommissionFee() {
        return commissionFee;
    }

    public void setCommissionFee(Integer commissionFee) {
        this.commissionFee = commissionFee;
    }

    public Long getTransferBalance() {
        return transferBalance;
    }

    public void setTransferBalance(Long transferBalance) {
        this.transferBalance = transferBalance;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(Date foundationDate) {
        this.foundationDate = foundationDate;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", commissionFee=" + commissionFee +
                ", transferBalance=" + transferBalance +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", foundationDate=" + foundationDate +
                ", coachName='" + coachName + '\'' +
                '}';
    }
}