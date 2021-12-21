package com.jobJunior.os.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobJunior.os.dtos.OSDTO;
import com.jobJunior.os.modelo.Cliente;
import com.jobJunior.os.modelo.OS;
import com.jobJunior.os.modelo.Tecnico;
import com.jobJunior.os.modelo.enuns.Prioridade;
import com.jobJunior.os.modelo.enuns.Status;
import com.jobJunior.os.repository.OSRepository;
import com.jobJunior.os.services.exception.ObjectNotFoundException;

@Service
public class OsService {

	@Autowired
	private OSRepository osRepository;

	@Autowired
	private TecnicoService tecnicoService;

	@Autowired
	private ClienteService clienteService;

	public OS findById(Integer id) {
		Optional<OS> osOptional = osRepository.findById(id);
		return osOptional.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado, de Id: " + id + " Tipo: " + OS.class.getName()));
	}

	public List<OS> findAll() {
		return osRepository.findAll();
	}

	public OS create(@Valid OSDTO osDTO) {
		return fromDTO(osDTO);
	}

	public OS update(@Valid OSDTO osdto) {
		findById(osdto.getId());
		return fromDTO(osdto);
	}

	private OS fromDTO(OSDTO osDTO) {
		OS newOs = new OS();
		newOs.setId(osDTO.getId());
		newOs.setObservacoes(osDTO.getObservacoes());
		newOs.setPrioridade(Prioridade.toEnum(osDTO.getPrioridade()));
		newOs.setStatus(Status.toEnum(osDTO.getStatus()));

		Tecnico tec = tecnicoService.findById(osDTO.getTecnico());
		Cliente cli = clienteService.findById(osDTO.getCliente());
		
		newOs.setTecnico(tec);
		newOs.setCliente(cli);

		if (newOs.getStatus().getCod().equals(2)) {
			newOs.setDataFechamento(LocalDateTime.now());
		}

		return osRepository.save(newOs);
	}

}
