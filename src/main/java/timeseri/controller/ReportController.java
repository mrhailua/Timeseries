package timeseri.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import timeseri.MongoService;
import timeseri.domain.SeriObject;
import timeseri.domain.SeriesReport;

@Controller
public class ReportController {
	static Logger logger = Logger.getLogger(ReportController.class);

	@Autowired
	private MongoService mongoService;

	@RequestMapping("/report")
	public ModelAndView home(@RequestParam String seriId) {
		final Map<String, Object> model = new HashMap<String, Object>();
		SeriObject seri = mongoService.getSeri(seriId);
		model.put("SeriId", seriId);
		model.put("SeriData", seri.getSeriData());
		model.put("SeriObj", seri);
		
		return new ModelAndView("report", model);
	}

	@RequestMapping("/series")
	public ModelAndView series() {
		final Map<String, Object> model = new HashMap<String, Object>();
		List<SeriesReport> reportSeries = mongoService.getTimeSeri(1500);
		model.put("reportSeries", reportSeries);

		return new ModelAndView("Series", model);
	}

}
