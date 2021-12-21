package com.jobJunior.os.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jobJunior.os.dtos.OSDTO;
import com.jobJunior.os.services.OsService;

@RestController
@RequestMapping(value = "/os")
public class OSController {

	@Autowired
	private OsService osService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<OSDTO> findById(@PathVariable Integer id) {
		OSDTO osdto = new OSDTO(osService.findById(id));

		return ResponseEntity.ok().body(osdto);
	}

	@GetMapping
	public ResponseEntity<List<OSDTO>> findAlll() {
		List<OSDTO> list = osService.findAll().stream()
				.map(obj -> new OSDTO(obj)).collect(Collectors.toList());

		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<OSDTO> create(@Valid @RequestBody OSDTO osDTO){
		osDTO = new OSDTO(osService.create(osDTO));
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(osDTO.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping
	public ResponseEntity<OSDTO> update(@Valid @RequestBody OSDTO osdto){
		osdto = new OSDTO(osService.update(osdto));
		
		return ResponseEntity.ok().body(osdto);
	}
}













