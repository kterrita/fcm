package ee.beleychev.fcm.web.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ee.beleychev.fcm.domain.FuelConsumptionRequest;
import ee.beleychev.fcm.exception.StorageFileNotFoundException;
import ee.beleychev.fcm.service.FuelConsumptionRequestService;
import ee.beleychev.fcm.service.StorageService;
import ee.beleychev.fcm.util.FcmJsonParser;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private final StorageService storageService;
    private final FuelConsumptionRequestService fuelConsumptionRequestService;

    @Autowired
    public UploadResource(StorageService storageService, FuelConsumptionRequestService fuelConsumptionRequestService) {
        this.storageService = storageService;
        this.fuelConsumptionRequestService = fuelConsumptionRequestService;
    }

    @GetMapping("/")
    public String listUploadedFiles() {
        return "uploadForm";
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {
        storageService.store(file);
        try {
            JSONArray jsonArray = FcmJsonParser.getJsonFromFile(storageService.loadAsResource(file.getOriginalFilename()));
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            List<FuelConsumptionRequest> requestsToSave = mapper.readValue(jsonArray.toJSONString(), new TypeReference<List<FuelConsumptionRequest>>() {
            });
            fuelConsumptionRequestService.saveAll(requestsToSave);
        }
        catch (ParseException e) {
            LOGGER.error("Error during parsing file", e);
            redirectAttributes.addFlashAttribute(MESSAGE,
                    "Error during parsing file " + file.getOriginalFilename() + "!");
            return REDIRECT;
        }
        catch (IOException e2) {
            LOGGER.error("Error during getting file", e2);
            redirectAttributes.addFlashAttribute(MESSAGE,
                    "Error during getting file " + file.getOriginalFilename() + "!");
            return REDIRECT;
        }
        catch (RuntimeException e3) {
            LOGGER.error("Error", e3);
        }

        redirectAttributes.addFlashAttribute(MESSAGE,
                "Your fuel request successfully uploaded " + file.getOriginalFilename() + "!");
        return REDIRECT;
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
