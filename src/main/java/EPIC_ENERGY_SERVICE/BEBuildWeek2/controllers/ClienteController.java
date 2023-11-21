package EPIC_ENERGY_SERVICE.BEBuildWeek2.controllers;

/*@RestController
@RequestMapping("/clienti")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping()
    public Page<Cliente> getAllClienti(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "id") String order) {
        return clienteService.getAllClienti(page, size > 20 ? 5 : size, order);

    }

    @GetMapping("/{id}")
    public Cliente getSingleCliente(@PathVariable int id) {
        return clienteService.getSingleCliente(id);
    }

    @GetMapping("/getbydata")
    public Page<Cliente> getByData(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "id") String order,@RequestParam String data){
        return clienteService.getByData(page, size > 20 ? 5 : size, order,data);

    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente createCliente(@RequestBody @Validated ClientePayload body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new ErrorList(validation.getAllErrors());
        } else return clienteService.save(body);
    }

    @PutMapping("/upload_img")
    public Cliente uploadImg(@RequestParam("logo") MultipartFile file) {
        return clienteService.uploadImg(file);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable int id) {
        clienteService.deleteCliente(id);
    }

}*/
