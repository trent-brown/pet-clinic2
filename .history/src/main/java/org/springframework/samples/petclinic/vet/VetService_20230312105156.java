package org.springframework.samples.petclinic.vet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class VetService {

	@Autowired
	VetRepository vetRepository;

	public Vet createVet(String firstName, String lastName, List<Specialty> specialties) {
		Vet vet = new Vet();
		vet.setFirstName(firstName);
		vet.setLastName(lastName);
		specialties.forEach(vet::addSpecialty);

		return vetRepository.save(vet);
	}
}
