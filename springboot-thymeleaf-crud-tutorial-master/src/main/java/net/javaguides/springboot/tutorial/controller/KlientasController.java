package net.javaguides.springboot.tutorial.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.javaguides.springboot.tutorial.entity.Klientas;
import net.javaguides.springboot.tutorial.repository.KlientasRepository;

@Controller
@RequestMapping("/klientai/")
public class KlientasController {

	private final KlientasRepository klientasRepository;

	@Autowired
	public KlientasController(KlientasRepository klientasRepository) {
		this.klientasRepository = klientasRepository;
	}

	@GetMapping("signup")
	public String showSignUpForm(Klientas klientas) {
		return "add-klientas";
	}

	@GetMapping("list")
	public String showUpdateForm(Model model) {
		model.addAttribute("klientai", klientasRepository.findAll());
		return "index";
	}

	@PostMapping("add")
	public String addKlientas(@Valid Klientas klientas, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-klientas";
		}

		klientasRepository.save(klientas);
		return "redirect:list";
	}

	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Klientas klientas = klientasRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid klientas Id:" + id));
		model.addAttribute("klientas", klientas);
		return "update-klientas";
	}

	@PostMapping("update/{id}")
	public String updateKlientas(@PathVariable("id") long id, @Valid Klientas klientas, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			klientas.setId(id);
			return "update-klientas";
		}

		klientasRepository.save(klientas);
		model.addAttribute("klientai", klientasRepository.findAll());
		return "index";
	}

	@GetMapping("delete/{id}")
	public String deleteKlientas(@PathVariable("id") long id, Model model) {
		Klientas klientas = klientasRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid klientas Id:" + id));
		klientasRepository.delete(klientas);
		model.addAttribute("klientai", klientasRepository.findAll());
		return "index";
	}
}
