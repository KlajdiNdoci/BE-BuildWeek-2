package EPIC_ENERGY_SERVICE.BEBuildWeek2.services;

import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Cliente;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.entities.Indirizzo;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.exceptions.NotFoundException;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads.ClientePayload;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.payloads.ClientePayloadModificaIndirizzo;
import EPIC_ENERGY_SERVICE.BEBuildWeek2.repositories.ClienteRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;


@Service
public class ClienteService {
    @Autowired
    private final ClienteRepository clienteRepository;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private IndirizzoService indirizzoService;
    @Autowired
    private CloudinaryService cloudinaryService;

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
        return clienteRepository.findById(idCliente).orElseThrow(() -> new NotFoundException(idCliente));
    }


    public Cliente save(ClientePayload cliente) throws IOException {
        Indirizzo sl = null;
        Indirizzo so = null;

        if (cliente.sedeLegale() != null)
            sl = indirizzoService.findById(Integer.parseInt(cliente.sedeLegale()));
        if (cliente.sedeOperativa() != null)
            so = indirizzoService.findById(Integer.parseInt(cliente.sedeOperativa()));
        Cliente c = Cliente.builder().ragioneSociale(cliente.ragioneSociale()).partitaIva(cliente.partitaIva()).email(cliente.email()).fatturatoAnnuale(cliente.fatturatoAnnuale()).pec(cliente.pec()).telefono(cliente.telefono()).emailContatto(cliente.emailContatto()).nomeContatto(cliente.nomeContatto()).cognomeContatto(cliente.cognomeContatto()).telefonoContatto(cliente.telefonoContatto()).indirizzoSedeLegale(sl).dataUltimoContatto(cliente.dataUltimoContatto()).tipoCliente(cliente.tipoCliente()).indirizzoSedeOperativa(so).build();
        return clienteRepository.save(c);
    }


    public Cliente findByIdAndUpadate(int idCliente, ClientePayload updatedCliente) {
        Indirizzo sl = null;
        Indirizzo so = null;

        if (updatedCliente.sedeLegale() != null)
            sl = indirizzoService.findById(Integer.parseInt(updatedCliente.sedeLegale()));
        if (updatedCliente.sedeOperativa() != null)
            so = indirizzoService.findById(Integer.parseInt(updatedCliente.sedeOperativa()));
        Cliente cliente = this.getById(idCliente);
        cliente.setPartitaIva(updatedCliente.partitaIva().isEmpty() ? cliente.getPartitaIva() : updatedCliente.partitaIva());
        cliente.setRagioneSociale(updatedCliente.ragioneSociale().isEmpty() ? cliente.getRagioneSociale() : updatedCliente.ragioneSociale());
        cliente.setEmail(updatedCliente.email().isEmpty() ? cliente.getEmail() : updatedCliente.email());
        cliente.setDataUltimoContatto(updatedCliente.dataUltimoContatto() == null ? cliente.getDataUltimoContatto() : updatedCliente.dataUltimoContatto());
        cliente.setFatturatoAnnuale(updatedCliente.fatturatoAnnuale());
        cliente.setPec(updatedCliente.pec().isEmpty() ? cliente.getPec() : updatedCliente.pec());
        cliente.setTelefono(updatedCliente.telefono().isEmpty() ? cliente.getTelefono() : updatedCliente.telefono());
        cliente.setTelefonoContatto(updatedCliente.telefonoContatto().isEmpty() ? cliente.getTelefonoContatto() : updatedCliente.telefonoContatto());
        cliente.setIndirizzoSedeLegale(sl == null ? cliente.getIndirizzoSedeLegale() : sl);
        cliente.setIndirizzoSedeOperativa(so == null ? cliente.getIndirizzoSedeOperativa() : so);
        return clienteRepository.save(cliente);
    }


    public void findByIdAndDelete(int idCliente) {
        Cliente c = this.getById(idCliente);
        cloudinaryService.deleteImageByUrl(c.getLogoAziendale());
        clienteRepository.deleteById(idCliente);
    }


    public void deleteAllClienti() {
        clienteRepository.deleteAll();
    }

    public Page<Cliente> findByDataUltimoContatto(int page, int size, String order, LocalDate dataUltimoContatto) {
        Pageable pagina = PageRequest.of(page, size, Sort.by(order));
        return clienteRepository.findByDataUltimoContatto(pagina, dataUltimoContatto);
    }

    public Page<Cliente> findByDataInserimento(int page, int size, String order, LocalDate dataInserimento) {
        Pageable pagina = PageRequest.of(page, size, Sort.by(order));
        return clienteRepository.findByDataInserimento(pagina, dataInserimento);
    }


    public Page<Cliente> findByFatturatoAnnuale(int page, int size, String order, double fatturatoAnnuale) {
        Pageable pagina = PageRequest.of(page, size, Sort.by(order));
        return clienteRepository.findByFatturatoAnnuale(pagina, fatturatoAnnuale);
    }

    public Page<Cliente> findByNomeContattoStartingWithIgnoreCase(int page, int size, String order, String nome) {
        Pageable pagina = PageRequest.of(page, size, Sort.by(order));
        return clienteRepository.findByNomeContattoStartingWithIgnoreCase(pagina, nome);
    }


    public Cliente uploadImg(MultipartFile file, int id) throws IOException {
        Cliente c = clienteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        String url = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        c.setLogoAziendale(url);
        clienteRepository.save(c);
        return c;
    }

    public void deleteCliente(int id) {
        Cliente c = getById(id);
        cloudinaryService.deleteImageByUrl(c.getLogoAziendale());
        clienteRepository.delete(c);
    }

    public Page<Cliente> getAllByProvincia(int page, int i, String order, String prov) {
        Pageable p = PageRequest.of(page, i, Sort.by("id"));
        return clienteRepository.findByIndirizzoSedeLegaleComuneProvinciaNome(p, prov);
    }

    public Cliente modifyCliente(ClientePayloadModificaIndirizzo body, int id) {
        Cliente c = getById(id);
        Indirizzo so;
        Indirizzo sl;
        if (body.sedeOperativa() == null)
            so = null;
        else
            so = indirizzoService.findById(Integer.parseInt(body.sedeOperativa()));
        if (body.sedeLegale() == null) sl = null;
        else sl = indirizzoService.findById(Integer.parseInt(body.sedeLegale()));

        c.setIndirizzoSedeOperativa(so != null ? so : c.getIndirizzoSedeOperativa());
        c.setIndirizzoSedeLegale(sl != null ? sl : c.getIndirizzoSedeLegale());

        return clienteRepository.save(c);

    }

    public Page<Cliente> findByIndirizzoSedeLegaleComuneProvinciaOrderByNome(int page, int i, String order) {
        Pageable p = PageRequest.of(page, i, Sort.by(order));
        return clienteRepository.findByIndirizzoSedeLegaleComuneProvinciaOrderByNome(p);
    }

}
