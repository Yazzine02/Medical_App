package com.example.demo.controller;

import com.example.demo.dto.PrescriptionDTO;
import com.example.demo.model.Consultation;
import com.example.demo.model.Patient;
import com.example.demo.model.Prescription;
import com.example.demo.repository.ConsultationRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.PrescriptionRepository;
import com.example.demo.service.PrescriptionService;
import com.itextpdf.layout.properties.TextAlignment;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/prescription")
public class PrescriptionController {
    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionService prescriptionService;
    private final PatientRepository patientRepository;
    private final ConsultationRepository consultationRepository;

    public PrescriptionController(PrescriptionRepository prescriptionRepository, PrescriptionService prescriptionService, PatientRepository patientRepository, ConsultationRepository consultationRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.prescriptionService = prescriptionService;
        this.patientRepository = patientRepository;
        this.consultationRepository = consultationRepository;
    }

    @GetMapping("")
    public String prescriptionList(Model model) {
        List<Prescription> prescriptionList = prescriptionRepository.findAll();
        model.addAttribute("prescriptions", prescriptionList);
        return "prescription";
    }

    @GetMapping("/add")
    public String showAddPrescriptionForm(Model model) {
        List<Patient> patients = patientRepository.findAll();
        model.addAttribute("patients", patients);
        List<Consultation> consultations = consultationRepository.findAll();
        model.addAttribute("consultations", consultations);
        return "add-prescription";
    }
    @PostMapping("/add")
    public String addPrescription(@RequestParam(name = "consultationId") int consultationId, @RequestParam(name = "patientId") int patientId, @RequestParam(name = "prescriptionText") String prescriptionText) {
        prescriptionService.addPrescription(consultationId, patientId, prescriptionText);
        return "redirect:/prescription";
    }

    @GetMapping("/modify/{id}")
    public String showModifyPrescriptionForm(@PathVariable Integer id,Model model){
        if(prescriptionRepository.findById(id).isPresent()){
            Prescription prescription = prescriptionRepository.findById(id).get();
            model.addAttribute("prescription", prescription);
        }
        else{
            model.addAttribute("errorMessage", "Prescription not found");
        }
        return "prescription-modify";
    }

    @PostMapping("/modify/{id}")
    public String modifyPrescription(
            @PathVariable("id") Integer id,
            @ModelAttribute("prescription") Prescription prescriptionData) {
        // Find the original prescription
        Prescription prescription = prescriptionService.getPrescriptionById(id);
        if (prescription == null) {
            throw new IllegalArgumentException("Invalid Prescription ID: " + id);
        }

        // Update the editable field
        prescription.setPrescriptionText(prescriptionData.getPrescriptionText());
        prescription.setDate(prescriptionData.getDate());

        // Save changes
        prescriptionService.save(prescription);

        // Redirect to the prescription list
        return "redirect:/prescription";
    }


    @PostMapping("/delete/{id}")
    public String deletePrescription(@PathVariable(name = "id") int id) {
        prescriptionService.deletePrescription(id);
        return "redirect:/prescription";
    }

    @GetMapping("/view/{id}")
    public String showViewPrescriptionForm(@PathVariable(name = "id") int id, Model model) {
        if(prescriptionRepository.findById(id).isPresent()) {
            Prescription prescription = prescriptionRepository.findById(id).get();
            model.addAttribute("prescription", prescription);
            return "prescription-view";
        }
        else{
            model.addAttribute("errorMessage", "Prescription not found");
            return "redirect:/prescription";
        }
    }
    @GetMapping("/download/{id}")
    public void downloadPrescription(@PathVariable(name = "id") int id, HttpServletResponse response){
        try {
            // Retrieve the prescription details by ID
            Prescription prescription = prescriptionService.getPrescriptionById(id);

            // Set the content type to application/pdf
            response.setContentType("application/pdf");

            // Set the header to force the download
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=prescription_" + id + ".pdf";
            response.setHeader(headerKey, headerValue);

            // Use iText to write the prescription to a PDF
            ServletOutputStream outputStream = response.getOutputStream();
            createPrescriptionPDF(outputStream, prescription);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void createPrescriptionPDF(ServletOutputStream outputStream, Prescription prescription) throws Exception {
        com.itextpdf.kernel.pdf.PdfWriter writer = new com.itextpdf.kernel.pdf.PdfWriter(outputStream);
        com.itextpdf.kernel.pdf.PdfDocument pdfDocument = new com.itextpdf.kernel.pdf.PdfDocument(writer);
        com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdfDocument);

        // Title
        document.add(new com.itextpdf.layout.element.Paragraph("Medical Prescription")
                .setBold()
                .setFontSize(18)
                .setTextAlignment(TextAlignment.CENTER)
        );

        // Add today's date
        document.add(new com.itextpdf.layout.element.Paragraph("Date: " + LocalDate.now()));

        // Add Patient Information
        document.add(new com.itextpdf.layout.element.Paragraph("Patient Name: " + prescription.getPatient().getFirstName()
                + " " + prescription.getPatient().getLastName()));
        document.add(new com.itextpdf.layout.element.Paragraph("CIN: " + prescription.getPatient().getCin()));

        // Add Prescription Details
        document.add(new com.itextpdf.layout.element.Paragraph("Prescription:"));
        document.add(new com.itextpdf.layout.element.Paragraph(prescription.getPrescriptionText()));

        document.close();
    }
}
