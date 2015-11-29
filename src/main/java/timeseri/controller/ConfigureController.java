package timeseri.controller;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import timeseri.InquiryAnalyzer;
import timeseri.MongoService;
import timeseri.domain.Configure;

@Controller
public class ConfigureController {
	static Logger logger = Logger.getLogger(ConfigureController.class);

	@Autowired
	private InquiryAnalyzer inquiryAnalyzer;

	@Autowired
	private MongoService mongoService;

	@RequestMapping(value = "/")
	public ModelAndView home() {

		final Map<String, Object> model = new HashMap<String, Object>();
		final List<Configure> configures = mongoService.getConfigures();
		model.put("configures", configures);
		return new ModelAndView("configure", model);

	}

	@RequestMapping(value = "/configure", method = RequestMethod.POST)
	public String createJob(@RequestParam String jobName, @RequestParam String logFolder,
	        @RequestParam String startText, @RequestParam String endText, RedirectAttributes redirectAttributes) {

		mongoService.saveConfigure(new Configure(jobName, logFolder, startText, endText));
		executeAnalyse(logFolder, startText, endText);
		redirectAttributes.addFlashAttribute("message", String.format("Start to Import data"));
		return "redirect:/";
	}

	@Async
	private void executeAnalyse(String logFolder, String startText, String endText) {
		try {
			File folder = new File(logFolder);
			inquiryAnalyzer.analyze(Arrays.asList(folder.listFiles()), startText, endText);
		} catch (Exception ex) {
			logger.error("Analyse error", ex);
		}
	}
}
