package com.javainuse.repository.staff;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javainuse.model.staff.Staff;

@Repository
public interface StaffRepo extends JpaRepository<Staff, Long> {

//	List<Staff> findAllByShiftId(Long shiftId);

}
