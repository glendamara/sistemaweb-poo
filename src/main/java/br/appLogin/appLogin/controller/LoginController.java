package br.appLogin.appLogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.appLogin.appLogin.model.Doutor;
import br.appLogin.appLogin.model.Paciente;
import br.appLogin.appLogin.repository.DoutorRepository;
import br.appLogin.appLogin.repository.PacienteRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class LoginController {
	
	@Autowired
	private PacienteRepository pac;
	@Autowired
	private DoutorRepository dor;
	
	@GetMapping("/login")
	public String login() {
		return "login-pacient";
	}
	
	@PostMapping("/logar")
	public String loginPaciente(Paciente paciente, Model model, HttpServletResponse response) {
		Paciente user = this.pac.login(paciente.getEmail(), paciente.getSenha());
		if(user != null) {
			
		}
		model.addAttribute("erro","Paciente invalido!");
		return "login";
	}
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/loginMed")
	public String loginMed() {
		return "login-doctor";
	}
	
	@GetMapping("/cadastroPaciente")
	public String cadastro() {
		return "cadastro-paciente";	
	}
	
	@GetMapping("/cadastroMed")
	public String cadastroMed() {
		return "cadastro-doutor";
	}
	
	@RequestMapping(value = "/cadastroMed", method = RequestMethod.POST)
	public String cadastroMed(@Valid Doutor doutor, BindingResult result) {
		
		if (result.hasErrors()) {
			return "redirect:/cadastroMed";
		}



		
		dor.save(doutor);

		
		return "redirect:/loginMed";
	}
	
	
	@RequestMapping(value = "/cadastroPaciente", method = RequestMethod.POST)
	public String cadastroPaciente(@Valid Paciente paciente, BindingResult result) {
		
		if (result.hasErrors()) {
			return "redirect:/cadastroPaciente";
		}

		
		if (paciente.getPlano() == null || paciente.getPlano().isEmpty() || paciente.getPlano().equals("none")) {
			paciente.setPlano("none"); 
		}

		
		pac.save(paciente);

		
		return "redirect:/login";
	}
}
