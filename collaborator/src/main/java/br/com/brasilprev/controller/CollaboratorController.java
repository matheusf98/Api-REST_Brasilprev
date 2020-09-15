package br.com.brasilprev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.brasilprev.models.Collaborator;
import br.com.brasilprev.repository.CollaboratorRepository;

@Controller
public class CollaboratorController {
	
	@Autowired
	private CollaboratorRepository cr;
	
	@RequestMapping(value="/registerEmployee", method= RequestMethod.GET)
	public String form() {
		System.out.println("Chamando GET");
		return "collaborator/formCollaborator";
	}
	
	@RequestMapping(value="/registerEmployee", method= RequestMethod.POST)
	public String formSave( Collaborator collaborator) {
		if(collaborator.getName().length() <= 3 || collaborator.getAddress().length() <= 5 || collaborator.getCpf().length() != 14) {
			System.out.println("Campos Em Branco ou invalidos");
			return "collaborator/formCollaboratorError";
		}else {
			cr.save(collaborator);
			System.out.println("Dados salvados");
			return "redirect:/collaborator";
		}
	}
	
	@RequestMapping(value="/editContributor", method= RequestMethod.GET)
	public String edit(Long id) {
		Collaborator collaborator = cr.findByid(id);
		System.out.println("Chamando GET");
		return "collaborator/editContributor";
	}
	
	@RequestMapping(value="/editContributor", method= RequestMethod.POST)
	public String editSave (Long id, String name, String address, String cpf) {
		Collaborator collaborator = cr.findByid(id);
		collaborator.setName(name);
		collaborator.setCpf(cpf);
		collaborator.setAddress(address);
		if(collaborator.getName().length() <= 3 || collaborator.getAddress().length() <= 5 || collaborator.getCpf().length() != 14) {
			System.out.println("Campos Em Branco ou invalidos");
			return "collaborator/editContributorError";
		}else {
			cr.save(collaborator);
			System.out.println("Editado com sucesso");
			return "redirect:/collaborator";
		}		
	}
	
	
	@RequestMapping("/collaborator")
	public ModelAndView collaboratorsList() {
		ModelAndView mav = new ModelAndView("index");
		Iterable<Collaborator> list = cr.findAll();
		mav.addObject("collaborator", list);
		
		return mav;
	}
	
	@RequestMapping("/deletar")
	public String deleteContributor(Long id) {
		Collaborator collaborator = cr.findByid(id);
		System.out.println("Deletado com sucesso");
		cr.delete(collaborator);
		return "redirect:/collaborator";
	}
	
}
