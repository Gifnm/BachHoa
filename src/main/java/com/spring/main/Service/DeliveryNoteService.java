package com.spring.main.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.EntityDTO.DeliveryNoteDTO;
import com.spring.main.EntityDTO.DeliveryNoteWithTotalAmountDTO;
import com.spring.main.EntityDTO.DetailedDeliveryNoteDTO;
import com.spring.main.jpa.DeliveryNoteJPA;
import com.spring.main.jpa.DetailedDeliveryNoteJPA;
import com.spring.main.jpa.EmployeeJPA;
import com.spring.main.jpa.ProductJPA;
import com.spring.main.jpa.StoreJPA;
import com.spring.main.model.DeliveryNote;
import com.spring.main.model.DetailedDeliveryNote;
import com.spring.main.model.Employee;
import com.spring.main.model.Store;
import com.spring.main.util.IdGenerator;

@Service
public class DeliveryNoteService {
	@Autowired
	DeliveryNoteJPA deliveryNoteJPA;
	@Autowired
	private EmployeeJPA employeeJPA;
	@Autowired
	private StoreJPA storeJPA;
	@Autowired
	private ProductJPA productJPA;
	@Autowired
	private DetailedDeliveryNoteJPA detailDeliveryNoteJPA;
	@Autowired
	private DetailedDeliveryNoteService detailedDeliveryNoteService;

	/**
	 * Lay danh sach phieu nhap hang
	 * 
	 * @param store Object cua hang
	 */
	public List<DeliveryNote> getAll(int storeID) {
		List<DeliveryNote> list = deliveryNoteJPA.getAllByStore(storeID);
		return list;
	}

	public List<DeliveryNote> getAll2() {
		List<DeliveryNote> list = deliveryNoteJPA.findAll();
		return list;
	}

	public void setFinish(String id) {
		long yourmilliseconds = System.currentTimeMillis();
		// SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
		Date resultdate = new Date(yourmilliseconds);
		deliveryNoteJPA.setFinish(resultdate, id);

	}

	public List<DeliveryNoteWithTotalAmountDTO> getDeliveryNotesWithTotalAmount(int storeID) {
		List<Object[]> resultList1 = deliveryNoteJPA.getDeliveryNotesWithTotalAmount(storeID);
		List<Object[]> resultList2 = deliveryNoteJPA.getDeliveryNotesWithPurchaseHistory(storeID);
		List<DeliveryNoteWithTotalAmountDTO> deliveryNoteWithTotalAmountDTOList = new ArrayList<>();

		for (Object[] result : resultList1) {
			DeliveryNoteWithTotalAmountDTO dto = new DeliveryNoteWithTotalAmountDTO();
			dto.setId((String) result[0]);
			dto.setTimeCreate((Timestamp) result[1]);
			dto.setTimeCompleted((Timestamp) result[2]);
			dto.setTotalAmount((Double) result[3]);
			dto.setEmployeeId((Integer) result[4]);
			dto.setEmployeeName((String) result[5]);
			dto.setStatus((String) result[6]);
			dto.setProductCount(((BigInteger) result[7]).intValue());
			deliveryNoteWithTotalAmountDTOList.add(dto);
		}
		for (Object[] result : resultList2) {
			DeliveryNoteWithTotalAmountDTO dto = new DeliveryNoteWithTotalAmountDTO();
			dto.setId((String) result[0]);
			dto.setTimeCreate((Timestamp) result[1]);
			dto.setTimeCompleted((Timestamp) result[2]);
			dto.setTotalAmount((Double) result[3]);
			dto.setEmployeeId((Integer) result[4]);
			dto.setEmployeeName((String) result[5]);
			dto.setStatus((String) result[6]);
			dto.setProductCount(((BigInteger) result[7]).intValue());
			deliveryNoteWithTotalAmountDTOList.add(dto);
		}
		deliveryNoteWithTotalAmountDTOList
				.sort(Comparator.comparing(DeliveryNoteWithTotalAmountDTO::getTimeCreate).reversed());
		return deliveryNoteWithTotalAmountDTOList;
	}

