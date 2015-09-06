package org.motive.BSHistory.mvc;

import org.motive.BSHistory.domain.BaseStation;
import org.motive.BSHistory.service.IBSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/common")
public class BaseStationController {

	@Autowired
	private IBSService service;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String printWelcome(Model model, String title, Long id) {

		BaseStation baseStation = null;
		if (id != null && title != null) {
			baseStation = service.findByIdAndTitle(id, title);
		}
		else if (id != null) {
			baseStation = service.findById(id);
		} else if (null != title) {
			baseStation = service.findByTitle(title);
		}


		model.addAttribute("message", "Spring 4 MVC - Hello World "
				+ baseStation);

		return "home";
	}
	
	// gettser & setters

	public IBSService geService() {
		return service;
	}

	public void setService(IBSService service) {
		this.service = service;
	}
}
