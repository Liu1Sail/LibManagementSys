package com.qdu.niit.library.service.impl;

import com.qdu.niit.library.entity.Borrowing;

import java.time.LocalDateTime;

public interface BorrowingService {
    boolean ifHaveByUid(int uid);
    boolean ifHaveByBid(int bid);
    Borrowing[] findAllByEndTime(LocalDateTime end_time);
    Borrowing[] findAllByUid(int uid);
    Borrowing[] findOneByBid(int bid);
}