	@Transactional
	public void createDeliveryNote(DeliveryNoteDTO deliveryNoteDTO) {
		// Convert DTO to entities
		DeliveryNote deliveryNote = new DeliveryNote();

		// Set properties from DTO to entity
		int employeeId = deliveryNoteDTO.getEmployeeID();
		int storeId = deliveryNoteDTO.getStoreID();

		// Generate custom ID
		String customId = IdGenerator.generateId();
		deliveryNote.setId(customId);
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		deliveryNote.setTimeCreate(ts);

		// Set store and employee (you may need to fetch them from the database using
		// storeID and employeeID)
		Optional<Employee> employeeOptional = employeeJPA.findById(employeeId);
		Optional<Store> storeOptional = storeJPA.findById(storeId);

		if (employeeOptional.isPresent() && storeOptional.isPresent()) {
			deliveryNote.setEmployee(employeeOptional.get());
			deliveryNote.setStore(storeOptional.get());
		} else {
			// Handle case where employee or store is not found
			throw new RuntimeException("Employee or Store not found");
		}
		// Save the deliveryNote entities before
		deliveryNoteJPA.save(deliveryNote);
		System.out.println("Save deliveryNote Successfully \n" + deliveryNote);

		// Save the list detailedDeliveryNotes one by one
		int index = 0;
		for (DetailedDeliveryNoteDTO detailedDeliveryNoteDTO : deliveryNoteDTO.getDetailedDeliveryNotes()) {
			DetailedDeliveryNote detailedDeliveryNote = new DetailedDeliveryNote();
			// Set properties from DTO to entity
			detailedDeliveryNote.setId(customId);
			detailedDeliveryNote.setProductID(detailedDeliveryNoteDTO.getProductID());
			detailedDeliveryNote.setIndex(index += 1);
			detailedDeliveryNote.setProduct(productJPA.getById(detailedDeliveryNoteDTO.getProductID()));
			detailedDeliveryNote.setQuantity(detailedDeliveryNoteDTO.getQuantity());
			// Set deliveryNote
			detailedDeliveryNote.setDeliveryNote(deliveryNote);

			// Save new delivery note
			detailedDeliveryNoteService.insert(detailedDeliveryNote);
			System.out.println("Save detailDeliveryNote Successfully \n" + detailedDeliveryNote);
		}

	}

	@Transactional
	public void updateDeliveryNote(String deliveryNoteId, List<DetailedDeliveryNoteDTO> detailDeliveryNoteDTOs) {
		// Fetch the existing delivery note with the provided ID
		Optional<DeliveryNote> deliveryNoteOptional = deliveryNoteJPA.findById(deliveryNoteId);

		if (deliveryNoteOptional.isPresent()) {
			DeliveryNote deliveryNote = deliveryNoteOptional.get();

			// Delete the list of old detailed delivery notes
			List<DetailedDeliveryNote> detailedDeliveryNotes = detailDeliveryNoteJPA.getAllByID(deliveryNoteId);
			for (DetailedDeliveryNote element : detailedDeliveryNotes) {
				detailDeliveryNoteJPA.delete(element);
			}
			// Insert new detailed delivery notes
			for (DetailedDeliveryNoteDTO detailedDeliveryNoteDTO : detailDeliveryNoteDTOs) {
				DetailedDeliveryNote detailedDeliveryNote = new DetailedDeliveryNote();

				// Update properties from DTO to entity
				detailedDeliveryNote.setId(deliveryNote.getId());
				detailedDeliveryNote.setProductID(detailedDeliveryNoteDTO.getProductID());
				detailedDeliveryNote.setIndex(detailedDeliveryNoteDTO.getIndex());
				detailedDeliveryNote.setQuantity(detailedDeliveryNoteDTO.getQuantity());
				detailedDeliveryNote.setProduct(productJPA.getById(detailedDeliveryNoteDTO.getProductID()));
				// Set deliveryNote
				detailedDeliveryNote.setDeliveryNote(deliveryNote);

				// Save the updated detailed delivery note
				detailedDeliveryNoteService.update(detailedDeliveryNote);
			}
		} else {
			throw new RuntimeException("Delivery Note not found");
		}
	}

	@Transactional
	public void deleteDeliveryNoteWithDetails(String deliveryNoteId) {
		// Fetch the DeliveryNote entity
		DeliveryNote deliveryNote = deliveryNoteJPA.findById(deliveryNoteId)
				.orElseThrow(() -> new EntityNotFoundException("DeliveryNote not found with id: " + deliveryNoteId));

		// Fetch the DetailedDeliveryNote records associated with the DeliveryNote
		List<DetailedDeliveryNote> detailedDeliveryNotes = detailDeliveryNoteJPA.getAllByID(deliveryNoteId);

		// Delete each DetailedDeliveryNote record
		for (DetailedDeliveryNote detailedDeliveryNote : detailedDeliveryNotes) {
			detailDeliveryNoteJPA.delete(detailedDeliveryNote);
		}

		// Delete the DeliveryNote
		deliveryNoteJPA.delete(deliveryNote);
		System.out.println("service thanh cong");
	}
}
