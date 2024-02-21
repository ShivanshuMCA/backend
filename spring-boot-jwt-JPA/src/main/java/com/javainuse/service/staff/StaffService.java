package com.javainuse.service.staff;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javainuse.model.staff.Shift;
import com.javainuse.model.staff.Staff;
import com.javainuse.model.staff.StaffDto;
import com.javainuse.model.staff.StaffRole;
import com.javainuse.repository.staff.StaffRepo;
import com.javainuse.repository.staff.StaffShiftRepo;

@Service
public class StaffService {

	@Autowired
	private StaffRepo staffRepo;

	@Autowired
	private StaffShiftRepo staffShiftRepo;

	public List<StaffRole> findAllAvailable(Long shiftId) {

		return staffShiftRepo.findAllByShiftId(shiftId);
	}

	public Staff save(@Valid StaffDto staffDto) {

		Staff staff = new Staff();
		Shift shift = new Shift();
		StaffRole role = new StaffRole();
		Staff savedUser = null;
		BeanUtils.copyProperties(staffDto, staff);
//		staff.setShift(shift);
		savedUser = staffRepo.save(staff);
//		if (!savedUser.equals(null)) {
//			StaffRole savedRole = staffShiftRepo.getByStaffId(savedUser.getId());
//			if (savedRole != null) {
//				
//				role.setStaffShiftId(savedRole.getStaffShiftId());
//				role.setShift(shift);
//				role.setStaff(staff);
//				staffShiftRepo.save(role);
//			} else {
//				role.setShift(shift);
//				role.setStaff(staff);
//				staffShiftRepo.save(role);
//			}
//			
//		}
		return savedUser;
	}

	@Transactional
	public void deleteStaff(Long id) {
		staffShiftRepo.deleteByStaffId(id);
		staffRepo.deleteById(id);
	}

	public Optional<Staff> findById(Long id) {
		return staffRepo.findById(id);
	}

	public List<Staff> findAll() {
		
		return staffRepo.findAll();
	}

}
