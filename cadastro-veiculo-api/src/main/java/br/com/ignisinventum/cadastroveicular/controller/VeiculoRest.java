package br.com.ignisinventum.cadastroveicular.controller;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ignisinventum.cadastroveicular.model.Veiculo;
import br.com.ignisinventum.cadastroveicular.service.VeiculoService;
import io.swagger.annotations.Api;

@RestController()
@RequestMapping("/veiculos")
@Api(value = "API Veicular 1.0")
public class VeiculoRest {

	@Autowired
	private VeiculoService service;

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping()
	public ResponseEntity<List<Veiculo>> buscarTodos() {
		return ResponseEntity.ok().body(service.buscarTodos());
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/{id}")
	public ResponseEntity<Veiculo> buscarPorId(@PathVariable("id") Integer id) {
		Veiculo ex = new Veiculo();
		ex.setId(id);
		return ResponseEntity.ok().body(service.buscarPorExemplo(ex).get());
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value="/find")
	public ResponseEntity<List<Veiculo>> buscarPorParametros(@RequestParam Map<String, Object> q ) {
		ModelMapper modelMapper = new ModelMapper();
		Veiculo ex = new Veiculo();
		modelMapper.map(q, ex);		
		return ResponseEntity.ok().body(service.buscarTodosPorExemplo(ex));
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping()
	public ResponseEntity<Veiculo> salvar(@RequestBody Veiculo veiculo) {
		return ResponseEntity.ok().body(service.salvar(veiculo));
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/{id}")
	public ResponseEntity<Veiculo> atualizar(@PathVariable("id") Integer id, @RequestBody Veiculo veiculo) {
		Veiculo ex = new Veiculo();
		ex.setId(id);
		return ResponseEntity.ok().body(service.buscarPorId(id).map(v->{
			v.setAno(veiculo.getAno());
			v.setDescricao(veiculo.getDescricao());
			v.setMarca(veiculo.getMarca());
			v.setVeiculo(veiculo.getVeiculo());
			v.setVendido(veiculo.getVendido());
			return service.salvar(v);
		}).get());
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PatchMapping("/{id}")
	public ResponseEntity<Veiculo> atualizarPorEnidade(@PathVariable("id") Integer id, @RequestBody Map<String, Object> valorAAtualizar) {
		ModelMapper modelMapper = new ModelMapper();
		Veiculo ex = service.buscarPorId(id).get();
		modelMapper.map(valorAAtualizar, ex);
		ex.setId(id);
		return ResponseEntity.ok().body(service.salvar(ex));
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> removerEntidade(@PathVariable("id") Integer id) {
		Veiculo ex = new Veiculo();
		ex.setId(id);
		ex = service.buscarPorExemplo(ex).get();
		return ResponseEntity.ok().body(service.remover(ex));
	}

}
