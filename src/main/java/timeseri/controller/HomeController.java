package timeseri.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping("/")
	public ModelAndView home() {
		final Map<String, Object> model = new HashMap<String, Object>();
		// final List<SeriObject> paymentInstruments =
		// paymentInstrumentService.findPaymentInstruments();
		// model.put("paymentInstruments", paymentInstruments);
		return new ModelAndView("home", model);
	}
}
