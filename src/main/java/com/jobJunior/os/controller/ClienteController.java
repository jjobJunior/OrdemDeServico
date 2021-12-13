package com.jobJunior.os.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobJunior.os.dtos.ClienteDTO;
import com.jobJunior.os.modelo.Cliente;
import com.jobJunior.os.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
		ClienteDTO clienteDTO = new ClienteDTO(clienteService.findById(id));
		return ResponseEntity.ok().body(clienteDTO);
	}

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> listClientes = clienteService.findAll();
		List<ClienteDTO> listDtos = new ArrayList<>();

		for (Cliente cliente : listClientes) {
			listDtos.add(new ClienteDTO(cliente));
		}
		return ResponseEntity.ok().body(listDtos);
	}
}
