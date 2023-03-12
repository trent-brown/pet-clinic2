package org.springframework.samples.petclinic.vet;

import org.springframework.data.repository.Repository;

public interface SpecialtyRepository extends Repository<Specialty, Integer> {

	Specialty save(Specialty entity);
}
