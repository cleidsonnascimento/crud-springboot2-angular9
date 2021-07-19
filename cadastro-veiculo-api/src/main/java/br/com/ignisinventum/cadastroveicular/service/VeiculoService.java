package br.com.ignisinventum.cadastroveicular.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.ignisinventum.cadastroveicular.model.Veiculo;
import br.com.ignisinventum.cadastroveicular.repository.VeiculoRepository;

@Service
public class VeiculoService {
	
	@Autowired
	private VeiculoRepository repository;
	
	@Transactional
	public Veiculo salvar(Veiculo veiculo) {
		return repository.saveAndFlush(veiculo);
	}
	
	@Transactional
	public boolean remover(Veiculo veiculo) {
		repository.delete(veiculo);
		return true;
	}
	
	public Optional<Veiculo> buscarPorExemplo(Veiculo veiculo) {
		Example<Veiculo> ex = Example.of(veiculo);
		return repository.findOne(ex);
	}
	
	public Optional<Veiculo> buscarPorId(Integer id) {
		return repository.findById(id);
	}
	
	public List<Veiculo> buscarTodosPorExemplo(Veiculo veiculo) {
		Example<Veiculo> ex = Example.of(veiculo);
		List<Veiculo> veiculos = repository.findAll(ex);
		return veiculos;
	}
	
	public List<Veiculo> buscarTodos() {
		List<Veiculo> veiculos = repository.findAll();
		return veiculos;
	}

}
