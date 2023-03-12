package org.springframework.samples.petclinic.vet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class VetService {

	@Autowired
	VetRepository vetRepository;
	@Autowired
	SpecialtyRepository specialtyRepository;

	public Vet createVet(String firstName, String lastName, List<Specialty> specialties) {
		Vet vet = new Vet();
		vet.setFirstName(firstName);
		vet.setLastName(lastName);

		final List<Specialty> specialtiesToAdd = new ArrayList<>();
		specialties.forEach(specialty -> {
			if(specialty.isNew()) {
				specialtiesToAdd.add(specialtyRepository.save(specialty));
			}
			else {
				specialtiesToAdd.add(specialty);
			}
		});
		specialtiesToAdd.forEach(vet::addSpecialty);

		return vetRepository.save(vet);
	}

	public Set<String> getAllVetSpecialtiesInClinic() {
		return vetRepository.findAll().stream().map(Vet::getSpecialties).flatMap(List::stream).map(Specialty::getName).collect(Collectors.toSet());
	}
}
