package service;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Cliente;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDate;


@Service
public class ClienteService {
    @Autowired
    private final ClienteRepository clienteRepository;
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Page<Cliente> getAllClienti(int page, int size, String sortBy) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return clienteRepository.findAll(pageable);
    }


    public Cliente getById(int idCliente) {
        return clienteRepository.findById(idCliente).orElse(null);
    }


    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }


    public Cliente  findByIdAndUpadate(int idCliente, Cliente updatedCliente) {
        Cliente cliente = this.getById(idCliente);
        cliente.setPartitaIva(updatedCliente.getPartitaIva());
        cliente.setRagioneSociale(updatedCliente.getRagioneSociale());
        cliente.setEmail(updatedCliente.getEmail());
        cliente.setDataInserimento(updatedCliente.getDataInserimento());
        cliente.setDataUltimoContatto(updatedCliente.getDataUltimoContatto());
        cliente.setFatturatoAnnuale(updatedCliente.getFatturatoAnnuale());
        cliente.setPec(updatedCliente.getPec());
        cliente.setTelefeno(updatedCliente.getTelefeno());
        cliente.setIdCliente(updatedCliente.getIdCliente());
        cliente.setIndirizzo(updatedCliente.getIndirizzo());
        return clienteRepository.save(cliente);
    }


    public void findByIdAndDelete(int idCliente) {
        clienteRepository.deleteById(idCliente);
    }


    public void deleteAllClienti() {
        clienteRepository.deleteAll();
    }

    public Page<Cliente> findByDataInserimento(int page, LocalDate dataInserimento) {
        Pageable pagina = PageRequest.of(page, 10);
        return clienteRepository.findByDataInserimento(pagina, dataInserimento);
    }


    public Page<Cliente> findByDataUltimoContatto(int page, LocalDate dataUltimoContatto) {
        Pageable pagina = PageRequest.of(page, 10);
        return clienteRepository.findByDataUltimoContatto(pagina, dataUltimoContatto);
    }


    public Page<Cliente> findByFatturatoAnnuale(int page, double fatturatoAnnuale) {
        Pageable pagina = PageRequest.of(page, 10);
        return clienteRepository.findByFatturatoAnnuale(pagina, fatturatoAnnuale);
    }



}
