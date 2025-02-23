package br.appLogin.appLogin.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.appLogin.appLogin.model.Avaliacao;
import br.appLogin.appLogin.model.Consulta;
import br.appLogin.appLogin.model.Doutor;
import br.appLogin.appLogin.model.ListaEspera;
import br.appLogin.appLogin.model.Paciente;
import br.appLogin.appLogin.repository.AvaliacaoRepository;
import br.appLogin.appLogin.repository.ConsultaRepository;
import br.appLogin.appLogin.repository.DoutorRepository;
import br.appLogin.appLogin.repository.ListaEsperaRepository;
import br.appLogin.appLogin.repository.PacienteRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private DoutorRepository doutorRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;
    
    @Autowired
    private ListaEsperaRepository listaEsperaRepository;

    // ************************ LOGIN ************************

    @GetMapping
    public String loginPage() {
        return "index";
    }

    @GetMapping("/loginPaciente")
    public String loginPaciente() {
        return "login-paciente";
    }

    @GetMapping("/loginDoutor")
    public String loginDoutor() {
        return "login-doutor";
    }

    @PostMapping("/logar")
    public String logar(
            @RequestParam String email,
            @RequestParam String senha,
            @RequestParam String tipo,
            HttpServletResponse response,
            Model model) {

        if (tipo.equalsIgnoreCase("paciente")) {
            Paciente paciente = pacienteRepository.findByEmailAndSenha(email, senha);
            if (paciente != null) {
                Cookie cookie = new Cookie("PacienteId", String.valueOf(paciente.getId()));
                response.addCookie(cookie);
                return "redirect:/dashboardPaciente";
            } else {
                model.addAttribute("erro", "Email ou senha inválidos!");
                return "login-paciente";
            }
        } else if (tipo.equalsIgnoreCase("doutor")) {
            Doutor doutor = doutorRepository.findByEmailAndSenha(email, senha);
            if (doutor != null) {
                Cookie cookie = new Cookie("DoutorId", String.valueOf(doutor.getId()));
                response.addCookie(cookie);
                return "redirect:/dashboardDoutor";
            } else {
                model.addAttribute("erro", "Email ou senha inválidos!");
                return "login-doutor";
            }
        }

        model.addAttribute("erro", "Tipo de login inválido!");
        return "index";
    }

    // ************************ CADASTRO ************************

    @GetMapping("/cadastroPaciente")
    public String cadastroPaciente() {
        return "cadastro-paciente";
    }

    @PostMapping("/cadastrarPaciente")
    public String cadastrarPaciente(
            @RequestParam String nome,
            @RequestParam String email,
            @RequestParam String senha,
            @RequestParam String planoSaude,
            @RequestParam int idade,
            Model model) {

        if (pacienteRepository.findByEmailAndSenha(email, senha) != null) {
            model.addAttribute("erro", "Paciente com este email já está cadastrado!");
            return "cadastro-paciente";
        }

        Paciente paciente = new Paciente();
        paciente.setNome(nome);
        paciente.setEmail(email);
        paciente.setSenha(senha);
        paciente.setPlanoSaude(planoSaude);
        paciente.setIdade(idade);

        pacienteRepository.save(paciente);
        return "redirect:/loginPaciente";
    }

    @GetMapping("/cadastroDoutor")
    public String cadastroDoutor() {
        return "cadastro-doutor";
    }

    @PostMapping("/cadastrarDoutor")
    public String cadastrarDoutor(
            @RequestParam String nome,
            @RequestParam String email,
            @RequestParam String senha,
            @RequestParam String especialidade,
            @RequestParam String planoAtendido,
            Model model) {

        if (doutorRepository.findByEmailAndSenha(email, senha) != null) {
            model.addAttribute("erro", "Doutor com este email já está cadastrado!");
            return "cadastro-doutor";
        }

        Doutor doutor = new Doutor();
        doutor.setNome(nome);
        doutor.setEmail(email);
        doutor.setSenha(senha);
        doutor.setEspecialidade(especialidade);
        doutor.setPlanoAtendido(planoAtendido);

        doutorRepository.save(doutor);
        return "redirect:/loginDoutor";
    }

    // ************************ DASHBOARD ************************

    @GetMapping("/dashboardPaciente")
    public String dashboardPaciente(HttpServletRequest request, Model model) {
        Long pacienteId = getPacienteIdFromCookies(request);
        if (pacienteId == null) {
            return "redirect:/loginPaciente";
        }

        Optional<Paciente> optionalPaciente = pacienteRepository.findById(pacienteId);
        if (optionalPaciente.isEmpty()) {
            return "redirect:/loginPaciente";
        }

        Paciente paciente = optionalPaciente.get();
        model.addAttribute("paciente", paciente);

        String planoSaude = paciente.getPlanoSaude();
        planoSaude = (planoSaude != null && !planoSaude.isEmpty()) ? planoSaude : "não tenho";

        List<Doutor> doutores = planoSaude.equalsIgnoreCase("não tenho")
                ? new ArrayList<>((Collection<? extends Doutor>) doutorRepository.findAll())
                : doutorRepository.findByPlanoAtendido(planoSaude);

        model.addAttribute("doutores", doutores);

        List<Consulta> consultas = consultaRepository.findByPaciente(paciente);
        model.addAttribute("consultas", consultas);

        return "dashboard-paciente";
    }

    @GetMapping("/dashboardDoutor")
    public String dashboardDoutor(HttpServletRequest request, Model model) {
        Long doutorId = getDoutorIdFromCookies(request);
        if (doutorId == null) {
            return "redirect:/loginDoutor";
        }

        Optional<Doutor> optionalDoutor = doutorRepository.findById(doutorId);
        if (optionalDoutor.isEmpty()) {
            return "redirect:/loginDoutor";
        }

        Doutor doutor = optionalDoutor.get();
        model.addAttribute("doutor", doutor);

        List<Consulta> consultas = consultaRepository.findByDoutorAndStatus(doutor, "AGENDADA");
        model.addAttribute("consultas", consultas);

        return "dashboard-doutor";
    }
    
    @GetMapping("/editarDoutor")
    public String editarDoutor(HttpServletRequest request, Model model) {
        Long doutorId = getDoutorIdFromCookies(request);
        if (doutorId == null) {
            return "redirect:/loginDoutor";
        }

        Optional<Doutor> optionalDoutor = doutorRepository.findById(doutorId);
        if (optionalDoutor.isEmpty()) {
            return "redirect:/loginDoutor";
        }

        model.addAttribute("doutor", optionalDoutor.get());
        return "editarDoutor"; // Página de edição
    }

    @PostMapping("/editarDoutor")
    public String atualizarDoutor(
            HttpServletRequest request,
            @RequestParam String nome,
            @RequestParam String especialidade,
            @RequestParam String planoAtendido, // Campo de texto simples
            @RequestParam(required = false) String senha,
            Model model) {

        Long doutorId = getDoutorIdFromCookies(request);
        if (doutorId == null) {
            return "redirect:/loginDoutor";
        }

        Optional<Doutor> optionalDoutor = doutorRepository.findById(doutorId);
        if (optionalDoutor.isEmpty()) {
            return "redirect:/loginDoutor";
        }

        Doutor doutor = optionalDoutor.get();

        // Atualizar os campos
        doutor.setNome(nome);
        doutor.setEspecialidade(especialidade);
        doutor.setPlanoAtendido(planoAtendido); // Atribuir o plano recebido do campo de texto

        if (senha != null && !senha.isEmpty()) {
            doutor.setSenha(senha);
        }

        // Salvar as alterações no banco
        doutorRepository.save(doutor);

        model.addAttribute("mensagem", "Dados atualizados com sucesso!");
        return "redirect:/dashboardDoutor";
    }

    // ************************ AGENDAMENTO DE CONSULTA ************************

    @PostMapping("/atualizarConsulta")
