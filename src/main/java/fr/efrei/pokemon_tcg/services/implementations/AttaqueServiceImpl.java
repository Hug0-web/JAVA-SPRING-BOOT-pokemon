package fr.efrei.pokemon_tcg.services.implementations;

import fr.efrei.pokemon_tcg.models.Attaque;
import fr.efrei.pokemon_tcg.repositories.AttaqueRepository;
import fr.efrei.pokemon_tcg.services.IAttaqueService;
import fr.efrei.pokemon_tcg.dto.CreateAttaque;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AttaqueServiceImpl implements IAttaqueService {

    private final AttaqueRepository repository;

    public AttaqueServiceImpl(AttaqueRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Attaque> findAll() {
        return repository.findAll();
    }

    @Override
    public Attaque findById(String uuid) {
        return repository.findById(uuid).orElse(null);
    }

    @Override
    public void create(CreateAttaque createAttaque) {
        Attaque attaque = new Attaque();
        attaque.setNom(createAttaque.getNom());
        attaque.setDegat(createAttaque.getDegat());
        repository.save(attaque);
    }

    @Override
    public boolean update(String uuid, Attaque attaque) {
        Attaque attaqueToUpdate = findById(uuid);
        if (attaqueToUpdate == null) {
            return false;
        }
        attaqueToUpdate.setNom(attaque.getNom());
        attaqueToUpdate.setDegat(attaque.getDegat());
        repository.save(attaqueToUpdate);
        return true;
    }

    @Override
    public boolean delete(String uuid) {
        if (repository.existsById(uuid)) {
            repository.deleteById(uuid);
            return true;
        }
        return false;
    }
}
