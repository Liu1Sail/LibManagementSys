package com.qdu.niit.library.entity;

import java.time.LocalDateTime;

public class Returning {
    public Returning(int uid,int bid,LocalDateTime back_time)
    {
        this.uid = uid;
        this.bid = bid;
        this.back_time = back_time;
    }
    private int uid;
    private int bid;
    private LocalDateTime back_time;
    public void setUid(int uid)
    {
        this.uid = uid;
    }
    public void setBid(int bid)
    {
        this.bid = bid;
    }
    public void setBack_time(LocalDateTime back_time)
    {
        this.back_time = back_time;
    }
    public int getUid()
    {
        return uid;
    }
    public int  getbid()
    {
        return bid;
    }
    public LocalDateTime getBack_time()
    {
        return back_time;
    }
}
