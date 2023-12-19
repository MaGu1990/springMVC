package com.hotel.de.hotelDB.controller;

import com.hotel.de.hotelDB.model.CadastroModel;
import com.hotel.de.hotelDB.repositoty.CadastroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class CadastroController {

    @Autowired
    CadastroRepository cadastroRepository

    @PostMapping("/cadastro")
    public ResponseEntity<CadastroModel> saveCadastro(@RequestBody @Valid CadastroRepository cadastroRepository) {
        var cadastroModel = new CadastroModel();
        BeanUtils.copyProperties(cadastroRepository, cadastroModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastroRepository.save(cadastroModel));
    }

    @GetMapping("/cadastro")
    public ResponseEntity<List<CadastroModel>> getAllCadastro(){
        List<CadastroModel> cadastroList = cadastroRepository.findAll();
        if (!cadastroList.isEmpty()){
            for (CadastroModel cadastro : cadastroList){
                UUID id = cadastro.getIdCadastro();
                cadastro.add(linkTo(methodOn(CadastroController.class).getOneCadastro(id)).withSelfRell());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(cadastroList);
    }

    @GetMapping("/cadastro/{id}")
    public ResponseEntity<Object> getOneCadastro(@PathVariable(value=id)UUID id){
        Optional<CadastroModel> cadastroX = cadastroRepository.findById(id);
        if (cadastroX.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cadastro não Existe");
        }
        cadastroX.get().add(linkTo(methodOn(CadastroController.class)getAllCadastros()).withRel("Cadastro.list"));
        return ResponseEntity.status(HttpStatus.OK).body(cadastroX.get());
    }

    @PutMapping("/cadastro/{id}")
    public ResponseEntity<Object> updateCadastro(@PathVariable(value=id)UUID id, @RequestBody @Valid CadastroRepository CadastroRepository){
        if (cadastroX.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cadadtro não Existe");
        }
        var cadastroModel = cadastroX.get();
        BeanUtils.copyProperties(CadastroRepository, cadastroModel);
        return ResponseEntity.status(HttpStatus.OK).body(cadastroRepository.save(cadastroModel));
    }

    @DeleteMapping("cadastro/{id}")
    public ResponseEntity<Object> deleteCadastro(@PathVariable(value=id)UUID id) {
        Optional<CadastroModel> cadastroX = cadastroRepository.findById(id);
        if (cadastroX.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cadastro não Existe");
        }
        cadastroRepository.delete(cadastroX.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cadastro Excluído com Sucesso");
    }
}