public String atualizarConsulta(
        @RequestParam Long consultaId,
        @RequestParam String descricao,
        @RequestParam Double valor
) {
    // Buscar a consulta pelo ID
    Optional<Consulta> consultaOptional = consultaRepository.findById(consultaId);

    if (consultaOptional.isPresent()) {
        Consulta consulta = consultaOptional.get();

        // Atualizar a descrição e o valor da consulta
        consulta.setDescricao(descricao);
        consulta.setValor(valor);

        // Salvar as alterações no banco de dados
        consultaRepository.save(consulta);
    }

    // Redirecionar para o dashboard do doutor
    return "redirect:/dashboardDoutor";
}





  @PostMapping("/agendarConsulta")
public String agendarConsulta(
        @RequestParam Long doutorId,
        @RequestParam String data,
        HttpServletRequest request,
        RedirectAttributes redirectAttributes) {

    // Obter o ID do paciente dos cookies
    Long pacienteId = getPacienteIdFromCookies(request);
    if (pacienteId == null) {
        return "redirect:/loginPaciente";
    }

    Optional<Paciente> optionalPaciente = pacienteRepository.findById(pacienteId);
    Optional<Doutor> optionalDoutor = doutorRepository.findById(doutorId);

    // Verificar se o paciente e o doutor existem
    if (optionalPaciente.isEmpty() || optionalDoutor.isEmpty()) {
        return "redirect:/dashboardPaciente";
    }

    // Parse da data
    Date dataConsulta;
    try {
        dataConsulta = new SimpleDateFormat("yyyy-MM-dd").parse(data);
    } catch (ParseException e) {
        // Mensagem de erro para data inválida
        redirectAttributes.addFlashAttribute("erro", "Formato de data inválido.");
        return "redirect:/dashboardPaciente";
    }

    // Buscar consultas no dia
    List<Consulta> consultasNoDia = consultaRepository.findByDoutorAndData(optionalDoutor.get(), dataConsulta);

    // Se o médico não estiver lotado, agendar consulta
    if (consultasNoDia.size() < 5) {
        Consulta consulta = new Consulta();
        consulta.setPaciente(optionalPaciente.get());
        consulta.setDoutor(optionalDoutor.get());
        consulta.setData(dataConsulta);
        consulta.setStatus("AGENDADA");
        consultaRepository.save(consulta);

        // Mensagem de sucesso
        redirectAttributes.addFlashAttribute("mensagem", "Consulta agendada com sucesso!");
    } else {
        // Caso o médico esteja lotado, incluir na lista de espera
        ListaEspera listaEspera = new ListaEspera();
        listaEspera.setPaciente(optionalPaciente.get());
        listaEspera.setDoutor(optionalDoutor.get());
        listaEspera.setData(dataConsulta);
        listaEsperaRepository.save(listaEspera);

        // Mensagem de lista de espera
        redirectAttributes.addFlashAttribute("mensagem", "O médico está lotado nesse dia. Você foi colocado na lista de espera.");
    }

    return "redirect:/dashboardPaciente";
}

    
    
    
    @GetMapping("/filtrarMedicos")
    public String filtrarMedicos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String especialidade,
            HttpServletRequest request,
            Model model) {

        Long pacienteId = getPacienteIdFromCookies(request);
        if (pacienteId == null) {
            return "redirect:/loginPaciente";
        }

        Optional<Paciente> optionalPaciente = pacienteRepository.findById(pacienteId);
        if (optionalPaciente.isEmpty()) {
            return "redirect:/loginPaciente";
        }

        Paciente paciente = optionalPaciente.get();
        String planoSaude = paciente.getPlanoSaude();

        List<Doutor> medicos;
        if (planoSaude == null || planoSaude.equalsIgnoreCase("não tenho")) {
            // Mostrar todos os médicos
            medicos = doutorRepository.findAllByNomeOrEspecialidade(nome, especialidade);
        } else {
            // Filtrar por plano e especialidade/nome
            medicos = doutorRepository.findByPlanoAtendidoAndNomeOrEspecialidade(planoSaude, nome, especialidade);
        }

        model.addAttribute("medicos", medicos);
        return "dashboard-paciente";
    }
    
    @PostMapping("/cancelarConsulta")
    public String cancelarConsulta(@RequestParam Long consultaId, Model model) {
        Optional<Consulta> consultaOptional = consultaRepository.findById(consultaId);

        if (consultaOptional.isPresent()) {
            Consulta consulta = consultaOptional.get();

            // Verificar lista de espera
            List<ListaEspera> listaEspera = listaEsperaRepository.findByDoutorAndData(consulta.getDoutor(), consulta.getData());
            if (!listaEspera.isEmpty()) {
                // Alocar paciente da lista de espera
                ListaEspera primeiroDaLista = listaEspera.get(0);
                Consulta novaConsulta = new Consulta();
                novaConsulta.setPaciente(primeiroDaLista.getPaciente());
                novaConsulta.setDoutor(primeiroDaLista.getDoutor());
                novaConsulta.setData(primeiroDaLista.getData());
                novaConsulta.setStatus("AGENDADA");

                consultaRepository.save(novaConsulta);
                listaEsperaRepository.delete(primeiroDaLista);
            }

            consultaRepository.delete(consulta);
        }
        return "redirect:/dashboardPaciente";
    }

   @PostMapping("/finalizarConsulta")
