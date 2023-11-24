package com.qdu.niit.library.entity;

import java.time.LocalDateTime;

public class ReadingRoom {
    int uid;//用户编号
    int bid;
    LocalDateTime start_time;
    LocalDateTime end_time;
    public ReadingRoom(int bid, int uid, LocalDateTime start_time, LocalDateTime end_time)
    {
        this.bid = bid;
        this.uid = uid;
        this.start_time = start_time;
        this.end_time = end_time;
    }
    public void setBid(int bid)
    {
        this.bid = bid;
    }
    public  void  setUid(int uid)
    {
        this.uid = uid;
    }
    public void setStart_time(LocalDateTime start_time)
    {
        this.start_time = start_time;
    }
    public void setEnd_time(LocalDateTime end_time)
    {
        this.end_time = end_time;
    }
    public int getUid()
    {
        return uid;
    }
    public int getBid()
    {
        return bid;
    }
    public LocalDateTime getEnd_time()
    {
        return end_time;
    }
    public LocalDateTime getStart_time()
    {
        return start_time;
    }
}
