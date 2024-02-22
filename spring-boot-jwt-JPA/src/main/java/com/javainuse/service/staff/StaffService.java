package com.javainuse.service.staff;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javainuse.model.staff.Staff;
import com.javainuse.model.staff.StaffDto;
import com.javainuse.repository.staff.StaffRepo;

@Service
public class StaffService {

	@Autowired
	private StaffRepo staffRepo;

	public List<Staff> findAllAvailable(String shift) {

		return staffRepo.findByShift(shift);
	}

	public Staff save(@Valid StaffDto staffDto) {

		Staff staff = new Staff();
		Staff savedUser = null;
		BeanUtils.copyProperties(staffDto, staff);
		savedUser = staffRepo.save(staff);
		return savedUser;
	}

	@Transactional
	public void deleteStaff(Long id) {
		staffRepo.deleteById(id);
	}

	public Optional<Staff> findById(Long id) {
		return staffRepo.findById(id);
	}

	public List<Staff> findAll() {

		return staffRepo.findAll();
	}

}