public String finalizarConsulta(
        @RequestParam Long consultaId,
        @RequestParam(required = false) String descricao,
        Model model
) {
    // Buscar a consulta pelo ID
    Optional<Consulta> consultaOptional = consultaRepository.findById(consultaId);
    if (consultaOptional.isEmpty()) {
        return "redirect:/dashboardPaciente";
    }

    Consulta consulta = consultaOptional.get();

    // Verificar se a descrição está presente
    if (consulta.getDescricao() == null || consulta.getDescricao().isEmpty()) {
        // Redirecionar de volta ao dashboard do paciente com uma mensagem de erro
        model.addAttribute("erro", "A consulta ainda não possui uma descrição do médico.");
        return "redirect:/dashboardPaciente";
    }

    // Atualizar o status para FINALIZADA
    consulta.setStatus("FINALIZADA");

    consultaRepository.save(consulta);

    // Redirecionar para a avaliação do médico
    return "redirect:/avaliarMedico?consultaId=" + consulta.getId();
}


    
// Rota para exibir o formulário de avaliação
@GetMapping("/avaliarMedico")
public String exibirFormularioDeAvaliacao(@RequestParam Long consultaId, Model model) {
    Optional<Consulta> consultaOptional = consultaRepository.findById(consultaId);

    if (consultaOptional.isPresent()) {
        Consulta consulta = consultaOptional.get();

        // Logs para debug (se necessário)
        System.out.println("Descrição do Doutor: " + consulta.getDescricao());
        System.out.println("Valor da consulta: " + consulta.getValor());

        // Adicionar dados da consulta ao modelo
        model.addAttribute("consulta", consulta);
            if (consulta.getPaciente().getPlanoSaude() == null || consulta.getPaciente().getPlanoSaude().equalsIgnoreCase("não tenho")) {
                model.addAttribute("valorConsulta", consulta.getValor());
    }
        
        model.addAttribute("descricaoDoutor", consulta.getDescricao()); // Adicionar a descrição

        return "avaliar-medico"; // Nome do template a ser renderizado
    }

    // Se a consulta não for encontrada, redireciona
    return "redirect:/dashboardPaciente";
}


    // Processar envio do formulário de avaliação
    @PostMapping("/avaliarMedico")
    public String processarAvaliacao(
            @RequestParam Long consultaId, // ID da consulta
            @RequestParam Integer estrelas, // Nota (número de estrelas)
            @RequestParam String comentario, // Comentário do paciente
            Model model) {

        // Buscar a consulta no banco
        Optional<Consulta> optionalConsulta = consultaRepository.findById(consultaId);

        if (optionalConsulta.isPresent()) {
            Consulta consulta = optionalConsulta.get();

            // Criar nova avaliação
            Avaliacao avaliacao = new Avaliacao();
            avaliacao.setConsulta(consulta); // Associar à consulta
            avaliacao.setPaciente(consulta.getPaciente()); // Associar ao paciente da consulta
            avaliacao.setDoutor(consulta.getDoutor()); // Associar ao doutor da consulta
            avaliacao.setEstrelas(estrelas); // Definir a quantidade de estrelas
            avaliacao.setComentario(comentario); // Definir o comentário

            // Salvar a avaliação
            avaliacaoRepository.save(avaliacao);

            // Redirecionar para o "Dashboard do Paciente" após salvar
            return "redirect:/dashboardPaciente";
        }

        // Se consulta não for encontrada, mostrar uma mensagem de erro
        return "redirect:/erro";
    }



    // ************************ FUNÇÕES AUXILIARES ************************

    private Long getPacienteIdFromCookies(HttpServletRequest request) {
        return getCookieValue(request, "PacienteId");
    }

    private Long getDoutorIdFromCookies(HttpServletRequest request) {
        return getCookieValue(request, "DoutorId");
    }

    private Long getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return Long.parseLong(cookie.getValue());
                }
            }
        }
        return null;
    }
}
