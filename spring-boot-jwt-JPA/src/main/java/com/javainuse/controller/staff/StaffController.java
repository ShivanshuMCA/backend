package com.javainuse.controller.staff;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.model.staff.Staff;
import com.javainuse.model.staff.StaffDto;
import com.javainuse.service.staff.StaffService;

@RestController
@RequestMapping("/api/staff")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StaffController {

	@Autowired
	private StaffService staffService;

	@GetMapping("/available")
	public List<Staff> getAvailableStaff(@RequestParam String shift) {

		return staffService.findAllAvailable(shift);
	}
	
	@GetMapping("/all")
	public List<Staff> getAllStaff() {

		return staffService.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Staff> getStaff(@PathVariable Long id) {

		return staffService.findById(id);
	}

	@PostMapping("add")
	public ResponseEntity<?> saveStaff(@Valid @RequestBody StaffDto staff) throws Exception {
		Staff SavedStaff = null;
		SavedStaff = staffService.save(staff);
		return ResponseEntity.ok(SavedStaff);
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
        return ResponseEntity.ok().build();
    }
}
