package br.com.ignisinventum.cadastroveicular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ignisinventum.cadastroveicular.model.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {

}
