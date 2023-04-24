package io.github.tomaskul.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@RefreshScope
@Controller
public class RateController {
    @Value("${rate}")
    String rate;
    @Value("${lanecount}")
    String lanecount;
    @Value("${tollstart}")
    String tollstart;

    @Value("${connstring}")
    String connstring;

    // Autowires to OAuth2 enabled client in SecurityConfig.java. Uncomment if enabling OAuth2 authorization server.
    // @Autowired
    // private WebClient webClient;

    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @RequestMapping("/report")
    public String report(Model m){
        // call downstream service.
        WebClient webClient = WebClient.builder().build();
        Flux<TollData> response = webClient.get().uri("http://localhost:8081/api/tolldata")
                .retrieve().bodyToFlux(TollData.class);
        List<TollData> tollData = response.collectList().block();
        m.addAttribute("tolldata", tollData);

        return "report";
    }

    @RequestMapping("/rate")
    public String getRate(Model m) {
        m.addAttribute("rateamount", rate);
        m.addAttribute("lanes", lanecount);
        m.addAttribute("tollstart", tollstart);
        m.addAttribute("connstring", connstring);

        return "rateview";
    }

}
