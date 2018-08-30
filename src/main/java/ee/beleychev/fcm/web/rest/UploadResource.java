package ee.beleychev.fcm.web.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ee.beleychev.fcm.domain.FcmJsonParserException;
import ee.beleychev.fcm.domain.FuelConsumptionRequest;
import ee.beleychev.fcm.repository.FuelConsumptionRequestRepository;
import ee.beleychev.fcm.util.FcmJsonParser;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

/**
 * @author beleychev
 */
@Controller
public class UploadResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadResource.class);
    private static final String MESSAGE = "message";
    private static final String REDIRECT = "redirect:/";
    private final FuelConsumptionRequestRepository fuelConsumptionRequestRepository;

    @Autowired
    public UploadResource(FuelConsumptionRequestRepository fuelConsumptionRequestRepository) {
        this.fuelConsumptionRequestRepository = fuelConsumptionRequestRepository;
    }

    /**
     * Creates and persists new request
     *
     * @param fuelConsumptionRequest request
     * @return created request
     */
    @PostMapping("/request")
    public @ResponseBody
    FuelConsumptionRequest newRequest(@RequestBody FuelConsumptionRequest fuelConsumptionRequest) {
        return fuelConsumptionRequestRepository.save(fuelConsumptionRequest);
    }

    /**
     * View of upload form
     *
     * @return uploadForm.html
     */
    @GetMapping("/")
    public String listUploadedFiles() {
        return "uploadForm";
    }

    /**
     * Handles uploaded file and saves bunch of json as requests
     *
     * @param file               uploaded file
     * @param redirectAttributes redirectAttributes
     * @return view with error or success info
     * @throws FcmJsonParserException in case of exception while creating JSON array from file
     */
    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) throws FcmJsonParserException {
        try {
            JSONArray jsonArray = FcmJsonParser.getJsonFromFile(file);
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            List<FuelConsumptionRequest> requestsToSave = mapper.readValue(jsonArray.toJSONString(), new TypeReference<List<FuelConsumptionRequest>>() {
            });
            fuelConsumptionRequestRepository.saveAll(requestsToSave);
        }
        catch (IOException e) {
            LOGGER.error("Error during getting file", e);
            redirectAttributes.addFlashAttribute(MESSAGE,
                    "IO Error during getting file " + file.getOriginalFilename() + "!");
            return REDIRECT;
        }
        catch (RuntimeException e2) {
            LOGGER.error("Error", e2);
            redirectAttributes.addFlashAttribute(MESSAGE,
                    "Unexpected error during getting file " + file.getOriginalFilename() + "!");
            return REDIRECT;
        }

        redirectAttributes.addFlashAttribute(MESSAGE,
                "Your fuel request successfully uploaded " + file.getOriginalFilename() + "!");
        return REDIRECT;
    }
}
