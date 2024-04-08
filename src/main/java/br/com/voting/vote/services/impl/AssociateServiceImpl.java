package br.com.voting.vote.services.impl;

import br.com.voting.vote.dtos.AssociateDTO;
import br.com.voting.vote.models.Associate;
import br.com.voting.vote.repositories.AssociateRepository;
import br.com.voting.vote.services.AssociateService;
import br.com.voting.vote.services.exceptions.ResourceNotFoundExceptions;
import br.com.voting.vote.services.exceptions.ResourceValidationException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssociateServiceImpl implements AssociateService {
    @Autowired
    private AssociateRepository associateRepository;


    @Transactional
    @Override
    public void createAssociate(AssociateDTO associateDTO) {
        if (associateDTO.getName() == null || associateDTO.getCpf() == null) {
            throw new ResourceValidationException("Invalid field!" + "name: "+ associateDTO.getName()+
                    ", cpf: " + associateDTO.getCpf());
        }

        Associate associate = new Associate();
        associate.setName(associateDTO.getName());
        associate.setCpf(associateDTO.getCpf());

        associateRepository.save(associate);
    }

    @Override
    public List<Associate> findAll() {
        return associateRepository.findAll();
    }

    @Override
    public Associate findById(String id) {
        Optional<Associate> associateOptional = associateRepository.findById(Long.parseLong(id));

        return associateOptional.orElseThrow(() -> new ResourceNotFoundExceptions(Long.parseLong(id)));
    }

    @Override
    public void deleteAssociate(String id) {
        Associate associate = findById(id);

        if(associate != null){
            associateRepository.delete(associate);
        }
    }

    @Transactional
    @Override
    public void updateAssociate(AssociateDTO associateDTO, String id) {
        Associate associate = findById(id);
        if(associate != null){
            associate.setCpf(associateDTO.getCpf());
            associate.setName(associateDTO.getName());
            associateRepository.save(associate);
        }
    }
}
