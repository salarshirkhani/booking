package com.example.demo;

import javax.persistence.*;

@Entity(name = "request")
public class request {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idr;
    @Column(name = "userId")
    private String userId;
    @Column(name = "guestId")
    private String guestId;
    @Column(name = "tik")
    private String tik;

    public String getIdr() {
        return idr;
    }

    public void setIdr(String idr) {
        this.idr = idr;
    }

    public String getUserid() {
        return userId;
    }

    public void setUserid(String userId) {
        this.userId = userId;
    }

    public String getGuestid() {
        return guestId;
    }

    public void setGuestid(String guestId) {
        this.guestId = guestId;
    }

    public String getTik() {
        return tik;
    }

    public void setTik(String tik) {
        this.tik = tik;
    }
}
