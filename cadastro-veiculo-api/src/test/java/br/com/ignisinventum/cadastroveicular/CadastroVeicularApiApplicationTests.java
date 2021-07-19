package br.com.ignisinventum.cadastroveicular;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ignisinventum.cadastroveicular.controller.VeiculoRest;
import br.com.ignisinventum.cadastroveicular.model.Veiculo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {
		"spring.h2.console.enabled=true", "spring.h2.console.path=/console" })
public class CadastroVeicularApiApplicationTests {

	@Autowired
	private VeiculoRest  rest;

	
	@Test
	public void get() {
		assertTrue(rest.buscarTodos().getBody().size() > 0);
	}
	
	@Test
	public void getId() {
		assertTrue(rest.buscarPorId(1).getBody().getId() > 0);
	}
	
	@Test
	public void getFind() {
		Map<String, Object> atributos = new HashMap<String, Object>();
		atributos.put("marca", "VOLKSWAGEN");
		atributos.put("vendido", true);
		assertTrue(rest.buscarPorParametros(atributos).getBody().size() > 0);
	}
	
	@Test
	public void post() {
		Veiculo v  = new Veiculo();
		v.setAno(1988);
		v.setMarca("VOLKSVAGEN");
		v.setVeiculo("VERONA");
		v.setVendido(false);
		v.setDescricao("CARRO DO ANO DE 1988");
		assertTrue(rest.salvar(v).getBody().getId() > 0);
	}
	
	@Test
	public void put() {
		Veiculo v  = rest.buscarPorId(1).getBody();
		v.setVendido(true);
		assertTrue(rest.atualizar(v.getId(), v).getBody().getId() > 0);
	}
	
	@Test
	public void patch() {
		Map<String, Object> atributos = new HashMap<String, Object>();
		atributos.put("veiculo", "teste");
		atributos.put("vendido", false);
		assertTrue(rest.atualizarPorEnidade(7, atributos).getBody().getId() > 0);	
	}
	
	@Test
	public void delete() {
		assertTrue(rest.removerEntidade(3).getBody().booleanValue() == true);
	}

}
