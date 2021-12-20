package com.jobJunior.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobJunior.os.dtos.OSDTO;
import com.jobJunior.os.modelo.OS;
import com.jobJunior.os.repository.OSRepository;
import com.jobJunior.os.services.exception.ObjectNotFoundException;

@Service
public class OsService {

	@Autowired
	private OSRepository osRepository;

	public OS findById(Integer id) {
		Optional<OS> osOptional = osRepository.findById(id);
		return osOptional.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado, de Id: " + id + " Tipo: " + OS.class.getName()));
	}
	
	public List<OS> findAll(){
		return osRepository.findAll();
	}

	public OS create(@Valid OSDTO osDTO) {
		
		return null;
	}

	public OS fromDTO(@Valid OSDTO osDTO) {

		return null;
	}
}
