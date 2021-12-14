package com.jobJunior.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobJunior.os.dtos.ClienteDTO;
import com.jobJunior.os.modelo.Cliente;
import com.jobJunior.os.repository.ClienteRepository;
import com.jobJunior.os.services.exception.DataIntegratyViolationException;
import com.jobJunior.os.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado, de Id: " + id + " Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente create(ClienteDTO clienteDTO) {
		if (findByCPF(clienteDTO) != null) {
			throw new DataIntegratyViolationException("CPF ja cadastrado na base de dados");

		}
		return clienteRepository
				.save(new Cliente(null, clienteDTO.getNome(), clienteDTO.getCpf(), clienteDTO.getTelefone()));
	}

	private Cliente findByCPF(ClienteDTO clienteDTO) {
		Cliente cliente = clienteRepository.findByCPF(clienteDTO.getCpf());
		if (cliente != null) {
			return cliente;
		}
		return null;
	}
}
