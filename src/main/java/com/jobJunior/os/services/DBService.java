package com.jobJunior.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobJunior.os.modelo.Cliente;
import com.jobJunior.os.modelo.OS;
import com.jobJunior.os.modelo.Tecnico;
import com.jobJunior.os.modelo.enuns.Prioridade;
import com.jobJunior.os.modelo.enuns.Status;
import com.jobJunior.os.repository.ClienteRepository;
import com.jobJunior.os.repository.OSRepository;
import com.jobJunior.os.repository.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private OSRepository osRepository;

	public void instanciaDB() {
		Tecnico t1 = new Tecnico(null, "job Junior", "694.820.540-24", "(18) 99999-9999");
		Tecnico t2 = new Tecnico(null, "Job Pereira", "135.514.830-86", "(33) 33333-9999");
		Cliente c1 = new Cliente(null, "Isadora", "902.207.590-73", "(48) 55555-4411");
		Cliente c2 = new Cliente(null, "Ana Julia", "570.494.520-17", "(12) 12121-4411");
		OS os1 = new OS(null, Prioridade.ALTA, "Teste de Create OS", Status.ANDAMENTO, c1, t1);
		OS os2 = new OS(null, Prioridade.ALTA, "Segundo Teste de Create OS", Status.ANDAMENTO, c2, t1);

		t1.getListOs().add(os1);
		c1.getListOs().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c1, c2));
		osRepository.saveAll(Arrays.asList(os1, os2));
	}
}
