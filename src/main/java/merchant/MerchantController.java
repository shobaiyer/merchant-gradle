package merchant;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import model.UserTransactions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import utils.CsvReader;

import javax.annotation.PostConstruct;

@Controller
public class MerchantController {

    private static final String FILE_NAME = System.getProperty("user.dir") + "/src/main/resources/db/coding_challenge_data.csv";
    private static final String TOO_FEW_TX = "Error - Too few transactions";

    private List<UserTransactions> userTx;

    @PostConstruct
    public void setUserTx() throws Exception{
        this.userTx = CsvReader.readFile(new File(FILE_NAME));
    }

    @GetMapping("/getRecentTx")
    @ResponseBody
    public String ping(@RequestParam(name="user-id", required=true) String userId) {

        List<UserTransactions> res = userTx.stream().filter(x -> Integer.parseInt(userId) == x.getUserId()).collect(Collectors.toList());
        if(res.size()<5)
            return new Gson().toJson(TOO_FEW_TX);
        else {

            List<String> merchants = res.stream().map(s -> s.getMerchant()).collect(Collectors.toList());

            Map<String, Long> merchantsFrequency = merchants
                    .stream()
                    .collect(Collectors.groupingBy(v -> v, Collectors.counting()));


            List<String> frequentMerchNames = merchantsFrequency
                    .entrySet()
                    .stream()
                    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                    .map(Map.Entry::getKey)
                    .limit(3)
                    .collect(Collectors.toList());

            return new Gson().toJson(frequentMerchNames);
        }
    }

    @PostMapping("/saveTx")
    @ResponseBody
    public void saveTx(@RequestBody UserTransactions userTx) throws MalformedURLException {
        this.userTx.add((userTx));
    }
}
