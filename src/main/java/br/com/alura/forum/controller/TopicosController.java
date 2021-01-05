package br.com.alura.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;


	@GetMapping
	public List<TopicoDto> lista(String nomeCurso) {

		if (nomeCurso == null) {
			List<TopicoDto> converter = TopicoDto.converter(topicoRepository.findAll());
			return converter;
		} else {
			List<TopicoDto> converter = TopicoDto.converter(topicoRepository.findByCursoNome(nomeCurso));

			return converter;
		}
	}
	
	@PostMapping
	public void cadastrar(@RequestBody TopicoForm topicoForm) {

		Topico topico = topicoForm.converter(cursoRepository);
		
		this.topicoRepository.save(topico);		
	}
}
