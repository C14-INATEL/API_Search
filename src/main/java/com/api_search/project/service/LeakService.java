package com.api_search.project.service;

import com.api_search.project.dto.LeakResponse;
import com.api_search.project.entity.Leak;
import com.api_search.project.entity.Leakeds_email;
import com.api_search.project.repository.LeakRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Service
public class LeakService {
    private LeakRepository leakRepository;
    private final Leakeds_emailService leakeds_emailService;

    //constructor
    public LeakService(LeakRepository leakRepository,
                       Leakeds_emailService leakeds_emailService) {
        this.leakRepository = leakRepository;
        this.leakeds_emailService = leakeds_emailService;
    }

    public void save(Leak leak){
        leakRepository.save(leak);
    }

    public Leak saveObject(Leak leak) {
        return leakRepository.save(leak);
    }

    public Leak searchById(Integer id){
        return leakRepository.findById(id).orElse(null);
    }

    public List<Leak> searchAll(){
        return leakRepository.findAll();
    }

    public boolean existsByid(Integer id) {
        return leakRepository.existsById(id);
    }

    public void deleteByid(Integer id){
        leakRepository.deleteById(id);
    }

    public void deleteALL(){
        leakRepository.deleteAll();
    }

    public void searchAndSave(String email) {
        System.out.println("Entering leak service");
        String apiKey = "0bbc494ea25453369bee84e65e794e162e8f6560";

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://leakcheck.io/api/public?key=" + apiKey + "&check=" + email;

        try {
            System.out.println("=== Debug json ===");

            String raw = restTemplate.getForObject(url, String.class);
            System.out.println(raw);

            LeakResponse response = restTemplate.getForObject(url, LeakResponse.class);

            if (response == null) {
                System.out.println("Response is null");
                return;
            }

            if (!response.isSuccess()) {
                System.out.println("No leaks found for: " + email);
                return;
            }

            if (response.getSources() == null) {
                System.out.println("Sources is null");
                return;
            }
            System.out.println("RESPONSE: " + response);
            System.out.println("SOURCES: " + (response != null ? response.getSources() : "null"));
            if (response != null && response.getSources() != null) {

                for (LeakResponse.Source r : response.getSources()) {
                    System.out.println("SOURCE: " + r.getName());
                    BigInteger register = BigInteger.valueOf(
                            (email + r.getName()).hashCode()
                    );

                    boolean exists = leakRepository
                            .existsByAccountMonitoredAndRegister(r.getName(), register);

                    if (!exists) {

                        Leak leak = new Leak();
                        leak.setAccountMonitored(r.getName());

                        try {
                            leak.setDate_ocurrence(LocalDate.parse(r.getDate()));
                        } catch (Exception e) {
                            leak.setDate_ocurrence(LocalDate.now());
                        }

                        leak.setRegister(register);

                        Leak savedLeak = saveObject(leak);

                        Leakeds_email le = new Leakeds_email();
                        le.setEmail_id(email);
                        le.setId_leakd(savedLeak.getId());

                        leakeds_emailService.save(le);
                    }
                }
                System.out.println("RESPONSE: " + response);
                System.out.println("SOURCES: " + (response != null ? response.getSources() : "null"));
            }

        } catch (Exception e) {
            System.out.println("Error to call API: " + e.getMessage());
        }
    }
}