package com.qdu.niit.library.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Borrowing//无主键
{
    private int uid;
    private int bid;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    /**
     *
     * @param uid
     * @param bid
     * @param start_time LocalDateTime类型
     * @param end_time LocalDateTime类型
     */
    public Borrowing(int uid, int bid, LocalDateTime start_time,LocalDateTime end_time)
    {
        this.uid = uid;
        this.bid = bid;
        this.start_time = start_time;
        this.end_time = end_time;
    }
    public void setUid(int uid)
    {
        this.uid = uid;
    }
    public void setBid(int bid)
    {
        this.bid = bid;
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
    public LocalDateTime getStart_time()
    {
        return start_time;
    }
    public LocalDateTime getEnd_time()
    {
        return  end_time;
    }
}